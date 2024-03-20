package com.eruption.engine.core.graphics;

import com.eruption.engine.core.math.Vector3f;

import java.nio.ByteBuffer;

public final class Image {

    public enum EMipMapProcedure {
        Linear4x4,
        Normal
    }

    public enum EColorCorrection {
        Plain,
        Clamp3,
        Clamp4,
        Normalize
    }

    private final EPixelFormat pixelFormat;
    private Layer[] layers;

    public Image(int width, int height, EPixelFormat format) {
        this.pixelFormat = format;
        generateLayers(width, height);
        generateLayerBuffer(0);
    }

    private void generateLayers(int width, int height) {
        int w = width;
        int h = height;
        int numLayers = 1;
        do {
            numLayers++;
            w = Math.max(w / 2, 1);
            h = Math.max(h / 2, 1);
        } while (w != 1 || h != 1);
        layers = new Layer[numLayers];

        w = width;
        h = height;
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(w, h);
            w = Math.max(w / 2, 1);
            h = Math.max(h / 2, 1);
        }
    }

    private void generateLayerBuffer(int layer) {
        if (layer < 0 || layer >= this.layers.length) {
            return;
        }

        int bpp = bytePerPixel(this.pixelFormat);
        Layer l = this.layers[layer];
        l.size = l.width * l.height * bpp;
        l.buffer = ByteBuffer.allocateDirect(l.size);
    }

    public void generateMipMaps(EMipMapProcedure procedure) {
        for (int i = 1; i < layers.length; i++) {
            Layer source = this.layers[i - 1];
            Layer destination = this.layers[i];
            if (destination.buffer == null) {
                generateLayerBuffer(i);
            }

            switch (procedure) {
                case Linear4x4 -> generateLinear4x4MipMaps(source, destination);
                case Normal -> generateNormalMipMaps(source, destination);
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private void generateLinear4x4MipMaps(Layer src, Layer dst) {
        int bpp = bytePerPixel(pixelFormat);
        dst.buffer.clear();
        int maxWidth = src.width - 1;
        int maxHeight = src.height - 1;
        for (int y = 0; y < dst.height; y++) {
            int srcRow0Idx = Math.min(y * 2, maxHeight) * src.width * bpp;
            int srcRow1Idx = Math.min(y * 2 + 1, maxHeight) * src.width * bpp;
            for (int x = 0; x < dst.width; x++) {
                int srcX0 = Math.min(x * 2, maxWidth);
                int srcX1 = Math.min(x * 2 + 1, maxWidth);
                int src00Idx = srcRow0Idx + srcX0 * bpp;
                int src01Idx = srcRow0Idx + srcX1 * bpp;
                int src10Idx = srcRow1Idx + srcX0 * bpp;
                int src11Idx = srcRow1Idx + srcX1 * bpp;
                for (int c = 0; c < bpp; c++) {
                    int s = src.buffer.get(src00Idx + c)
                            + src.buffer.get(src01Idx + c)
                            + src.buffer.get(src10Idx + c)
                            + src.buffer.get(src11Idx + c);
                    s >>= 2;
                    dst.buffer.put((byte) s);
                }
            }
        }
    }


    @SuppressWarnings("Duplicates")
    private void generateNormalMipMaps(Layer src, Layer dst) {
        int bpp = bytePerPixel(pixelFormat);
        dst.buffer.clear();
        int maxWidth = src.width - 1;
        int maxHeight = src.height - 1;
        for (int y = 0; y < dst.height; y++) {
            int srcRow0Idx = Math.min(y * 2, maxHeight) * src.width * bpp;
            int srcRow1Idx = Math.min(y * 2 + 1, maxHeight) * src.width * bpp;
            for (int x = 0; x < dst.width; x++) {
                int srcX0 = Math.min(x * 2, maxWidth);
                int srcX1 = Math.min(x * 2 + 1, maxWidth);
                int src00Idx = srcRow0Idx + srcX0 * bpp;
                int src01Idx = srcRow0Idx + srcX1 * bpp;
                int src10Idx = srcRow1Idx + srcX0 * bpp;
                int src11Idx = srcRow1Idx + srcX1 * bpp;
                for (int c = 0; c < bpp; c++) {
                    int s = src.buffer.get(src00Idx + c)
                            + src.buffer.get(src01Idx + c)
                            + src.buffer.get(src10Idx + c)
                            + src.buffer.get(src11Idx + c);
                    s >>= 2;
                    dst.buffer.put((byte) s);
                }
            }
        }
    }

    public void colorCorrection(EColorCorrection colorCorrection) {
        switch (colorCorrection) {
            case Plain -> {
            }
            case Clamp3 -> colorCorrectionClamp3();
            case Clamp4 -> colorCorrectionClamp4();
            case Normalize -> colorCorrectionNormalize();
        }
    }


    private void colorCorrectionClamp3() {

        int bpp = bytePerPixel(pixelFormat);
        if (bpp < 3) {
            return;
        }
        for (Layer l : this.layers) {
            if (l.buffer == null) {
                continue;
            }
            // start at the beginning
            l.buffer.rewind();
            for (int i = 0; i < l.width * l.height; i++) {
                // read the first three byte and move the pointer back
                l.buffer.mark();
                float fr = (float) l.buffer.get() / 255.0f;
                float fg = (float) l.buffer.get() / 255.0f;
                float fb = (float) l.buffer.get() / 255.0f;
                l.buffer.rewind();

                float f = fr + fg + fb;
                if (f > 1.0f) {
                    fr /= f;
                    fg /= f;
                    fb /= f;
                }

                l.buffer.put((byte) (fr * 255.0f));
                l.buffer.put((byte) (fg * 255.0f));
                l.buffer.put((byte) (fb * 255.0f));

                // skip the remaining bytes
                for (int b=3; b<bpp; b++) {
                    l.buffer.get();
                }
            }
        }
    }

    private void colorCorrectionClamp4() {

        int bpp = bytePerPixel(pixelFormat);
        if (bpp < 4) {
            return;
        }
        for (Layer l : this.layers) {
            if (l.buffer == null) {
                continue;
            }
            // start at the beginning
            l.buffer.rewind();
            for (int i = 0; i < l.width * l.height; i++) {
                // read the first three byte and move the pointer back
                l.buffer.mark();
                float fr = (float) l.buffer.get() / 255.0f;
                float fg = (float) l.buffer.get() / 255.0f;
                float fb = (float) l.buffer.get() / 255.0f;
                float fa = (float) l.buffer.get() / 255.0f;
                l.buffer.rewind();

                float f = fr + fg + fb + fa;
                if (f > 1.0f) {
                    fr /= f;
                    fg /= f;
                    fb /= f;
                    fa /= f;
                }

                l.buffer.put((byte) (fr * 255.0f));
                l.buffer.put((byte) (fg * 255.0f));
                l.buffer.put((byte) (fb * 255.0f));
                l.buffer.put((byte) (fa * 255.0f));
            }
        }
    }
    private void colorCorrectionNormalize() {

        int bpp = bytePerPixel(pixelFormat);
        if (bpp < 3) {
            return;
        }
        Vector3f v = new Vector3f();
        for (Layer l : this.layers) {
            if (l.buffer == null) {
                continue;
            }
            // start at the beginning
            l.buffer.rewind();
            for (int i = 0; i < l.width * l.height; i++) {
                // read the first three byte and move the pointer back
                l.buffer.mark();
                float fx = ((float) l.buffer.get() / 255.0f) * 2.0f - 1.0f;
                float fy = ((float) l.buffer.get() / 255.0f) * 2.0f - 1.0f;
                float fz = ((float) l.buffer.get() / 255.0f) * 2.0f - 1.0f;
                l.buffer.rewind();

                v.set(fx, fy, fz);
                v.normalize();

                l.buffer.put((byte) ((v.x * 0.5f + 0.5) * 255.0f));
                l.buffer.put((byte) ((v.y * 0.5f + 0.5) * 255.0f));
                l.buffer.put((byte) ((v.z * 0.5f + 0.5) * 255.0f));

                // skip the remaining bytes
                for (int b=3; b<bpp; b++) {
                    l.buffer.get();
                }
            }
        }
    }

    private static final class Layer {
        final int width;
        final int height;
        int size;
        ByteBuffer buffer;

        public Layer(int width, int height) {
            this.width = width;
            this.height = height;
            this.size = 0;
            this.buffer = null;
        }
    }

    private int bytePerPixel(EPixelFormat format) {
        return switch (format) {
            case R -> 1;
            case RG -> 2;
            case RGB -> 3;
            case RGBA -> 4;
            default -> 0;
        };
    }

}

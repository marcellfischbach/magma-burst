package com.cobaltsky.engine.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Locator {

    private final String encoded;

    private final String path;

    private final String filename;

    private final String ext;

    public Locator(String encoded) {
        this.encoded = normalize(fixEncoded(encoded));

        int extIdx = this.encoded.lastIndexOf('.');
        if (extIdx != -1) {
            this.ext = this.encoded.substring(extIdx + 1);
        } else {
            this.ext = "";
        }

        int filenameEnd = extIdx != -1 ? extIdx : this.encoded.length();
        int extPath = this.encoded.lastIndexOf("/");
        if (extPath != -1) {
            this.path = this.encoded.substring(0, extPath + 1);
            this.filename = this.encoded.substring(extPath + 1, filenameEnd);
        } else {
            this.path = "";
            this.filename = this.encoded.substring(0, filenameEnd);
        }
    }

    public String getEncoded() {
        return encoded;
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }

    public String getExt() {
        return ext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Locator locator = (Locator) o;
        return Objects.equals(encoded, locator.encoded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encoded);
    }

    @Override
    public String toString() {
        return encoded;
    }

    public Locator append(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            return this;
        }

        char firstChar = encoded.charAt(0);
        if (firstChar == '/' || firstChar == '\\') {
            return new Locator(encoded);
        }

        return new Locator(path + encoded);
    }

    private static String fixEncoded(String encoded) {
        while (true) {
            String changed = encoded.replace("\\", "/");
            if (changed.equals(encoded)) {
                break;
            }
            encoded = changed;
        }

        while (true) {
            String changed = encoded.replace("//", "/");
            if (changed.equals(encoded)) {
                break;
            }
            encoded = changed;
        }

        return encoded;
    }

    private static String normalize(String path) {
        String[] parts = path.split("/");
        List<String> resultPath = new ArrayList<>(parts.length);
        for (String part : parts) {
            if (".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                if (!resultPath.isEmpty()) {
                    resultPath.remove(resultPath.size() - 1);
                }
            } else {
                resultPath.add(part);
            }
        }
        StringBuilder sb = new StringBuilder(path.length());
        for (String part : resultPath) {
            sb.append(part)
              .append("/");
        }

        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }


}

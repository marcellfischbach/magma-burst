package com.eruption.engine.graphics.opengl.gl4;

import com.eruption.engine.core.graphics.EVertexStream;
import com.eruption.engine.core.graphics.IGraphics;
import com.eruption.engine.core.graphics.IGraphicsMesh;
import com.eruption.engine.core.graphics.Mesh;
import com.eruption.engine.core.math.Color4f;
import com.eruption.engine.core.math.Vector2f;
import com.eruption.engine.core.math.Vector3f;
import com.eruption.engine.core.math.Vector4f;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL40.*;

public class GraphicsMeshGL4 implements IGraphicsMesh {

    private int primitive;

    private int indexCount;

    private int indexType;

    private int vao;

    private VertexBufferGL4 vb;
    private IndexBufferGL4 ib;


    @Override
    public void render(IGraphics graphics) {
        glBindVertexArray(vao);
        glDrawElements(this.primitive, this.indexCount, this.indexType, 0);
    }

    @Override
    public void update(IGraphics graphics, Mesh mesh) {

        if (vb != null) {
            vb.release();
        }
        if (ib != null) {
            ib.release();
        }

        if (this.vao == 0) {
            this.vao = glGenVertexArrays();
        }
        glBindVertexArray(this.vao);

        CreateVBResult vbResult = createVB((GraphicsGL4) graphics, mesh);
        if (vbResult == null) {
            return;
        }
        CreateIBResult ibResult = createIB((GraphicsGL4) graphics, mesh, vbResult.meshData.getNumberOfVertices());
        createStreamAssignments (vbResult.meshData);

        this.primitive = switch (mesh.getPrimitive()) {
            case POINTS -> GL_POINTS;
            case LINES -> GL_LINES;
            case TRIANGLES -> GL_TRIANGLES;
        };
        this.indexCount = ibResult.count;
        this.indexType = ibResult.type;

    }

    private record CreateVBResult(VertexBufferGL4 vb, MeshData meshData) {
    }

    private record MeshData(
            List<Vector4f> positions,
            List<Vector3f> normals,
            List<Vector3f> tangents,
            List<Vector3f> coTangents,
            List<Color4f> colors,
            List<Vector2f> uv0,
            List<Vector3f> uv03,
            List<Vector4f> uv04,

            List<Vector2f> uv1,
            List<Vector3f> uv13,
            List<Vector4f> uv14
    ) {

        public int getNumberOfVertices () {
            return positions.size();
        }

        private boolean isValid() {
            if (positions.isEmpty()) {
                return false;
            }
            if ((!normals.isEmpty() && normals.size() != positions.size())
                    || (!tangents.isEmpty() && tangents.size() != positions.size())
                    || (!coTangents.isEmpty() && coTangents.size() != positions.size())
                    || (!colors.isEmpty() && colors.size() != positions.size())
                    || (!uv0.isEmpty() && uv0.size() != positions.size())
                    || (!uv03.isEmpty() && uv03.size() != positions.size())
                    || (!uv04.isEmpty() && uv04.size() != positions.size())
                    || (!uv1.isEmpty() && uv1.size() != positions.size())
                    || (!uv13.isEmpty() && uv13.size() != positions.size())
                    || (!uv14.isEmpty() && uv14.size() != positions.size())
            ) {
                return false;
            }

            if ((uv0.size() + uv03.size() + uv04.size() > positions.size())
                    || (uv1.size() + uv13.size() + uv14.size() > positions.size())) {
                return false;
            }
            return true;
        }

        private int getVertexSizeInBytes () {
            //
            // calculate the size of a single vertex in bytes
            int vertexSize = 4 * Float.BYTES; // the position size
            if (!normals.isEmpty()) {
                vertexSize += 3 * Float.BYTES;
            }
            if (!tangents.isEmpty()) {
                vertexSize += 3 * Float.BYTES;
            }
            if (!coTangents.isEmpty()) {
                vertexSize += 3 * Float.BYTES;
            }
            if (!colors.isEmpty()) {
                vertexSize += 4 * Float.BYTES;
            }
            if (!uv0.isEmpty()) {
                vertexSize += 2 * Float.BYTES;
            }
            if (!uv03.isEmpty()) {
                vertexSize += 3 * Float.BYTES;
            }
            if (!uv04.isEmpty()) {
                vertexSize += 4 * Float.BYTES;
            }
            if (!uv1.isEmpty()) {
                vertexSize += 2 * Float.BYTES;
            }
            if (!uv13.isEmpty()) {
                vertexSize += 3 * Float.BYTES;
            }
            if (!uv14.isEmpty()) {
                vertexSize += 4 * Float.BYTES;
            }

            return vertexSize;
        }
        
        private void put (ByteBuffer buffer) {
            for (int i = 0; i < positions.size(); i++) {
                put(buffer, positions.get(i));
                if (!normals.isEmpty()) {
                    put(buffer, normals.get(i));
                }
                if (!tangents.isEmpty()) {
                    put(buffer, tangents.get(i));
                }
                if (!coTangents.isEmpty()) {
                    put(buffer, coTangents.get(i));
                }
                if (!colors.isEmpty()) {
                    put(buffer, colors.get(i));
                }
                if (!uv0.isEmpty()) {
                    put(buffer, uv0.get(i));
                }
                if (!uv03.isEmpty()) {
                    put(buffer, uv03.get(i));
                }
                if (!uv04.isEmpty()) {
                    put(buffer, uv04.get(i));
                }
                if (!uv1.isEmpty()) {
                    put(buffer, uv0.get(i));
                }
                if (!uv13.isEmpty()) {
                    put(buffer, uv03.get(i));
                }
                if (!uv14.isEmpty()) {
                    put(buffer, uv04.get(i));
                }
            }
        }

        private static void put (ByteBuffer buffer, Vector2f v) {
            buffer.putFloat(v.x);
            buffer.putFloat(v.y);
        }

        private static void put (ByteBuffer buffer, Vector3f v) {
            buffer.putFloat(v.x);
            buffer.putFloat(v.y);
            buffer.putFloat(v.z);
        }

        private static void put (ByteBuffer buffer, Vector4f v) {
            buffer.putFloat(v.x);
            buffer.putFloat(v.y);
            buffer.putFloat(v.z);
            buffer.putFloat(v.w);
        }

        private static void put (ByteBuffer buffer, Color4f c) {
            buffer.putFloat(c.r);
            buffer.putFloat(c.g);
            buffer.putFloat(c.b);
            buffer.putFloat(c.a);
        }

    }

    private CreateVBResult createVB(GraphicsGL4 graphics, Mesh mesh) {
        //
        // check if all buffers are either empty of have the same size
        MeshData data = new MeshData(
                mesh.getPositions(),
                mesh.getNormals(),
                mesh.getTangents(),
                mesh.getCoTangents(),
                mesh.getColors(),
                mesh.getUV0(),
                mesh.getUV03(),
                mesh.getUV04(),
                mesh.getUV0(),
                mesh.getUV03(),
                mesh.getUV04()
        );

        if (!data.isValid()) {
            return null;
        }

        int vertexSizeInBytes = data.getVertexSizeInBytes();
        ByteBuffer buffer = BufferUtils.createByteBuffer(vertexSizeInBytes * data.positions.size());
        buffer.clear();
        data.put(buffer);
        buffer.rewind();

        VertexBufferGL4 vb = graphics.createVertexBuffer(vertexSizeInBytes * data.positions.size());
        vb.copy(buffer);

        return new CreateVBResult(vb,data);
    }




    private record CreateIBResult(IndexBufferGL4 ib, int count, int type) {
    }

    private CreateIBResult createIB(GraphicsGL4 graphics, Mesh mesh, int numVertices) {
        if (numVertices >= 0x8ffff) {
            // create 32bit indices
            List<Integer> indices = mesh.getIndices();
            ByteBuffer buffer = BufferUtils.createByteBuffer(indices.size() * 4);
            buffer.clear();
            for (Integer index : indices) {
                buffer.putInt(index);
            }
            buffer.rewind();
            IndexBufferGL4 ib = graphics.createIndexBuffer(indices.size() * 4);
            ib.copy(buffer);
            return new CreateIBResult(ib, indices.size(), GL_UNSIGNED_INT);
        } else {

            // create 16bit indices
            List<Integer> indices = mesh.getIndices();
            ByteBuffer buffer = BufferUtils.createByteBuffer(indices.size() * 2);
            buffer.clear();
            for (Integer index : indices) {
                buffer.putShort(index.shortValue());
            }
            buffer.rewind();
            IndexBufferGL4 ib = graphics.createIndexBuffer(indices.size() * 2);
            ib.copy(buffer);
            return new CreateIBResult(ib, indices.size(), GL_UNSIGNED_SHORT);

        }
    }

    private void createStreamAssignments (MeshData meshData) {
        int stride = meshData.getVertexSizeInBytes();
        int ptr = 0;
        glVertexAttribPointer(EVertexStream.POSITION.ordinal(),4, GL_FLAT, false, stride, ptr);
        glEnableVertexAttribArray(EVertexStream.POSITION.ordinal());
        ptr += 4 * Float.SIZE;

        if (!meshData.normals.isEmpty()) {
            glVertexAttribPointer(EVertexStream.NORMAL.ordinal(), 3, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.NORMAL.ordinal());
            ptr += 3 * Float.SIZE;
        }

        if (!meshData.tangents.isEmpty()) {
            glVertexAttribPointer(EVertexStream.TANGENT.ordinal(), 3, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.TANGENT.ordinal());
            ptr += 3 * Float.SIZE;
        }
        if (!meshData.coTangents.isEmpty()) {
            glVertexAttribPointer(EVertexStream.CO_TANGENT.ordinal(), 3, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.CO_TANGENT.ordinal());
            ptr += 3 * Float.SIZE;
        }

        if (!meshData.colors.isEmpty()) {
            glVertexAttribPointer(EVertexStream.COLOR.ordinal(), 4, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.COLOR.ordinal());
            ptr += 4 * Float.SIZE;
        }

        if (!meshData.uv0.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV0.ordinal(), 2, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV0.ordinal());
            ptr += 2 * Float.SIZE;
        }
        if (!meshData.uv03.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV0.ordinal(), 3, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV0.ordinal());
            ptr += 3 * Float.SIZE;
        }
        if (!meshData.uv04.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV0.ordinal(), 4, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV0.ordinal());
            ptr += 4 * Float.SIZE;
        }
        if (!meshData.uv1.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV1.ordinal(), 2, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV1.ordinal());
            ptr += 2 * Float.SIZE;
        }
        if (!meshData.uv13.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV1.ordinal(), 3, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV1.ordinal());
            ptr += 3 * Float.SIZE;
        }
        if (!meshData.uv14.isEmpty()) {
            glVertexAttribPointer(EVertexStream.UV1.ordinal(), 4, GL_FLAT, false, stride, ptr);
            glEnableVertexAttribArray(EVertexStream.UV1.ordinal());
            ptr += 4 * Float.SIZE;
        }
    }
}

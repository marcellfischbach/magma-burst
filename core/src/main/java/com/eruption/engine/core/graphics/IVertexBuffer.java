package com.eruption.engine.core.graphics;

import java.nio.ByteBuffer;

public interface IVertexBuffer {

    void bind ();

    void unbind ();

    void copy (ByteBuffer buffer);
    void copy (ByteBuffer buffer, long offset);

    ByteBuffer map();
    void unmap ();
}

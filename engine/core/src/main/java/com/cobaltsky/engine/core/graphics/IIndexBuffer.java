package com.cobaltsky.engine.core.graphics;

import java.nio.ByteBuffer;

public interface IIndexBuffer {

    void bind ();

    void unbind ();

    void copy (ByteBuffer buffer);
    void copy (ByteBuffer buffer, long offset);

    ByteBuffer map();
    void unmap ();
}

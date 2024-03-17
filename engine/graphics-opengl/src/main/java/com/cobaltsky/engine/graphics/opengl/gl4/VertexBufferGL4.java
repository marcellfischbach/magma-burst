package com.cobaltsky.engine.graphics.opengl.gl4;

import com.cobaltsky.engine.core.graphics.IVertexBuffer;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL40.*;

public class VertexBufferGL4 extends NamedGL4 implements IVertexBuffer {

    public VertexBufferGL4() {
        this.name = glGenBuffers();
    }

    public void create (int size) {
        glBufferData(GL_ARRAY_BUFFER, size, GL_STATIC_DRAW);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.name);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }


    @Override
    public void copy(ByteBuffer buffer) {
        glBufferSubData(GL_ARRAY_BUFFER, 0, buffer);
    }

    @Override
    public void copy(ByteBuffer buffer, long offset) {
        glBufferSubData(GL_ARRAY_BUFFER, offset, buffer);
    }

    @Override
    public ByteBuffer map() {
        bind ();
        return glMapBuffer(GL_ARRAY_BUFFER, GL_READ_ONLY);
    }

    @Override
    public void unmap() {
        glUnmapBuffer(GL_ARRAY_BUFFER);
    }


    @Override
    protected void release() {
        if (this.name != 0) {
            glDeleteBuffers(this.name);
            this.name = 0;
        }
    }
}

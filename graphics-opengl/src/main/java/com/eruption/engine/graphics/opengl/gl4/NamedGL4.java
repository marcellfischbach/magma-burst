package com.eruption.engine.graphics.opengl.gl4;

public abstract class NamedGL4 {

    protected int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    protected abstract void release();
}

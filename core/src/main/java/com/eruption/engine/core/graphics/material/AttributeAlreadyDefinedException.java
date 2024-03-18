package com.eruption.engine.core.graphics.material;

public class AttributeAlreadyDefinedException extends RuntimeException {

    private final String name;

    public AttributeAlreadyDefinedException(String name) {
        super("The attribute '" + name + "' already defined.");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

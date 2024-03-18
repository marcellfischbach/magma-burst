package com.eruption.engine.core.graphics.material;

public class InvalidAttributeTypeException extends RuntimeException {

    private final String attributeName;
    private final EAttributeType actualType;
    private final EAttributeType expectedType;

    public InvalidAttributeTypeException(String attributeName,
                                         EAttributeType actualType,
                                         EAttributeType expectedType) {
        super ("Attribute '" + attributeName + "' is of type '" + actualType + "' bute type '" + expectedType + "' was expected.");
        this.attributeName = attributeName;
        this.actualType = actualType;
        this.expectedType = expectedType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public EAttributeType getExpectedType() {
        return expectedType;
    }

    public EAttributeType getActualType() {
        return actualType;
    }
}

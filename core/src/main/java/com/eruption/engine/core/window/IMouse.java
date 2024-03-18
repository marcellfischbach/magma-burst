package com.eruption.engine.core.window;

public interface IMouse {

    int getX();
    int getY();

    int getDeltaX();
    int getDeltaY();

    boolean isButtonDown (EMouseButton button);
    boolean isButtonUp (EMouseButton button);
    boolean isButtonPressed (EMouseButton button);
    boolean isButtonReleased (EMouseButton button);

    void setMode (EMouseMode mode);
    EMouseMode getMode ();
}

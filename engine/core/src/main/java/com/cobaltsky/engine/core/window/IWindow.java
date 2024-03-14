package com.cobaltsky.engine.core.window;

public interface IWindow {

    int getWidth ();

    int getHeight ();

    int getX();

    int getY();

    void setPosition (int x, int y);

    boolean isFullscreen ();

    void setTitle(String title);
    String getTitle();

    void pollEvents ();

    void finishFrame ();

    boolean isVisible ();

    boolean shouldClose();

    IKeyboard getKeyboard();
}

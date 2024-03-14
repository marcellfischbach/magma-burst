package com.cobaltsky.engine.core.window;

public interface IWindow {

    int getWidth ();

    int getHeight ();

    boolean isFullscreen ();

    void setTitle(String title);
    String getTitle();

    void pollEvents ();

    boolean isVisible ();

    boolean shouldClose();
}
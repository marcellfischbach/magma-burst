package com.eruption.engine.graphics.opengl.gl4;

import static org.lwjgl.opengl.GL40.*;

public final class ErrorGL4 {

    public static void check() {

    }

    public static void _check() {
        int error = glGetError();
        if (error == GL_NO_ERROR) {
            return;
        }

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        System.err.print("Error " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + " : " + stackTraceElement.getLineNumber() + "  ");

        switch (error) {
            case GL_INVALID_ENUM -> System.err.println("GL_INVALID_ENUM");
            case GL_INVALID_VALUE -> System.err.println("GL_INVALID_VALUE");
            case GL_INVALID_OPERATION -> System.err.println("GL_INVALID_OPERATION");
            case GL_INVALID_FRAMEBUFFER_OPERATION -> System.err.println("GL_INVALID_FRAMEBUFFER_OPERATION");
            case GL_OUT_OF_MEMORY -> System.err.println("GL_OUT_OF_MEMORY");
            case GL_STACK_UNDERFLOW -> System.err.println("GL_STACK_UNDERFLOW");
            case GL_STACK_OVERFLOW -> System.err.println("GL_STACK_OVERFLOW");
            default -> System.err.println("Error: " + error);
        }
    }


    private ErrorGL4() {

    }
}

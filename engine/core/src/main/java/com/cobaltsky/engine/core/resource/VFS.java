package com.cobaltsky.engine.core.resource;

import java.io.FileInputStream;
import java.io.InputStream;

public class VFS {

    public InputStream open (String filename) {
        return VFS.class.getResourceAsStream(filename);
    }


    private VFS () {
    }

    private static VFS instance = null;

    public static VFS instance () {
        if (instance == null) {
            instance = new VFS();
        }
        return instance;
    }
}

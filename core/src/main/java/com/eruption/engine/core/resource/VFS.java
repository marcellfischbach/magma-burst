package com.eruption.engine.core.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class VFS {

    private static final Logger LOGGER = LogManager.getLogger(VFS.class);

    public InputStream open (String filename) {
        return VFS.class.getResourceAsStream(filename);
    }

    public InputStream open(Locator locator) {
        return open("/data/" + locator.getEncoded());
    }

    public String getTextContent (Locator locator) {
        try (InputStream is = open(locator)) {
            byte[] buffer = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (is.available() != 0) {
                int size = is.read(buffer);
                sb.append(new String(buffer, 0, size));
            }
            return sb.toString();
        }
        catch (Exception e) {
            LOGGER.error("", e);
            return "";
        }
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

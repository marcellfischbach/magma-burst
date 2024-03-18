package com.eruption.engine.core.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetManager {

    private final List<IAssetLoader> assetLoaders = new ArrayList<>();

    private final Map<Locator, Map<Class<?>, Object>> assetCache = new HashMap<>();

    private static final Object NULL = new Object();

    public AssetManager register (IAssetLoader loader) {
        this.assetLoaders.add(loader);
        return this;
    }


    public <T> T load(Class<T> cls, Locator locator) {
        for (IAssetLoader<?> assetLoader : this.assetLoaders) {
            if (assetLoader.canLoad(cls, locator)) {
                return cls.cast(assetLoader.load(cls, locator));
            }
        }
        return null;
    }

    public <T> T get(Class<T> cls, Locator locator) {
        Map<Class<?>, Object> cacheMap = this.assetCache.computeIfAbsent(locator, l -> new HashMap<>());
        Object o = cacheMap.computeIfAbsent(cls, c -> null);
        if (o == NULL) {
            return null;
        }

        T asset = load(cls, locator);
        if (asset != null) {
            cacheMap.put(cls, asset);
        }
        else {
            cacheMap.put(cls, NULL);
        }
        return asset;
    }

    private AssetManager () {
    }

    private static AssetManager instance = null;

    public static AssetManager instance () {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

}

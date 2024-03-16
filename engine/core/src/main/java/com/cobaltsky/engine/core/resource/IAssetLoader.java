package com.cobaltsky.engine.core.resource;

public interface IAssetLoader<T> {

    boolean canLoad(Class<?> cls, Locator locator);

    T load(Class<?> cls, Locator locator);

}

package com.cobaltsky.engine.core.module;

import com.cobaltsky.engine.core.Engine;

import java.util.Set;

public interface IModule {

    Set<Class<?>> definingClasses ();

    Set<Class<?>> dependingClasses();

    String getName ();

    void register (Engine engine);

    void initialize (Engine engine) throws Exception;

}

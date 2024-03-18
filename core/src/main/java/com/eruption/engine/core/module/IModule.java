package com.eruption.engine.core.module;

import com.eruption.engine.core.Engine;

import java.util.Set;

public interface IModule {

    Set<Class<?>> definingClasses ();

    Set<Class<?>> dependingClasses();

    String getName ();

    void register (Engine engine);

    void initialize (Engine engine) throws Exception;

}

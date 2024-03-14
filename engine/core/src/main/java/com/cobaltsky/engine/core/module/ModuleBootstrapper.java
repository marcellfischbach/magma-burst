package com.cobaltsky.engine.core.module;

import com.cobaltsky.engine.core.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class ModuleBootstrapper {

    private static final Logger LOGGER = LogManager.getLogger(ModuleBootstrapper.class);

    public static void bootstrap(Engine engine) {

        try {

            ServiceLoader<IModule> serviceLoader = ServiceLoader.load(IModule.class);
            List<IModule> rawModules = new ArrayList<>();
            for (IModule iModule : serviceLoader) {
                rawModules.add(iModule);
            }

            List<IModule> sortedModules = sort(rawModules);

            for (IModule module : sortedModules) {
                module.register(engine);
            }


            for (IModule module : sortedModules) {
                module.initialize(engine);
            }
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    private static List<IModule> sort(List<IModule> modules) {
        return modules;
    }

}

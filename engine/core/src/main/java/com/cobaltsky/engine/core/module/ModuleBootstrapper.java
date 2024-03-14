package com.cobaltsky.engine.core.module;

import com.cobaltsky.engine.core.Engine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

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
                LOGGER.info("Register: " + module.getName());
                module.register(engine);
            }


            for (IModule module : sortedModules) {
                LOGGER.info("Initialize: " + module.getName());
                module.initialize(engine);
            }
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    private static List<IModule> sort(List<IModule> modules) {
        List<IModule> pendingModules = new ArrayList<>(modules);
        Set<Class<?>> availableClasses = new HashSet<>();
        List<IModule> sortedModules = new ArrayList<>();
        while (!pendingModules.isEmpty()) {
            boolean foundOne = false;
            for (IModule module : pendingModules) {
                Set<Class<?>> dependingedClasses = module.dependingClasses();
                if (dependingedClasses.isEmpty() || availableClasses.containsAll(dependingedClasses)) {
                    availableClasses.addAll(module.definingClasses());
                    sortedModules.add(module);
                    pendingModules.remove(module);
                    foundOne = true;
                    break;
                }
            }
            if (!foundOne) {
                throw new CyclicDependenciesFoundException();
            }
        }

        return sortedModules;
    }


}

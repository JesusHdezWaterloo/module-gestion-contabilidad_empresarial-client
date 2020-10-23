package com.jhw.module.gestion.contabilidad.consume.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Modulo de SpringA-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class SpringAConsumeCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new SpringAConsumeCoreInjectionConfig());

    private static SpringAConsumeCoreModule INSTANCE;

    public static SpringAConsumeCoreModule getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("El modulo de Spring A Consume-Core no se ha inicializado");
        }
        return INSTANCE;
    }

    public static SpringAConsumeCoreModule init() {
        INSTANCE = new SpringAConsumeCoreModule();
        return getInstance();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Spring A Consume Core Module";
    }

}

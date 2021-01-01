package com.jhw.module.gestion.contabilidad.consume.module;

import com.root101.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jhw.module.gestion.contabilidad.service.ResourceServiceClientImplementation;

/**
 * Modulo de Contabilidad_Empresarial-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ContabilidadConsumeCoreModule extends DefaultAbstractModule {

    private final Injector inj = Guice.createInjector(new ContabilidadConsumeCoreInjectionConfig());

    private static ContabilidadConsumeCoreModule INSTANCE;

    public static ContabilidadConsumeCoreModule getInstance() {
        if (INSTANCE == null) {
            init();
        }
        return INSTANCE;
    }

    private static void init() {
        INSTANCE = new ContabilidadConsumeCoreModule();
        ResourceServiceClientImplementation.init();
    }

    @Override
    protected <T> T getOwnImplementation(Class<T> type) {
        return inj.getInstance(type);
    }

    @Override
    public String getModuleName() {
        return "Contabilidad Empresarial Consume Core Module";
    }

}

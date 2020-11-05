package com.jhw.module.gestion.contabilidad.consume.module;

import com.clean.core.app.modules.DefaultAbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

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
            throw new NullPointerException("El modulo de Contabilidad_Empresarial Consume-Core no se ha inicializado");
        }
        return INSTANCE;
    }

    public static ContabilidadConsumeCoreModule init() {
        INSTANCE = new ContabilidadConsumeCoreModule();
        return getInstance();
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

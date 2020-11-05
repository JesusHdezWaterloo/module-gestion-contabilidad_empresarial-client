package com.jhw.module.gestion.contabilidad.consume.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Configuracion del injection del modulo de PlanTrabajo-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ContabilidadConsumeCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(OstTaskUseCase.class).to(OstTaskUseCaseImpl.class).in(Singleton.class);
        bind(OstTaskUseCaseConsume.class).to(OstTaskUseCaseImpl.class).in(Singleton.class);
    }

}

package com.jhw.module.gestion.contabilidad.consume.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jhw.module.gestion.contabilidad.consume.usecase_def.*;
import com.jhw.module.gestion.contabilidad.consume.usecase_impl.*;
import com.jhw.module.gestion.contabilidad.core.usecase_def.*;

/**
 * Configuracion del injection del modulo de PlanTrabajo-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ContabilidadConsumeCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(CuadreUseCase.class).to(CuadreUseCaseImpl.class).in(Singleton.class);
        bind(CuadreUseCaseConsume.class).to(CuadreUseCaseImpl.class).in(Singleton.class);
    }

}

package com.jhw.module.gestion.contabilidad.consume.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.jhw.module.gestion.contabilidad.consume.usecase_impl.TrabajoUseCaseImpl;
import com.jhw.example.spring_a.core.usecase_def.*;

/**
 * Configuracion del injection del modulo de SpringA-consume-core.
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class SpringAConsumeCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(TrabajoUseCase.class).to(TrabajoUseCaseImpl.class).in(Singleton.class);
    }

}

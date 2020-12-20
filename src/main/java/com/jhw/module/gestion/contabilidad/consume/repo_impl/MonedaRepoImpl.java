/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.repo_impl;

import static com.jhw.module.gestion.contabilidad.core.ModuleGestionContabilidadEmpresarialConstants.*;
import com.jhw.module.gestion.contabilidad.core.domain.*;
import com.jhw.module.gestion.contabilidad.core.usecase_def.*;
import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.ConsumerRepoTemplate;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MonedaRepoImpl extends ConsumerRepoTemplate<MonedaDomain> implements MonedaUseCase {

    public MonedaRepoImpl() {
        super(MonedaDomain.class, RESTHandler.urlActualREST() + MONEDA_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    @Override
    public MonedaDomain findMonedaBase() throws Exception {
        return template().getForObject(urlGeneral + MONEDA_FIND_BASE_PATH, MonedaDomain.class);
    }

}

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
public class TipoOperacionContableRepoImpl extends ConsumerRepoTemplate<TipoOperacionContableDomain> implements TipoOperacionContableUseCase {

    public TipoOperacionContableRepoImpl() {
        super(TipoOperacionContableDomain.class, RESTHandler.urlActualREST() + TIPO_OPERACION_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }
}

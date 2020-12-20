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
import com.jhw.utils.spring.client.RestTemplateUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class OperacionContableRepoImpl extends ConsumerRepoTemplate<OperacionContableDomain> implements OperacionContableUseCase {

    public OperacionContableRepoImpl() {
        super(OperacionContableDomain.class, RESTHandler.urlActualREST() + OPERACION_CONTABLE_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    /**
     * Delegate a findAll(Integer idCuentaContable) para lightweight
     *
     * @param cuenta
     * @return
     * @throws Exception
     */
    @Override
    public List<OperacionContableDomain> findAll(CuentaContableDomain cuenta) throws Exception {
        return findAll(cuenta.getIdCuentaContable());
    }

    @Override
    public List<OperacionContableDomain> findAll(Integer idCuentaContable) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(CUENTA, idCuentaContable);
        return RestTemplateUtils.getForList(template(), urlGeneral + OPERACION_CONTABLE_FIND_ALL_PATH, map, OperacionContableDomain.class);
    }

}

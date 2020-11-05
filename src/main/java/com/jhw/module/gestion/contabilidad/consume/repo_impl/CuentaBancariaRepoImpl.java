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
import java.util.*;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CuentaBancariaRepoImpl extends ConsumerRepoTemplate<CuentaBancariaDomain> implements CuentaBancariaUseCase {

    public CuentaBancariaRepoImpl() {
        super(RESTHandler.restTemplate(), CuentaBancariaDomain.class, RESTHandler.urlActualREST() + CUENTA_BANCARIA_GENERAL_PATH);
    }

    @Override
    public List<Cuenta> findAllCuentas() throws Exception {
        return RestTemplateUtils.getForList(template, urlGeneral + CUENTA_BANCARIA_FIND_ALL_CUENTAS_PATH, Cuenta.class);
    }

    @Override
    public CuentaBancariaDomain findCuentaDefault(MonedaDomain moneda) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(MONEDA, moneda);
        return template.getForObject(urlGeneral + CUENTA_BANCARIA_FIND_DEFAULT_PATH, CuentaBancariaDomain.class, map);
    }

    @Override
    public List<CuentaBancariaDomain> findAll(String searchText) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(SEARCH_TEXT, searchText);
        return RestTemplateUtils.getForList(template, urlGeneral + CUENTA_BANCARIA_FIND_ALL_SEARCH_PATH, map, CuentaBancariaDomain.class);
    }
}

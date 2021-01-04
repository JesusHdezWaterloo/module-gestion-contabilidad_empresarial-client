/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.gestion.contabilidad.consume.repo_impl;

import static com.root101.module.gestion.contabilidad.rest.ModuleGestionContabilidadEmpresarialConstants.*;
import com.root101.module.gestion.contabilidad.core.domain.*;
import com.root101.module.gestion.contabilidad.core.usecase_def.*;
import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.ConsumerRepoTemplate;
import com.jhw.utils.spring.client.RestTemplateUtils;
import java.util.*;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuentaBancariaRepoImpl extends ConsumerRepoTemplate<CuentaBancariaDomain> implements CuentaBancariaUseCase {

    public CuentaBancariaRepoImpl() {
        super(CuentaBancariaDomain.class, RESTHandler.urlActualREST() + CUENTA_BANCARIA_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    @Override
    public List<Cuenta> findAllCuentas() throws Exception {
        return RestTemplateUtils.getForList(template(), urlGeneral + CUENTA_BANCARIA_FIND_ALL_CUENTAS_PATH, Cuenta.class);
    }

    /**
     * Delegate to findCuentaDefault(Integer idMoneda) para * Delegate to
     * findAllCuenta(Integer idTipoCuenta) para lightweight
     *
     *
     * @param moneda
     * @return
     * @throws Exception
     */
    @Override
    public CuentaBancariaDomain findCuentaDefault(MonedaDomain moneda) throws Exception {
        return findCuentaDefault(moneda.getIdMoneda());
    }

    @Override
    public CuentaBancariaDomain findCuentaDefault(Integer idMoneda) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(MONEDA, idMoneda);
        return template().getForObject(urlGeneral + CUENTA_BANCARIA_FIND_DEFAULT_PATH, CuentaBancariaDomain.class, map);
    }

    @Override
    public List<CuentaBancariaDomain> findAll(String searchText) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(SEARCH_TEXT, searchText);
        return RestTemplateUtils.getForList(template(), urlGeneral + CUENTA_BANCARIA_FIND_ALL_SEARCH_PATH, map, CuentaBancariaDomain.class);
    }

}

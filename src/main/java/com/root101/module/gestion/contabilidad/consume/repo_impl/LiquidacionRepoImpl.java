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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LiquidacionRepoImpl extends ConsumerRepoTemplate<LiquidacionDomain> implements LiquidacionUseCase {

    public LiquidacionRepoImpl() {
        super(LiquidacionDomain.class, RESTHandler.urlActualREST() + LIQUIDACION_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    /**
     * Delegate to findAll(Integer IdCuentaBancaria) para lightweight
     *
     * @param cuenta
     * @return
     * @throws Exception
     */
    @Override
    public List<LiquidacionDomain> findAll(CuentaBancariaDomain cuenta) throws Exception {
        return findAll(cuenta.getIdCuentaBancaria());
    }

    @Override
    public List<LiquidacionDomain> findAll(Integer IdCuentaBancaria) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(CUENTA, IdCuentaBancaria);
        return RestTemplateUtils.getForList(template(), urlGeneral + LIQUIDACION_FIND_ALL_PATH, map, LiquidacionDomain.class);
    }

    @Override
    public LiquidacionDomain getLiquidacion(CuadreDomain cuadre) throws Exception {
        return getLiquidacion(cuadre.getIdCuadre());
    }

    @Override
    public LiquidacionDomain getLiquidacion(Integer idCuadre) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(CUADRE, idCuadre);
        return template().getForObject(urlGeneral + LIQUIDACION_GET_PATH, LiquidacionDomain.class, map);
    }

}

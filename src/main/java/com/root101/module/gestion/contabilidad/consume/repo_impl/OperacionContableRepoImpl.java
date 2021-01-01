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

import static com.root101.module.gestion.contabilidad.core.ModuleGestionContabilidadEmpresarialConstants.*;
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

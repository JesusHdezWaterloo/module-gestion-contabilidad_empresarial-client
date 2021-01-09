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

import static com.root101.module.gestion.contabilidad.rest.ModuleGestionContabilidadRESTConstants.*;
import com.root101.module.gestion.contabilidad.core.domain.*;
import com.root101.module.gestion.contabilidad.core.usecase_def.*;
import com.root101.module.util.rest_config.services.RESTHandler;
import com.root101.spring.client.ConsumerRepoTemplate;
import com.root101.spring.client.RestTemplateUtils;
import java.util.*;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuadreRepoImpl extends ConsumerRepoTemplate<CuadreDomain> implements CuadreUseCase {

    public CuadreRepoImpl() {
        super(CuadreDomain.class, RESTHandler.urlActualREST() + CUADRE_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    @Override
    public List<CuadreDomain> findAllPending() throws Exception {
        return RestTemplateUtils.getForList(template(), urlGeneral + CUADRE_FIND_ALL_PENDING_PATH, CuadreDomain.class);
    }

    @Override
    public List<CuadreDomain> findAllLiquidadas() throws Exception {
        return RestTemplateUtils.getForList(template(), urlGeneral + CUADRE_FIND_ALL_LIQUIDADAS_PATH, CuadreDomain.class);
    }

    @Override
    public List<CuadreDomain> findByLiquidada(boolean liquidada) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(LIQUIDADA, liquidada);
        return RestTemplateUtils.getForList(template(), urlGeneral + CUADRE_FIND_BY_LIQUIDADA_PATH, map, CuadreDomain.class);
    }

}

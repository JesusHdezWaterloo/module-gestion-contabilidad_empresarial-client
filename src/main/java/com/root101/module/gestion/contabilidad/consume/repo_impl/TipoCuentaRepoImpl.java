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
public class TipoCuentaRepoImpl extends ConsumerRepoTemplate<TipoCuentaDomain> implements TipoCuentaUseCase {

    public TipoCuentaRepoImpl() {
        super(TipoCuentaDomain.class, RESTHandler.urlActualREST() + TIPO_CUENTA_GENERAL_PATH);
    }

    @Override
    protected RestOperations template() {
        return RESTHandler.OAuth2RestTemplate();
    }

    /**
     * Delegate a findAllEquivalent(Integer idTipoCuenta) para lightweight
     *
     * @param tipoCuenta
     * @return
     * @throws Exception
     */
    @Override
    public List<TipoCuentaDomain> findAllEquivalent(TipoCuentaDomain tipoCuenta) throws Exception {
        return findAllEquivalent(tipoCuenta.getIdTipoCuenta());
    }

    @Override
    public List<TipoCuentaDomain> findAllEquivalent(Integer idTipoCuenta) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(TIPO_CUENTA, idTipoCuenta);
        return RestTemplateUtils.getForList(template(), urlGeneral + TIPO_CUENTA_FIND_ALL_EQUIVALENT_PATH, map, TipoCuentaDomain.class);
    }

}

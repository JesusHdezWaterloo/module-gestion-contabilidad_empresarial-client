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
package com.root101.module.gestion.contabilidad.consume.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.module.gestion.contabilidad.consume.module.ContabilidadConsumeCoreModule;
import com.root101.module.gestion.contabilidad.consume.repo_impl.FormaPagoRepoImpl;
import com.root101.module.gestion.contabilidad.consume.usecase_def.FormaPagoUseCaseConsume;
import com.root101.module.gestion.contabilidad.core.domain.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class FormaPagoUseCaseImpl extends DefaultCRUDUseCase<FormaPagoDomain> implements FormaPagoUseCaseConsume {

    private final FormaPagoRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(FormaPagoRepoImpl.class);

    public FormaPagoUseCaseImpl() {
        setRepo(repoUC);
    }

}

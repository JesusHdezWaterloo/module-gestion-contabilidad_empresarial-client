/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.usecase_impl;

import com.clean.core.app.usecase.*;
import com.jhw.module.gestion.contabilidad.consume.module.ContabilidadConsumeCoreModule;
import com.jhw.module.gestion.contabilidad.consume.repo_impl.*;
import com.jhw.module.gestion.contabilidad.consume.usecase_def.*;
import com.jhw.module.gestion.contabilidad.core.domain.*;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TitularUseCaseImpl extends DefaultCRUDUseCase<TitularDomain> implements TitularUseCaseConsume {

    private final TitularRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TitularRepoImpl.class);

    public TitularUseCaseImpl() {
        setRepo(repoUC);
    }

}

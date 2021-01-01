/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.jhw.module.gestion.contabilidad.consume.module.ContabilidadConsumeCoreModule;
import com.jhw.module.gestion.contabilidad.consume.repo_impl.*;
import com.jhw.module.gestion.contabilidad.consume.usecase_def.*;
import com.jhw.module.gestion.contabilidad.core.domain.*;
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class OperacionContableUseCaseImpl extends DefaultCRUDUseCase<OperacionContableDomain> implements OperacionContableUseCaseConsume {

    private final OperacionContableRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(OperacionContableRepoImpl.class);

    public OperacionContableUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<OperacionContableDomain> findAll(CuentaContableDomain cuenta) throws Exception {
        return repoUC.findAll(cuenta);
    }

    @Override
    public List<OperacionContableDomain> findAll(Integer idCuentaContable) throws Exception {
        return repoUC.findAll(idCuentaContable);
    }

}

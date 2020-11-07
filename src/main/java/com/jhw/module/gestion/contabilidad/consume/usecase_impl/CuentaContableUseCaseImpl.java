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
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CuentaContableUseCaseImpl extends DefaultCRUDUseCase<CuentaContableDomain> implements CuentaContableUseCaseConsume {

    private final CuentaContableRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaContableRepoImpl.class);

    public CuentaContableUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<Cuenta> findAllCuentas() throws Exception {
        return repoUC.findAllCuentas();
    }

    @Override
    public List<CuentaContableDomain> findAllCuenta(TipoCuentaDomain tipo) throws Exception {
        return repoUC.findAllCuenta(tipo);
    }

    @Override
    public List<CuentaContableDomain> findAllCuenta(Integer idTipoCuenta) throws Exception {
        return repoUC.findAllCuenta(idTipoCuenta);
    }

    @Override
    public List<CuentaContableDomain> findAll(String text) throws Exception {
        return repoUC.findAll(text);
    }

}

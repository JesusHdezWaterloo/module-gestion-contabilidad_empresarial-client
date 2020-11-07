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
public class CuentaBancariaUseCaseImpl extends DefaultCRUDUseCase<CuentaBancariaDomain> implements CuentaBancariaUseCaseConsume {

    private final CuentaBancariaRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaBancariaRepoImpl.class);

    public CuentaBancariaUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<Cuenta> findAllCuentas() throws Exception {
        return repoUC.findAllCuentas();
    }

    @Override
    public CuentaBancariaDomain findCuentaDefault(MonedaDomain moneda) throws Exception {
        return repoUC.findCuentaDefault(moneda);
    }

    @Override
    public CuentaBancariaDomain findCuentaDefault(Integer idMoneda) throws Exception {
        return repoUC.findCuentaDefault(idMoneda);
    }

    @Override
    public List<CuentaBancariaDomain> findAll(String searchText) throws Exception {
        return repoUC.findAll(searchText);
    }
}

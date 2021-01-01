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
public class LiquidacionUseCaseImpl extends DefaultCRUDUseCase<LiquidacionDomain> implements LiquidacionUseCaseConsume {

    private final LiquidacionRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(LiquidacionRepoImpl.class);

    public LiquidacionUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<LiquidacionDomain> findAll(CuentaBancariaDomain cuenta) throws Exception {
        return repoUC.findAll(cuenta);
    }

    @Override
    public List<LiquidacionDomain> findAll(Integer IdCuentaBancaria) throws Exception {
        return repoUC.findAll(IdCuentaBancaria);
    }

    @Override
    public LiquidacionDomain getLiquidacion(CuadreDomain cuadre) throws Exception {
        return repoUC.getLiquidacion(cuadre);
    }

    @Override
    public LiquidacionDomain getLiquidacion(Integer idCuadre) throws Exception {
        return repoUC.getLiquidacion(idCuadre);
    }

}

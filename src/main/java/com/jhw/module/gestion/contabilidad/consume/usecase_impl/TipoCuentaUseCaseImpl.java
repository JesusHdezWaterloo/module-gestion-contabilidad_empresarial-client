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
public class TipoCuentaUseCaseImpl extends DefaultCRUDUseCase<TipoCuentaDomain> implements TipoCuentaUseCaseConsume {

    private final TipoCuentaRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TipoCuentaRepoImpl.class);

    public TipoCuentaUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<TipoCuentaDomain> findAllEquivalent(TipoCuentaDomain selectedItem) throws Exception {
        return repoUC.findAllEquivalent(selectedItem);
    }

    @Override
    public List<TipoCuentaDomain> findAllEquivalent(Integer idTipoCuenta) throws Exception {
        return repoUC.findAllEquivalent(idTipoCuenta);
    }

}

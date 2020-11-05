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
public class FormaPagoUseCaseImpl extends DefaultCRUDUseCase<FormaPagoDomain> implements FormaPagoUseCaseConsume {

    private final FormaPagoRepoImpl repoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(FormaPagoRepoImpl.class);

    public FormaPagoUseCaseImpl() {
        setRepo(repoUC);
    }

}

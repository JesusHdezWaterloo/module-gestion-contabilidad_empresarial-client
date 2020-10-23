/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.usecase_impl;

import com.clean.core.app.usecase.*;
import com.jhw.module.gestion.contabilidad.consume.module.SpringAConsumeCoreModule;
import com.jhw.module.gestion.contabilidad.consume.repo_impl.TrabajoRepoImpl;
import com.jhw.example.spring_a.core.domain.TrabajoDomain;
import com.jhw.example.spring_a.core.usecase_def.TrabajoUseCase;
import java.util.List;

/**
 * <Delegate extends ConsumerRepoTemplate<TrabajoDomain> & TrabajoUseCase>
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TrabajoUseCaseImpl extends DefaultCRUDUseCase<TrabajoDomain> implements TrabajoUseCase {

    private final TrabajoRepoImpl repoUC = SpringAConsumeCoreModule.getInstance().getImplementation(TrabajoRepoImpl.class);

    public TrabajoUseCaseImpl() {
        setRepo(repoUC);
    }

    @Override
    public List<TrabajoDomain> upperBasico() throws Exception {
        return repoUC.upperBasico();
    }

}

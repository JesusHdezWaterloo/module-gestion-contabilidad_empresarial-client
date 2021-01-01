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
package com.jhw.module.gestion.contabilidad.ui.cuadre;

import com.root101.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.jhw.module.gestion.contabilidad.ui.cuadre.pedazos.OperacionCuadreInputView;
import com.root101.module.gestion.contabilidad.core.domain.facade.CuadreUI;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.root101.module.gestion.contabilidad.core.domain.facade.DocNombreUI;
import com.root101.module.gestion.contabilidad.core.domain.facade.FechaDescUI;
import com.root101.module.gestion.contabilidad.core.domain.facade.OperacionCuadreUI;
import com.jhw.module.gestion.contabilidad.ui.cuadre.pedazos.DocNombreInputView;
import com.jhw.module.gestion.contabilidad.ui.cuadre.pedazos.FechaDescInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuadreInputView extends CleanCRUDInputView<CuadreDomain> {

    public static CuadreInputView from() {
        return new CuadreInputView(null);
    }

    public static CuadreInputView fromModel(CuadreDomain model) {
        return new CuadreInputView(model);
    }

    private CuadreInputView(CuadreDomain model) {
        super(model, ContabilidadSwingModule.cuadreUC);
        initComponents();
        update();
    }

    private void initComponents() {
        //doc, nombre ....
        docNombreInputView = DocNombreInputView.from();

        //valor, cuentas ....//TODO
        //operacionInputView = OperacionCuadreInputView.fromTipoOp(TipoOperacionContableUseCaseImpl.MOVIMIENTO_INTERNO);
        operacionInputView = OperacionCuadreInputView.from();

        //fecha, desc ....
        fechaDescInputView = FechaDescInputView.from();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder(400);
        vlc.add(docNombreInputView);
        vlc.add(operacionInputView);
        vlc.add(fechaDescInputView, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private DocNombreInputView docNombreInputView;
    private OperacionCuadreInputView operacionInputView;
    private FechaDescInputView fechaDescInputView;
    // End of variables declaration                   

    @Override
    public void update() {
        if (getOldModel() == null) {
            setHeader("Crear Cuadre");
        } else {
            setHeader("Editar Cuadre");
            docNombreInputView.setObject(new DocNombreUI(getOldModel().info()));
            fechaDescInputView.setObject(new FechaDescUI(getOldModel().info()));
            operacionInputView.setObject(new OperacionCuadreUI(getOldModel()));
        }
    }

    @Override
    public CuadreDomain getNewModel() throws Exception {
        DocNombreUI docNombre = docNombreInputView.getNewModel();
        OperacionCuadreUI op = operacionInputView.getNewModel();
        FechaDescUI fechaDesc = fechaDescInputView.getNewModel();

        CuadreUI cuadre = new CuadreUI(docNombre, op, fechaDesc);
        if (getOldModel() == null) {
            return cuadre.buildCuadre();
        } else {
            CuadreDomain cuadreDomain = cuadre.buildCuadre();;
            cuadreDomain.setIdCuadre(getOldModel().getIdCuadre());
            return cuadreDomain;
        }
    }

}

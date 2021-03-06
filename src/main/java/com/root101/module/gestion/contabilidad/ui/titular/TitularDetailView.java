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
package com.root101.module.gestion.contabilidad.ui.titular;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.TitularDomain;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.root101.swing.material.components.table.Column;
import com.root101.swing.models.input.dialogs.DialogModelInput;
import com.root101.swing.models.detail._MaterialPanelDetail;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TitularDetailView extends _MaterialPanelDetail<TitularDomain> {

    private static final String COL_NOMBRE = "Titular";
    private static final String COL_DESC = "Descripción";

    public TitularDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DESC).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Titulares de cuenta");
        this.setIcon(ContabilidadModuleNavigator.ICON_TITULAR);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.titularUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(TitularDomain obj) {
        return new Object[]{obj.getNombreTitular(),
            obj.getDescripcion()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, TitularInputView.from());
    }

    @Override
    protected TitularDomain deleteAction(TitularDomain obj) {
        try {
            return ContabilidadSwingModule.titularUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(TitularDomain obj) {
        new DialogModelInput(this, TitularInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(TitularDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

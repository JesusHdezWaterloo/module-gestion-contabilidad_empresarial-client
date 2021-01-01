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
package com.jhw.module.gestion.contabilidad.ui.forma_pago;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.FormaPagoDomain;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class FormaPagoDetailView extends _MaterialPanelDetail<FormaPagoDomain> {

    private static final String COL_PAGO = "Pago";
    private static final String COL_DESC = "Descripción";

    public FormaPagoDetailView() {
        super(
                Column.builder().name(COL_PAGO).build(),
                Column.builder().name(COL_DESC).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Forma de Pago");
        this.setIcon(ContabilidadModuleNavigator.ICON_FORMA_PAGO);
        
        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.formaPagoUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(FormaPagoDomain obj) {
        return new Object[]{obj.getNombreFormaPago(), obj.getDescripcion()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, FormaPagoInputView.from());
    }

    @Override
    protected FormaPagoDomain deleteAction(FormaPagoDomain obj) {
        try {
            return ContabilidadSwingModule.formaPagoUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(FormaPagoDomain obj) {
        new DialogModelInput(this, FormaPagoInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(FormaPagoDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

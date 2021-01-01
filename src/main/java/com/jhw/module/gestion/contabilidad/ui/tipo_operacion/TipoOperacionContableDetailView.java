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
package com.jhw.module.gestion.contabilidad.ui.tipo_operacion;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.TipoOperacionContableDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TipoOperacionContableDetailView extends _MaterialPanelDetail<TipoOperacionContableDomain> {

    private static final String COL_NOMBRE = "Tipo";
    private static final String COL_DESC = "Descripción";

    public TipoOperacionContableDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DESC).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Tipos de operaciones");
        this.setIcon(ContabilidadModuleNavigator.ICON_TIPO_OPERACION);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.tipoOperacionContableUC.findAll());
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    @Override
    public Object[] getRowObject(TipoOperacionContableDomain obj) {
        return new Object[]{
            obj.getNombreOperacion(),
            obj.getDescripcion()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, TipoOperacionContableInputView.from());
    }

    @Override
    protected TipoOperacionContableDomain deleteAction(TipoOperacionContableDomain obj) {
        try {
            return ContabilidadSwingModule.tipoOperacionContableUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(TipoOperacionContableDomain obj) {
        new DialogModelInput(this, TipoOperacionContableInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(TipoOperacionContableDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

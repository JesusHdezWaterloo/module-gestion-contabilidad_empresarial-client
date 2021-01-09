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
package com.root101.module.gestion.contabilidad.ui.tipo_cuenta;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
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
public class TipoCuentaDetailView extends _MaterialPanelDetail<TipoCuentaDomain> {

    private static final String COL_NOMBRE = "Tipo";
    private static final String COL_DEB_CRED = "Débito - Crédito";
    private static final String COL_LIQUIDABLE = "Liquidable";

    public TipoCuentaDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DEB_CRED).build(),
                Column.builder().name(COL_LIQUIDABLE).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Tipos de Cuenta");
        this.setIcon(ContabilidadModuleNavigator.ICON_TIPO_CUENTA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.tipoCuentaUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(TipoCuentaDomain obj) {
        return new Object[]{
            obj.getNombreTipoCuenta(),
            obj.debito_credito(),
            obj.liquidable()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, TipoCuentaInputView.from());
    }

    @Override
    protected TipoCuentaDomain deleteAction(TipoCuentaDomain obj) {
        try {
            return ContabilidadSwingModule.tipoCuentaUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(TipoCuentaDomain obj) {
        new DialogModelInput(this, TipoCuentaInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(TipoCuentaDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

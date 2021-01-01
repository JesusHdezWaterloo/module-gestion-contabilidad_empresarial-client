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
package com.jhw.module.gestion.contabilidad.ui.moneda;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyCellRender;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyTableComponent;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class MonedaDetailView extends _MaterialPanelDetail<MonedaDomain> {

    private static final String COL_MONEDA = "Moneda";
    private static final String COL_COMPRA = "Compra";
    private static final String COL_VENTA = "Venta";

    public MonedaDetailView() {
        super(
                Column.builder().name(COL_MONEDA).build(),
                Column.builder().name(COL_COMPRA).build(),
                Column.builder().name(COL_VENTA).build()
        );

        this.personalize();
    }

    private void personalize() {
        setUpEditorsRenders();

        this.setHeaderText("Monedas");
        this.setIcon(ContabilidadModuleNavigator.ICON_MONEDA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.monedaUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(MonedaDomain obj) {
        return new Object[]{
            obj.getNombreMoneda(),
            MoneyTableComponent.from(obj.getCompra(), ContabilidadSwingModule.monedaUC.getMonedaBase()),
            MoneyTableComponent.from(obj.getVenta(), ContabilidadSwingModule.monedaUC.getMonedaBase())};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, MonedaInputView.from());
    }

    @Override
    protected MonedaDomain deleteAction(MonedaDomain obj) {
        try {
            return ContabilidadSwingModule.monedaUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(MonedaDomain obj) {
        new DialogModelInput(this, MonedaInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(MonedaDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

    private void setUpEditorsRenders() {
        getTable().getColumn(COL_COMPRA).setCellRenderer(new MoneyCellRender());
        getTable().getColumn(COL_VENTA).setCellRenderer(new MoneyCellRender());
    }
}

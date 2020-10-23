/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.ui.cuenta_contable;

import com.jhw.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.module.gestion.contabilidad.ui.cuenta.CuentaSinglePanel;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.gestion.contabilidad.ui.operacion_contable.OperacionContableDetailView;
import com.jhw.swing.models.detail.DialogDetail;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.utils.UpdateListener;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CuentaContableSinglePanel extends CuentaSinglePanel {

    private final UpdateListener updList = new UpdateListener(this) {
        @Override
        public String[] propertiesToListenFor() {
            return new String[]{"edit"};
        }
    };
    private final CuentaContableDomain cuenta;

    public CuentaContableSinglePanel(CuentaContableDomain cuenta) {
        super(cuenta);
        this.cuenta = cuenta;
        addPropertyChange();
    }

    @Override
    protected void viewAction() {
        new DialogDetail(this, "Operaciones", new OperacionContableDetailView(cuenta));
    }

    @Override
    protected void editAction() {
        DialogModelInput.from(CuentaContableInputView.fromModel(cuenta));
    }

    private void addPropertyChange() {
        ContabilidadSwingModule.cuentaContableUC.addPropertyChangeListener(updList);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.ui.cuenta_contable;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.module.gestion.contabilidad.ui.cuenta.CuentaDetailMainPanel;
import com.jhw.module.gestion.contabilidad.ui.cuenta.CuentaSinglePanel;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.utils.UpdateListener;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CuentaContableDetailMainPanel extends CuentaDetailMainPanel<CuentaContableDomain> {

    private final UpdateListener updList = new UpdateListener(this) {
        @Override
        public String[] propertiesToListenFor() {
            return new String[]{"create", "destroy", "destroyById"};
        }
    };

    public CuentaContableDetailMainPanel() {
        setHeader("Cuentas Contables");
        setIcon(ContabilidadModuleNavigator.ICON_CUENTA_CONTABLE);
        addPropertyChange();
    }

    @Override
    public void createAction() {
        DialogModelInput.from(CuentaContableInputView.from());
    }

    @Override
    public void update() {
        try {
            rellenarCuentas(ContabilidadSwingModule.cuentaContableUC.findAll(getSearchText()));
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    protected CuentaSinglePanel buildSingle(CuentaContableDomain cuenta) {
        return new CuentaContableSinglePanel(cuenta);
    }

    private void addPropertyChange() {
        ContabilidadSwingModule.cuentaContableUC.addPropertyChangeListener(updList);
        ContabilidadSwingModule.cuadreUC.addPropertyChangeListener(updList);
        ContabilidadSwingModule.liquicadionUC.addPropertyChangeListener(updList);
    }
}

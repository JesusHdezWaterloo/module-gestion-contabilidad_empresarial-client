/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.ui.cuenta_bancaria;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.CuentaBancariaDomain;
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
public class CuentaBancariaDetailMainPanel extends CuentaDetailMainPanel<CuentaBancariaDomain> {

    private final UpdateListener updList = new UpdateListener(this) {
        @Override
        public String[] propertiesToListenFor() {
            return new String[]{"create", "destroy", "destroyById"};
        }
    };

    public CuentaBancariaDetailMainPanel() {
        setHeader("Cuentas Bancarias");
        setIcon(ContabilidadModuleNavigator.ICON_CUENTA_BANCARIA);
        addPropertyChange();
    }

    @Override
    public void createAction() {
        DialogModelInput.from(CuentaBancariaInputView.from());
    }

    @Override
    public void update() {
        try {
            rellenarCuentas(ContabilidadSwingModule.cuentaBancariaUC.findAll(getSearchText()));
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    protected CuentaSinglePanel buildSingle(CuentaBancariaDomain cuenta) {
        return new CuentaBancariaSinglePanel(cuenta);
    }

    private void addPropertyChange() {
        ContabilidadSwingModule.cuentaBancariaUC.addPropertyChangeListener(updList);
        ContabilidadSwingModule.liquicadionUC.addPropertyChangeListener(updList);
    }
}

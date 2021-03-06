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
package com.root101.module.gestion.contabilidad.ui.cuenta_bancaria;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.module.gestion.contabilidad.core.domain.CuentaBancariaDomain;
import com.root101.module.gestion.contabilidad.ui.cuenta.CuentaDetailMainPanel;
import com.root101.module.gestion.contabilidad.ui.cuenta.CuentaSinglePanel;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.root101.swing.models.input.dialogs.DialogModelInput;
import com.root101.swing.models.utils.UpdateListener;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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

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
package com.jhw.module.gestion.contabilidad.ui.cuenta_contable;

import com.root101.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.module.gestion.contabilidad.ui.cuenta.CuentaSinglePanel;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.gestion.contabilidad.ui.operacion_contable.OperacionContableDetailView;
import com.jhw.swing.models.detail.DialogDetail;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.utils.UpdateListener;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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

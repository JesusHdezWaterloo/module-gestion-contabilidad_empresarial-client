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
package com.root101.module.gestion.contabilidad.ui.cuenta_contable;

import com.root101.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.root101.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.root101.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuentaContableICBS extends InputComboBoxSelection<CuentaContableDomain> {

    private List<CuentaContableDomain> actualList;

    public CuentaContableICBS() {
        setLabel("Cuenta Contable");
        setIcon(ContabilidadModuleNavigator.ICON_CUENTA_CONTABLE);
    }

    @Override
    public List<CuentaContableDomain> getList() throws Exception{
        actualList = ContabilidadSwingModule.cuentaContableUC.findAll();
        return actualList;
    }

    @Override
    public ModelPanel<CuentaContableDomain> inputPanel() {
        return CuentaContableInputView.from();
    }
    
    public void setMatchingItem(TipoCuentaDomain tipo, MonedaDomain moneda) {
        for (CuentaContableDomain c : actualList) {
            if (c.getTipoCuentaFk().equals(tipo) && c.getMonedaFk().equals(moneda)) {
                setObject(c);
                break;
            }
        }
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.cuentaContableUC.addPropertyChangeListener(this);
    }
}

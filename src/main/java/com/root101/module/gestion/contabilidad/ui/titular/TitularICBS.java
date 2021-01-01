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
package com.root101.module.gestion.contabilidad.ui.titular;

import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.root101.module.gestion.contabilidad.core.domain.TitularDomain;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TitularICBS extends InputComboBoxSelection<TitularDomain> {

    public TitularICBS() {
        setLabel("Titular");
        setIcon(ContabilidadModuleNavigator.ICON_TITULAR);
    }

    @Override
    public List<TitularDomain> getList() throws Exception{
        return ContabilidadSwingModule.titularUC.findAll();
    }

    @Override
    public ModelPanel<TitularDomain> inputPanel() {
        return TitularInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.titularUC.addPropertyChangeListener(this);
    }
}

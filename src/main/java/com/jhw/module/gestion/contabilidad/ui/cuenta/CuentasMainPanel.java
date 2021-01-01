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
package com.jhw.module.gestion.contabilidad.ui.cuenta;

import com.jhw.module.gestion.contabilidad.ui.cuenta_bancaria.CuentaBancariaDetailMainPanel;
import com.jhw.module.gestion.contabilidad.ui.cuenta_contable.CuentaContableDetailMainPanel;
import com.jhw.swing.material.components.container.panel._PanelTransparent;
import com.jhw.swing.material.components.scrollpane.SmoothScrollMouseWheelListener;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuentasMainPanel extends _PanelTransparent implements Update {

    public CuentasMainPanel() {
        initComponents();
        update();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        _PanelTransparent background = new _PanelTransparent();
        background.setLayout(new BorderLayout());

        //scroll clasico que se ve mejor
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().setOpaque(false);
        scroll.addMouseWheelListener(new SmoothScrollMouseWheelListener(scroll));
        
        scroll.setViewportView(background);
        this.add(scroll);

        //this.add(background);
        cuentasBancarias = new CuentaBancariaDetailMainPanel();
        background.add(cuentasBancarias, BorderLayout.NORTH);

        cuentasContables = new CuentaContableDetailMainPanel();
        background.add(cuentasContables, BorderLayout.CENTER);
    }
    private CuentaBancariaDetailMainPanel cuentasBancarias;
    private CuentaContableDetailMainPanel cuentasContables;

    @Override
    public void update() {
        cuentasBancarias.update();
        cuentasContables.update();
    }
}

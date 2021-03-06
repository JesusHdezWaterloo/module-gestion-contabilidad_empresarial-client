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
package com.root101.module.gestion.contabilidad.ui.cuenta;

import com.root101.module.gestion.contabilidad.core.domain.Cuenta;
import com.root101.swing.material.components.button.MaterialButton;
import com.root101.swing.material.components.button.MaterialButtonIcon;
import com.root101.swing.material.components.button.MaterialButtonsFactory;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.layout.VerticalLayoutComponent;
import com.root101.swing.material.components.container.layout.VerticalLayoutContainer;
import com.root101.swing.material.components.container.panel._MaterialPanel;
import com.root101.swing.material.components.container.panel._PanelTransparent;
import com.root101.swing.material.components.labels.MaterialLabel;
import com.root101.swing.material.components.labels.MaterialLabelDobleMoney;
import com.root101.swing.material.components.labels.MaterialLabelsFactory;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialFontRoboto;
import com.root101.swing.material.standards.MaterialIcons;
import com.root101.swing.material.standards.MaterialShadow;
import com.root101.swing.prepared.button.MaterialPreparedButtonsFactory;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public abstract class CuentaSinglePanel extends _MaterialPanel implements Update {

    private final Cuenta cuenta;

    public CuentaSinglePanel(Cuenta cuenta) {
        this.cuenta = cuenta;
        initComponents();
        addListeners();
        update();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        this.setBackground(MaterialColors.GREY_100);

        this.setBorder(new EmptyBorder(
                MaterialShadow.OFFSET_TOP,
                MaterialShadow.OFFSET_LEFT,
                MaterialShadow.OFFSET_BOTTOM,
                MaterialShadow.OFFSET_RIGHT));

        //actions
        _PanelTransparent actions = new _PanelTransparent();
        actions.setLayout(new BorderLayout());

        buttonEdit = MaterialButtonsFactory.buildIconTransparent();
        buttonEdit.setIcon(MaterialIcons.EDIT);
        buttonEdit.setRippleColor(MaterialColors.BLUEA_400);
        actions.add(buttonEdit, BorderLayout.WEST);

        buttonView = MaterialPreparedButtonsFactory.buildView();
        buttonView.setText("Detalles");
        actions.add(buttonView, BorderLayout.EAST);
        this.add(actions, BorderLayout.SOUTH);

        //background
        JPanel background = MaterialContainersFactory.buildPanelTransparent();
        background.setLayout(new BorderLayout());
        background.setBorder(new EmptyBorder(5, 10, 0, 10));

        //nombre
        labelNombreCuenta = MaterialLabelsFactory.build();
        labelNombreCuenta.setFont(MaterialFontRoboto.BOLD.deriveFont(24f));
        background.add(labelNombreCuenta, BorderLayout.NORTH);

        debito = MaterialLabelsFactory.buildDoubleMoneyPositive();
        debito.setText("Débito");

        credito = MaterialLabelsFactory.buildDoubleMoneyNegative();
        credito.setText("Crédito");

        saldo = MaterialLabelsFactory.buildDoubleMoney();
        saldo.setText("Saldo");

        //center
        VerticalLayoutContainer.builder center = VerticalLayoutContainer.builder();
        center.add(VerticalLayoutComponent.builder(debito).gapTop(5).build());
        center.add(credito, true);
        center.add(saldo);

        background.add(center.build());

        this.add(background);
    }

    private MaterialLabel labelNombreCuenta;
    private MaterialLabelDobleMoney debito;
    private MaterialLabelDobleMoney credito;
    private MaterialLabelDobleMoney saldo;
    private MaterialButton buttonView;
    private MaterialButtonIcon buttonEdit;

    private void addListeners() {
        buttonEdit.addActionListener((ActionEvent e) -> {
            editAction();
        });
        buttonView.addActionListener((ActionEvent e) -> {
            viewAction();
        });
    }

    protected abstract void editAction();

    protected abstract void viewAction();

    @Override
    public void update() {
        this.labelNombreCuenta.setText(cuenta.toString());
        this.debito.setMoney(cuenta.getDebito(), cuenta.getMonedaFk());
        this.credito.setMoney(cuenta.getCredito(), cuenta.getMonedaFk());
        this.saldo.setMoney(cuenta.saldo(), cuenta.getMonedaFk());
        this.setToolTipText(cuenta.getDescripcion());
    }

}

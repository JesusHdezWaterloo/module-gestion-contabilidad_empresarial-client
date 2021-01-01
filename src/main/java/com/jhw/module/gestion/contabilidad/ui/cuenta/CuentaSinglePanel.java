/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.ui.cuenta;

import com.jhw.module.gestion.contabilidad.core.domain.Cuenta;
import com.jhw.swing.material.components.button.MaterialButton;
import com.jhw.swing.material.components.button.MaterialButtonIcon;
import com.jhw.swing.material.components.button.MaterialButtonsFactory;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import com.jhw.swing.material.components.container.layout.VerticalLayoutComponent;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.material.components.container.panel._MaterialPanel;
import com.jhw.swing.material.components.container.panel._PanelTransparent;
import com.jhw.swing.material.components.labels.MaterialLabel;
import com.jhw.swing.material.components.labels.MaterialLabelDobleMoney;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialFontRoboto;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.material.standards.MaterialShadow;
import com.jhw.swing.prepared.button.MaterialPreparedButtonsFactory;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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

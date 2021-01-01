/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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

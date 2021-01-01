/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.ui.cuenta;

import com.jhw.module.gestion.contabilidad.core.domain.Cuenta;
import com.jhw.swing.material.components.container.panel._MaterialPanel;
import com.jhw.swing.material.components.container.panel._PanelTransparent;
import com.jhw.swing.material.components.scrollpane._MaterialScrollPaneCore;
import com.jhw.swing.material.standards.MaterialShadow;
import com.jhw.swing.models.detail.HeaderDetailPanel;
import com.root101.utils.interfaces.Update;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public abstract class CuentaDetailMainPanel<T extends Cuenta> extends _MaterialPanel implements Update {

    public CuentaDetailMainPanel() {
        initComponents();
        addListeners();
    }

    private void initComponents() {
        this.setBorder(new EmptyBorder(
                MaterialShadow.OFFSET_TOP + 10,
                MaterialShadow.OFFSET_LEFT + 10,
                MaterialShadow.OFFSET_BOTTOM + 10,
                MaterialShadow.OFFSET_RIGHT + 10));

        this.setLayout(new BorderLayout());

        header = new HeaderDetailPanel();
        panelCuentasSingle = new _PanelTransparent();
        MigLayout mig = new MigLayout(
                new LC().align("center", "center").insetsAll("0").gridGap("0", "0"),
                new AC(),
                new AC().fill().grow()
        );
        panelCuentasSingle.setLayout(mig);

        this.add(header, BorderLayout.NORTH);

        //panel cuentas
        _MaterialScrollPaneCore scroll = new _MaterialScrollPaneCore();
        scroll.remove(scroll.getHorizontalScrollBar());
        scroll.setViewportView(panelCuentasSingle);
        this.add(scroll, BorderLayout.SOUTH);

        //this.add(panelCuentasSingle);
        //this.add(panelCuentasSingle,BorderLayout.SOUTH);
    }

    private HeaderDetailPanel header;
    private _PanelTransparent panelCuentasSingle;

    public void setIcon(ImageIcon icon) {
        header.setIcon(icon);
    }

    protected String getSearchText() {
        return header.getSearchText();
    }

    public void setHeader(String text) {
        header.setHeaderText(text);
    }

    public void rellenarCuentas(List<T> cuentas) {
        panelCuentasSingle.removeAll();
        for (int i = 0; i < cuentas.size() / 2; i++) {
            panelCuentasSingle.add(buildSingle(cuentas.get(2 * i)), "grow, push, newline");
            panelCuentasSingle.add(buildSingle(cuentas.get(2 * i + 1)), "grow, push");
        }
        if (cuentas.size() % 2 != 0 && cuentas.size() >= 0) {
            panelCuentasSingle.add(buildSingle(cuentas.get(cuentas.size() - 1)), "grow, push,  newline, spanx 2");
        }

        this.revalidate();
    }

    protected abstract CuentaSinglePanel buildSingle(T cuenta);

    private void addListeners() {
        header.addButtonNuevoActionListener((ActionEvent e) -> {
            createAction();
        });
        header.setSearchActionListener((ActionEvent e) -> {
            update();
        });
    }

    public abstract void createAction();

}

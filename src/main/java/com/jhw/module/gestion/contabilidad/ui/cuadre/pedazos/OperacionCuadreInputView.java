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
package com.jhw.module.gestion.contabilidad.ui.cuadre.pedazos;

import com.root101.module.gestion.contabilidad.core.domain.TipoOperacionContableDomain;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.root101.module.gestion.contabilidad.core.domain.facade.OperacionCuadreUI;
import com.jhw.module.gestion.contabilidad.ui.cuenta_contable.CuentaContableICBS;
import com.jhw.module.gestion.contabilidad.ui.moneda.MonedaICBS;
import com.jhw.module.gestion.contabilidad.ui.tipo_operacion.TipoOperacionContableICBS;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutComponent;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.jhw.swing.material.components.textfield.MaterialFormatedTextFieldIcon;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.prepared.textfield.MaterialPreparedTextFactory;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class OperacionCuadreInputView extends CleanCRUDInputView<OperacionCuadreUI> {

    public static OperacionCuadreInputView from() {
        return new OperacionCuadreInputView((OperacionCuadreUI) null);
    }

    public static OperacionCuadreInputView fromTipoOp(TipoOperacionContableDomain opDefecto) {
        return new OperacionCuadreInputView(opDefecto);
    }

    private OperacionCuadreInputView(TipoOperacionContableDomain opDefecto) {
        this((OperacionCuadreUI) null);
        setTipoOp(opDefecto);
    }

    public OperacionCuadreInputView(OperacionCuadreUI model) {
        super(model, null, OperacionCuadreUI.class);
        initComponents();
        addListeners();
        update();
    }

    private void initComponents() {
        textFieldValor = MaterialPreparedTextFactory.buildFormatedMoneyIcon(true);
        textFieldValor.setLabel("Valor");
        textFieldValor.setHint("Valor de la operación");
        textFieldValor.setIcon(MaterialIcons.ATTACH_MONEY);

        moneda = new MonedaICBS();
        tipoOperICBS = new TipoOperacionContableICBS();

        //cuenta
        cuentaICBS = new CuentaContableICBS();
        cuentaICBS.setLabel("Cuenta Inicial");

        //cuadre
        cuentaCuadreICBS = new CuentaContableICBS();
        cuentaCuadreICBS.setLabel("Cuenta Cuadre");

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();

        HorizontalLayoutContainer.builder hlcDebito = HorizontalLayoutContainer.builder((int) textFieldValor.getPreferredSize().getHeight());
        hlcDebito.add(HorizontalLayoutComponent.builder(textFieldValor).gapRight(5).build());
        hlcDebito.add(HorizontalLayoutComponent.builder(moneda).gapLeft(5).build());
        vlc.add(hlcDebito.build());

        vlc.add(cuentaICBS);
        vlc.add(cuentaCuadreICBS);
        vlc.add(tipoOperICBS);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialFormatedTextFieldIcon textFieldValor;
    private MonedaICBS moneda;
    private CuentaContableICBS cuentaICBS;
    private CuentaContableICBS cuentaCuadreICBS;
    private TipoOperacionContableICBS tipoOperICBS;
    // End of variables declaration                   

    @Override
    protected boolean doValidate() {
        return true;
    }

    @Override
    public void setObject(OperacionCuadreUI t) {
        setOldModel(t);
        update();
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("valor", textFieldValor);
        bindFields.put("moneda", moneda);
        bindFields.put("cuenta", cuentaICBS);
        bindFields.put("cuadre", cuentaCuadreICBS);
        bindFields.put("tipoOp", tipoOperICBS);
        return bindFields;
    }

    private void addListeners() {
        tipoOperICBS.addActionListener((ActionEvent e) -> {
            changeTipoOp();
        });
    }

    public void setTipoOp(TipoOperacionContableDomain opDefecto) {
        if (opDefecto != null) {
            try {
                this.cuentaICBS.setMatchingItem(opDefecto.getTipoCuentaDefectoFk(), moneda.getObject());

                this.cuentaCuadreICBS.setMatchingItem(opDefecto.getTipoCuentaCuadreDefectoFk(), moneda.getObject());

                tipoOperICBS.setObject(opDefecto);
            } catch (Exception e) {
            }
        }
    }

    public TipoOperacionContableICBS getTipoOperICBS() {
        return tipoOperICBS;
    }

    public MonedaICBS getMoneda() {
        return moneda;
    }

    public CuentaContableICBS getCuentaICBS() {
        return cuentaICBS;
    }

    public CuentaContableICBS getCuentaCuadreICBS() {
        return cuentaCuadreICBS;
    }

    public MaterialFormatedTextFieldIcon getTextFieldValor() {
        return textFieldValor;
    }

    private void changeTipoOp() {
        try {
            setTipoOp(tipoOperICBS.getObject());
        } catch (Exception e) {
        }
    }
}

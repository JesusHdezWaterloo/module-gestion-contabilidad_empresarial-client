package com.jhw.module.gestion.contabilidad.ui.liquidacion;

import com.jhw.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.jhw.module.gestion.contabilidad.core.domain.LiquidacionDomain;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.ui.cuadre.CuadreICBS;
import com.jhw.module.gestion.contabilidad.ui.cuenta_bancaria.CuentaBancariaICBS;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.datepicker._MaterialDatePickerIcon;
import com.jhw.swing.material.components.labels.MaterialLabelDobleMoney;
import com.jhw.swing.material.components.labels.MaterialLabelsFactory;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class LiquidacionInputView extends CleanCRUDInputView<LiquidacionDomain> {

    public static LiquidacionInputView fromBase(LiquidacionDomain base) {
        return new LiquidacionInputView(base, null);
    }

    public static LiquidacionInputView fromModel(LiquidacionDomain model) {
        return new LiquidacionInputView(null, model);
    }

    public static LiquidacionInputView from() {
        return new LiquidacionInputView(null, null);
    }

    private LiquidacionInputView(LiquidacionDomain base, LiquidacionDomain model) {
        super(model, ContabilidadSwingModule.liquicadionUC, LiquidacionDomain.class);
        setBase(base);
        initComponents();
        addListeners();
        update();
    }

    private void initComponents() {
        setHeader("Crear Liquidación", "Editar Liquidación");

        //nombre
        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setHint("Nombre");
        textFieldNombre.setLabel("Nombre de la Liquidación");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);

        //documento
        textFieldDocumento= MaterialTextFactory.buildIcon();
        textFieldDocumento.setLabel("Documento");
        textFieldDocumento.setHint("Factura o Transacción asociada");
        textFieldDocumento.setIcon(MaterialIcons.DRAFTS);

        //debito
        labelDebitoValue = MaterialLabelsFactory.buildDoubleMoneyPositive();
        labelDebitoValue.setText("Débito");

        //credito
        labelCreditoValue  = MaterialLabelsFactory.buildDoubleMoneyNegative();
        labelCreditoValue.setText("Crédito");

        //fecha
        datePickerFecha = new _MaterialDatePickerIcon();
        datePickerFecha.setLabel("Fecha");

        //cuenta
        cuentaICBS = new CuentaBancariaICBS();

        //cuadre
        cuadreICBS = new CuadreICBS();

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldNombre);
        vlc.add(textFieldDocumento);

        vlc.add(labelDebitoValue);
        vlc.add(labelCreditoValue);

        vlc.add(datePickerFecha, true);
        vlc.add(cuadreICBS, true);
        vlc.add(cuentaICBS, true);

        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldDocumento;
    private MaterialTextFieldIcon textFieldNombre;
    private MaterialLabelDobleMoney labelDebitoValue;
    private MaterialLabelDobleMoney labelCreditoValue;
    private _MaterialDatePickerIcon datePickerFecha;
    private CuentaBancariaICBS cuentaICBS;
    private CuadreICBS cuadreICBS;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public void update() {
        super.update();
        cuadreICBS.setEnabled(getBase() == null);
        if (getBase() == null) {
            labelCreditoValue.setMoney(BigDecimal.ZERO, "");//en el edit se actualizan por el get selected del cuadreICBS
            labelDebitoValue.setMoney(BigDecimal.ZERO, "");
        }
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("documento", textFieldDocumento);
        bindFields.put("nombre", textFieldNombre);
        bindFields.put("fecha", datePickerFecha);
        bindFields.put("cuentaFk", cuentaICBS);
        bindFields.put("cuadreFk", cuadreICBS);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }

    private void addListeners() {
        cuadreICBS.addActionListener((ActionEvent e) -> {
            onCuadreICBSActionPerformed();
        });
    }

    private void onCuadreICBSActionPerformed() {
        try {
            CuadreDomain cuadre = cuadreICBS.getObject();
            labelCreditoValue.setMoney(cuadre.getOperacionContableFk().getDebito(), cuadre.getOperacionContableFk().getCuentaFk().getMonedaFk());
            labelDebitoValue.setMoney(cuadre.getOperacionContableFk().getCredito(), cuadre.getOperacionContableFk().getCuentaFk().getMonedaFk());
        } catch (Exception e) {
        }
    }
}

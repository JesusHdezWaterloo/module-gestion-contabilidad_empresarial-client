package com.jhw.module.gestion.contabilidad.ui.moneda;

import com.jhw.swing.material.components.container.layout.HorizontalLayoutComponent;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.gestion.contabilidad.utils.MonedaHandler;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialFormatedTextFieldIcon;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.prepared.textfield.MaterialPreparedTextFactory;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class MonedaInputView extends CleanCRUDInputView<MonedaDomain> {

    public static MonedaInputView from() {
        return new MonedaInputView(null);
    }

    public static MonedaInputView fromModel(MonedaDomain model) {
        return new MonedaInputView(model);
    }

    private MonedaInputView(MonedaDomain model) {
        super(model, ContabilidadSwingModule.monedaUC, MonedaDomain.class);
        initComponents();
        personalize();
        update();
    }

    private void initComponents() {
        setHeader("Crear Moneda", "Editar Moneda");

        //tipo
        textFieldTipo = MaterialTextFactory.buildIcon();
        textFieldTipo.setLabel("Moneda");
        textFieldTipo.setHint("Nombre de moneda. Ej.: EUR");
        textFieldTipo.setIcon(MaterialIcons.PRIORITY_HIGH);

        //compra
        textFieldCompra = MaterialPreparedTextFactory.buildFormatedMoneyIcon();
        textFieldCompra.setHint("Precio de compra");
        textFieldCompra.setLabel("Compra");

        //venta
        textFieldVenta = MaterialPreparedTextFactory.buildFormatedMoneyIcon();
        textFieldVenta.setHint("Precio de venta");
        textFieldVenta.setLabel("Venta");

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldTipo);
        HorizontalLayoutContainer.builder hlcCompraVenta = HorizontalLayoutContainer.builder((int) textFieldCompra.getPreferredSize().getHeight());
        hlcCompraVenta.add(HorizontalLayoutComponent.builder(textFieldCompra).gapRight(5).build());
        hlcCompraVenta.add(HorizontalLayoutComponent.builder(textFieldVenta).gapLeft(5).build());
        vlc.add(hlcCompraVenta.build());

        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextArea textAreaDescripcion;
    private MaterialFormatedTextFieldIcon<BigDecimal> textFieldCompra;
    private MaterialFormatedTextFieldIcon<BigDecimal> textFieldVenta;
    private MaterialTextFieldIcon textFieldTipo;
    // End of variables declaration                   

    private void personalize() {
        try {
            textFieldCompra.setExtra(MonedaHandler.getMonedaBase().getNombreMoneda());
            textFieldVenta.setExtra(MonedaHandler.getMonedaBase().getNombreMoneda());
        } catch (Exception e) {
        }
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreMoneda", textFieldTipo);
        bindFields.put("compra", textFieldCompra);
        bindFields.put("venta", textFieldVenta);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}
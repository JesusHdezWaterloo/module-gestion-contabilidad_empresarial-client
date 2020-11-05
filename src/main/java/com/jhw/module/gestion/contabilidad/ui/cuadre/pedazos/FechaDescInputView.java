package com.jhw.module.gestion.contabilidad.ui.cuadre.pedazos;

import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.core.domain.facade.FechaDescUI;
import com.jhw.module.gestion.contabilidad.ui.forma_pago.FormaPagoICBS;
import com.jhw.swing.material.components.datepicker.MaterialDatePickerIcon;
import com.jhw.swing.material.components.datepicker.MaterialDatePickersFactory;
import com.jhw.swing.material.components.datepicker._MaterialDatePickerIcon;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class FechaDescInputView extends CleanCRUDInputView<FechaDescUI> {

    public static FechaDescInputView from() {
        return new FechaDescInputView(null);
    }

    private FechaDescInputView(FechaDescUI model) {
        super(model, null, FechaDescUI.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("", "");

        //fecha
        datePickerFecha = MaterialDatePickersFactory.buildIcon();
        datePickerFecha.setLabel("Fecha");

        //forma de pago
        formaPagoICBS = new FormaPagoICBS();

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(datePickerFecha);
        vlc.add(formaPagoICBS);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialDatePickerIcon datePickerFecha;
    private FormaPagoICBS formaPagoICBS;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("fecha", datePickerFecha);
        bindFields.put("pago", formaPagoICBS);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

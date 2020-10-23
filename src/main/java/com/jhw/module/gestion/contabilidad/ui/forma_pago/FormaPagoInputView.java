package com.jhw.module.gestion.contabilidad.ui.forma_pago;

import com.jhw.module.gestion.contabilidad.core.domain.FormaPagoDomain;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.standards.MaterialIcons;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class FormaPagoInputView extends CleanCRUDInputView<FormaPagoDomain> {

    public static FormaPagoInputView from() {
        return new FormaPagoInputView(null);
    }

    public static FormaPagoInputView fromModel(FormaPagoDomain model) {
        return new FormaPagoInputView(model);
    }

    private FormaPagoInputView(FormaPagoDomain model) {
        super(model, ContabilidadSwingModule.formaPagoUC, FormaPagoDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        //header
        setHeader("Crear Tipo de Pago", "Editar Tipo de Pago");

        //nombre
        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setLabel("Tipo de pago");
        textFieldNombre.setHint("Nombre del tipo de pago. Ej: Efectivo");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);

        //descripcion
        textAreaDescripcion  = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();

        vlc.add(textFieldNombre);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    private MaterialTextFieldIcon textFieldNombre;
    private MaterialTextArea textAreaDescripcion;

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreFormaPago", textFieldNombre);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

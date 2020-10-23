package com.jhw.module.gestion.contabilidad.ui.titular;

import com.jhw.module.gestion.contabilidad.core.domain.TitularDomain;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
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
public class TitularInputView extends CleanCRUDInputView<TitularDomain> {

    public static TitularInputView from() {
        return new TitularInputView(null);
    }
    public static TitularInputView fromModel(TitularDomain model) {
        return new TitularInputView(model);
    }

    private TitularInputView(TitularDomain model) {
        super(model, ContabilidadSwingModule.titularUC, TitularDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Titular", "Editar Titular");

        //tipo
        textFieldNombre = MaterialTextFactory.buildIcon();
        textFieldNombre.setLabel("Titular");
        textFieldNombre.setHint("Nombre del titilar");
        textFieldNombre.setIcon(MaterialIcons.PRIORITY_HIGH);

        //descripcion
        textAreaDescripcion  = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldNombre);

        vlc.add(textAreaDescripcion, true);
        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldNombre;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreTitular", textFieldNombre);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

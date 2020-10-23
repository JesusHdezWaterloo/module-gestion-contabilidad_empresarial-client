package com.jhw.module.gestion.contabilidad.ui.tipo_cuenta;

import com.clean.core.exceptions.ValidationException;
import com.jhw.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFactory;
import com.jhw.swing.material.components.textfield.MaterialTextFieldIcon;
import com.jhw.swing.material.components.toggle.MaterialToggleButton;
import com.jhw.swing.material.components.toggle.MaterialToggleFactory;
import com.jhw.swing.material.components.toggle.ToggleGroup;
import com.jhw.swing.material.standards.MaterialIcons;
import java.util.Map;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class TipoCuentaInputView extends CleanCRUDInputView<TipoCuentaDomain> {

    public static TipoCuentaInputView from() {
        return new TipoCuentaInputView(null);
    }

    public static TipoCuentaInputView fromModel(TipoCuentaDomain model) {
        return new TipoCuentaInputView(model);
    }

    private TipoCuentaInputView(TipoCuentaDomain model) {
        super(model, ContabilidadSwingModule.tipoCuentaUC, TipoCuentaDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Tipo de Cuenta", "Editar Tipo de Cuenta");

        //tipo
        textFieldTipo = MaterialTextFactory.buildIcon();
        textFieldTipo.setLabel("Tipo de cuenta");
        textFieldTipo.setHint("Nombre del tipo de cuenta. Ej.: Cuenta por Pagar");
        textFieldTipo.setIcon(MaterialIcons.PRIORITY_HIGH);

        //debito
        checkBoxDebito = MaterialToggleFactory.buildCheckBox();
        checkBoxDebito.setText("Débito");
        checkBoxDebito.setSelected(true);

        //credito
        checkBoxCredito = MaterialToggleFactory.buildCheckBox();
        checkBoxCredito.setText("Crédito");

        ToggleGroup group = MaterialToggleFactory.buildGroup();
        group.add(checkBoxDebito);
        group.add(checkBoxCredito);

        //liquidable
        checkBoxLiquidable = MaterialToggleFactory.buildCheckBox();
        checkBoxLiquidable.setText("Liquidable");

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldTipo);

        HorizontalLayoutContainer.builder hlcDebCred = HorizontalLayoutContainer.builder();
        hlcDebCred.add(checkBoxDebito);
        hlcDebCred.add(checkBoxCredito);
        vlc.add(hlcDebCred.build());

        vlc.add(checkBoxLiquidable);

        vlc.add(textAreaDescripcion, true);
        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldTipo;
    private MaterialToggleButton checkBoxDebito;
    private MaterialToggleButton checkBoxCredito;
    private MaterialToggleButton checkBoxLiquidable;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public void update() {
        super.update();
        if (getOldModel() != null) {
            checkBoxDebito.setSelected(getOldModel().getDebitoCredito());
            checkBoxCredito.setSelected(!getOldModel().getDebitoCredito());
        }
    }

    @Override
    public TipoCuentaDomain getNewModel() throws Exception {
        TipoCuentaDomain m = super.getNewModel();
        if (!checkBoxDebito.isSelected() && !checkBoxCredito.isSelected()) {
            throw new ValidationException("La cuenta tiene que ser de débito o de crédito");
        }
        m.setDebitoCredito(checkBoxDebito.isSelected());
        return m;
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreTipoCuenta", textFieldTipo);
        bindFields.put("liquidable", checkBoxLiquidable);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

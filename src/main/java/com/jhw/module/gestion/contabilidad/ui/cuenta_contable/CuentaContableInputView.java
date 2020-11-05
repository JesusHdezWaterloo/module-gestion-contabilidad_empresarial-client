package com.jhw.module.gestion.contabilidad.ui.cuenta_contable;

import com.jhw.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.gestion.contabilidad.ui.moneda.MonedaICBS;
import com.jhw.module.gestion.contabilidad.ui.tipo_cuenta.TipoCuentaICBS;
import com.jhw.module.gestion.contabilidad.ui.titular.TitularICBS;
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
public class CuentaContableInputView extends CleanCRUDInputView<CuentaContableDomain> {

    public static CuentaContableInputView from() {
        return new CuentaContableInputView(null);
    }

    public static CuentaContableInputView fromModel(CuentaContableDomain model) {
        return new CuentaContableInputView(model);
    }

    private CuentaContableInputView(CuentaContableDomain model) {
        super(model, ContabilidadSwingModule.cuentaContableUC, CuentaContableDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        //header
        setHeader("Crear Cuenta Contable", "Editar Cuenta Contable");

        //nombre
        textFieldNombreCuenta = MaterialTextFactory.buildIcon();
        textFieldNombreCuenta.setLabel("Nombre");
        textFieldNombreCuenta.setHint("Nombre de la cuenta");
        textFieldNombreCuenta.setIcon(ContabilidadModuleNavigator.ICON_CUENTA_CONTABLE);

        //codigo
        textFieldCodigo = MaterialTextFactory.buildIcon();
        textFieldCodigo.setLabel("Código contable");
        textFieldCodigo.setHint("Código de identificación. Ej: 110");
        textFieldCodigo.setIcon(MaterialIcons.GRID_ON);

        //moneda
        monedaICBS = new MonedaICBS();
        monedaICBS.setIcon(MaterialIcons.ATTACH_MONEY);

        //tipo de cuenta
        tipoCuentaICBS = new TipoCuentaICBS();

        //titular
        titularICBS = new TitularICBS();

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldNombreCuenta);
        vlc.add(textFieldCodigo);
        vlc.add(monedaICBS);
        vlc.add(tipoCuentaICBS);
        vlc.add(titularICBS);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldNombreCuenta;
    private MaterialTextFieldIcon textFieldCodigo;
    private MonedaICBS monedaICBS;
    private TipoCuentaICBS tipoCuentaICBS;
    private TitularICBS titularICBS;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public void update() {
        super.update();
        monedaICBS.setEnabled(getOldModel() == null);
        tipoCuentaICBS.setEnabled(getOldModel() == null);
    }

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreCuenta", textFieldNombreCuenta);
        bindFields.put("codigo", textFieldCodigo);
        bindFields.put("monedaFk", monedaICBS);
        bindFields.put("titularFk", titularICBS);
        bindFields.put("tipoCuentaFk", tipoCuentaICBS);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

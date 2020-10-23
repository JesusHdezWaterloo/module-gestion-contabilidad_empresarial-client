package com.jhw.module.gestion.contabilidad.ui.tipo_cuenta;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class TipoCuentaDetailView extends _MaterialPanelDetail<TipoCuentaDomain> {

    private static final String COL_NOMBRE = "Tipo";
    private static final String COL_DEB_CRED = "Débito - Crédito";
    private static final String COL_LIQUIDABLE = "Liquidable";

    public TipoCuentaDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DEB_CRED).build(),
                Column.builder().name(COL_LIQUIDABLE).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Tipos de Cuenta");
        this.setIcon(ContabilidadModuleNavigator.ICON_TIPO_CUENTA);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.tipoCuentaUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(TipoCuentaDomain obj) {
        return new Object[]{
            obj.getNombreTipoCuenta(),
            obj.debito_credito(),
            obj.liquidable()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, TipoCuentaInputView.from());
    }

    @Override
    protected TipoCuentaDomain deleteAction(TipoCuentaDomain obj) {
        try {
            return ContabilidadSwingModule.tipoCuentaUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(TipoCuentaDomain obj) {
        new DialogModelInput(this, TipoCuentaInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(TipoCuentaDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

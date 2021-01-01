package com.jhw.module.gestion.contabilidad.ui.titular;

import com.root101.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.TitularDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class TitularDetailView extends _MaterialPanelDetail<TitularDomain> {

    private static final String COL_NOMBRE = "Titular";
    private static final String COL_DESC = "Descripción";

    public TitularDetailView() {
        super(
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DESC).build()
        );

        this.personalize();
    }

    private void personalize() {
        this.setHeaderText("Titulares de cuenta");
        this.setIcon(ContabilidadModuleNavigator.ICON_TITULAR);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.titularUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(TitularDomain obj) {
        return new Object[]{obj.getNombreTitular(),
            obj.getDescripcion()};
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, TitularInputView.from());
    }

    @Override
    protected TitularDomain deleteAction(TitularDomain obj) {
        try {
            return ContabilidadSwingModule.titularUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(TitularDomain obj) {
        new DialogModelInput(this, TitularInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(TitularDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

}

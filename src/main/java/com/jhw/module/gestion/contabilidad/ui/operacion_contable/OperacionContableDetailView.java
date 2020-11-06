package com.jhw.module.gestion.contabilidad.ui.operacion_contable;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.module.gestion.contabilidad.core.domain.OperacionContableDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyCellRender;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyTableComponent;
import com.jhw.swing.models.detail._MaterialPanelDetail;
import com.jhw.swing.util.Utils;
import com.jhw.utils.others.DTF;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class OperacionContableDetailView extends _MaterialPanelDetail<OperacionContableDomain> {

    private static final String COL_DCUMENTO = "Documento";
    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_DEBITO = "Débito";
    private static final String COL_CREDITO = "Crédito";
    private static final String COL_FECHA = "Fecha";
    private static final String COL_CUENTA = "Cuenta";

    private final CuentaContableDomain cuenta;

    public OperacionContableDetailView() {
        this(null);
    }

    public OperacionContableDetailView(CuentaContableDomain cuenta) {
        super(
                Column.builder().name(COL_DCUMENTO).build(),
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DEBITO).build(),
                Column.builder().name(COL_CREDITO).build(),
                Column.builder().name(COL_FECHA).build(),
                Column.builder().name(COL_CUENTA).build()
        );
        this.cuenta = cuenta;

        this.personalize();

        //actualizarlo a mano xq es un popup que mas nadie actualzia
        this.update();
    }

    private void personalize() {
        setUpEditorsRenders();
        String cuentaStr = cuenta == null ? "" : ": " + cuenta.toString();
        this.setHeaderText("Operaciones" + cuentaStr);
        this.setIcon(ContabilidadModuleNavigator.ICON_OPERACION);

        this.setOptionPanelVisibility(false);

        this.setActionColumnButtonsVisivility(false, false, false);//no pone el view, no esta implementado todavia

        this.setAdjustColumns(true);

        changeSize();
    }

    private void changeSize() {
        Rectangle screen = Utils.getScreenSize();
        setPreferredSize(new Dimension(screen.width * 8 / 10, screen.height * 8 / 10));
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.operacionContableUC.findAll(cuenta));
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(OperacionContableDomain obj) {
        return new Object[]{
            obj.getInfoOperacionContableFk().getDocumento(),
            obj.getInfoOperacionContableFk().getNombre(),
            MoneyTableComponent.from(obj.getDebito(), obj.getCuentaFk().getMonedaFk()),
            MoneyTableComponent.from(obj.getCredito(), obj.getCuentaFk().getMonedaFk()),
            DTF.LOCAL_DATE_FORMATTER.format(obj.getInfoOperacionContableFk().getFecha()),
            obj.getCuentaFk()
        };
    }

    @Override
    protected void buttonNuevoActionListener() {
        //new DialogModelInput(this, new LiquidacionInputView());
    }

    @Override
    protected OperacionContableDomain deleteAction(OperacionContableDomain obj) {
        try {
            return ContabilidadSwingModule.operacionContableUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(OperacionContableDomain obj) {
        //new DialogModelInput(this, new LiquidacionInputView(obj));
    }

    @Override
    protected void viewAction(OperacionContableDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

    private void setUpEditorsRenders() {
        getTable().getColumn(COL_DEBITO).setCellRenderer(new MoneyCellRender());
        getTable().getColumn(COL_CREDITO).setCellRenderer(new MoneyCellRender());
    }
}

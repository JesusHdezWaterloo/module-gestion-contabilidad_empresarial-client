package com.jhw.module.gestion.contabilidad.ui.liquidacion;

import com.clean.core.app.services.ExceptionHandler;
import com.jhw.module.gestion.contabilidad.core.domain.CuentaBancariaDomain;
import com.jhw.module.gestion.contabilidad.core.domain.LiquidacionDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyCellRender;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyTableComponent;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;
import com.jhw.swing.util.Utils;
import com.jhw.utils.others.SDF;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class LiquidacionDetailView extends _MaterialPanelDetail<LiquidacionDomain> {

    private static final String COL_DCUMENTO = "Documento";
    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_DEBITO = "+";
    private static final String COL_CREDITO = "-";
    private static final String COL_FECHA = "Fecha";
    private static final String COL_CUENTA = "Cuenta";
    private static final String COL_REFERENCIA = "Referencia";

    private final CuentaBancariaDomain cuenta;

    public LiquidacionDetailView() {
        this(null);
    }

    public LiquidacionDetailView(CuentaBancariaDomain cuenta) {
        super(
                Column.builder().name(COL_DCUMENTO).build(),
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_DEBITO).build(),
                Column.builder().name(COL_CREDITO).build(),
                Column.builder().name(COL_FECHA).build(),
                Column.builder().name(COL_REFERENCIA).build(),
                Column.builder().name(COL_CUENTA).build()
        );

        this.cuenta = cuenta;
        this.personalize();
        
        this.update();//update aqui ya que es un dialog que mas nadie lo actualiza
    }

    private void personalize() {
        setUpEditorsRenders();

        String cuentaStr = cuenta == null ? "" : ": " + cuenta.toString();
        this.setHeaderText("Liquidaciones" + cuentaStr);
        this.setIcon(ContabilidadModuleNavigator.ICON_LIQUIDACIONES);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia
        changeSize();

        sizeColumnCuenta();
    }

    private void sizeColumnCuenta() {
        //si es de una cuenta especifica no muestra la columna, es redundante
        if (cuenta != null) {
            getTable().getColumn(COL_CUENTA).setMaxWidth(0);
            getTable().getColumn(COL_CUENTA).setMinWidth(0);
            getTable().getColumn(COL_CUENTA).setPreferredWidth(0);
        }
    }

    private void changeSize() {
        Rectangle screen = Utils.getScreenSize();
        setPreferredSize(new Dimension(screen.width * 8 / 10, screen.height * 8 / 10));
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.liquicadionUC.findAll(cuenta));
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(LiquidacionDomain obj) {
        return new Object[]{
            obj.getDocumento(),
            obj.getNombre(),
            MoneyTableComponent.from(obj.getDebito(), obj.getCuentaFk().getMonedaFk()),
            MoneyTableComponent.from(obj.getCredito(), obj.getCuentaFk().getMonedaFk()),
            SDF.SDF.format(obj.getFecha()),
            obj.getCuadreFk(),
            obj.getCuentaFk()
        };
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, LiquidacionInputView.from());
    }

    @Override
    protected LiquidacionDomain deleteAction(LiquidacionDomain obj) {
        try {
            return ContabilidadSwingModule.liquicadionUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(LiquidacionDomain obj) {
        new DialogModelInput(this, LiquidacionInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(LiquidacionDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

    private void setUpEditorsRenders() {
        getTable().getColumn(COL_DEBITO).setCellRenderer(new MoneyCellRender());
        getTable().getColumn(COL_CREDITO).setCellRenderer(new MoneyCellRender());
    }
}

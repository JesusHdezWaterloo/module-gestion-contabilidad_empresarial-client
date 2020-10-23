package com.jhw.module.gestion.contabilidad.ui.cuadre;

import com.clean.core.app.services.ExceptionHandler;
import com.clean.core.app.services.Notification;
import com.clean.core.app.services.NotificationsGeneralType;
import com.jhw.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.jhw.module.gestion.contabilidad.core.domain.LiquidacionDomain;
import com.jhw.module.gestion.contabilidad.ui.liquidacion.LiquidacionInputView;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.material.components.container.panel._PanelGradient;
import com.jhw.swing.material.components.table.Column;
import com.jhw.swing.material.components.table.editors_renders.component.ComponentCellEditor;
import com.jhw.swing.material.components.table.editors_renders.component.ComponentCellRender;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyCellRender;
import com.jhw.swing.material.components.table.editors_renders.money.MoneyTableComponent;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialIcons;
import com.jhw.swing.models.input.dialogs.DialogModelInput;
import com.jhw.swing.models.detail._MaterialPanelDetail;
import com.jhw.utils.others.SDF;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class CuadreDetailView extends _MaterialPanelDetail<CuadreDomain> {

    private static final String COL_LIQUIDADO = "Liq.";
    private static final String COL_DCUMENTO = "Documento";
    private static final String COL_NOMBRE = "Nombre";
    private static final String COL_FECHA = "Fecha";
    private static final String COL_CUENTA = "Cuenta";
    private static final String COL_DEBITO1 = "+";
    private static final String COL_CREDITO1 = "-";
    private static final String COL_CUADRE = "Cuadre";
    //private static final String COL_DEBITO2 = "+ ";
    //private static final String COL_CREDITO2 = "- ";

    public CuadreDetailView() {
        super(
                Column.builder().name(COL_LIQUIDADO).width(5).build(),
                Column.builder().name(COL_DCUMENTO).build(),
                Column.builder().name(COL_NOMBRE).build(),
                Column.builder().name(COL_FECHA).build(),
                Column.builder().name(COL_CUENTA).build(),
                Column.builder().name(COL_DEBITO1).build(),
                Column.builder().name(COL_CREDITO1).build(),
                Column.builder().name(COL_CUADRE).build()
        //Column.builder().name(COL_DEBITO2).build(),
        //Column.builder().name(COL_CREDITO2).build()
        );

        this.personalize();
    }

    private void personalize() {
        setUpEditorsRenders();

        //addOptionsElements();
        addActionsExtra();

        this.setHeaderText("Operaciones - Cuadre");
        this.setIcon(ContabilidadModuleNavigator.ICON_CUADRE);

        this.setActionColumnButtonsVisivility(true, true, false);//no pone el view, no esta implementado todavia

        this.setAdjustColumns(true);
    }

    @Override
    public void update() {
        try {
            setCollection(ContabilidadSwingModule.cuadreUC.findAll());
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

    @Override
    public Object[] getRowObject(CuadreDomain obj) {
        return new Object[]{
            getLiquidado(obj),
            obj.info().getDocumento(),
            obj.info().getNombre(),
            SDF.SDF.format(obj.info().getFecha()),
            obj.getOperacionContableFk().getCuentaFk(),
            MoneyTableComponent.from(obj.getOperacionContableFk().getDebito(), obj.getOperacionContableFk().getCuentaFk().getMonedaFk()),
            MoneyTableComponent.from(obj.getOperacionContableFk().getCredito(), obj.getOperacionContableFk().getCuentaFk().getMonedaFk()),
            obj.getOperacionContableCuadreFk().getCuentaFk()
        //MoneyTableComponent.from(obj.getOperacionContableCuadreFk().getDebito(), obj.getOperacionContableCuadreFk().getCuentaFk().getMonedaFk()),
        //MoneyTableComponent.from(obj.getOperacionContableCuadreFk().getCredito(), obj.getOperacionContableCuadreFk().getCuentaFk().getMonedaFk())
        };
    }

    private JPanel getLiquidado(CuadreDomain cuadre) {
        _PanelGradient panel = new _PanelGradient();
        if (cuadre.getLiquidada()) {
            panel.setIcon(MaterialIcons.DONE);
        } else {
            panel.setBackground(MaterialColors.TRANSPARENT);
            panel.setIcon(null);
        }
        return panel;
    }

    @Override
    protected void buttonNuevoActionListener() {
        new DialogModelInput(this, CuadreInputView.from());
    }

    @Override
    protected CuadreDomain deleteAction(CuadreDomain obj) {
        try {
            return ContabilidadSwingModule.cuadreUC.destroy(obj);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
        return null;
    }

    @Override
    protected void editAction(CuadreDomain obj) {
        new DialogModelInput(this, CuadreInputView.fromModel(obj));
    }

    @Override
    protected void viewAction(CuadreDomain obj) {
        System.out.println("NO NECESARIO TODAVÍA.");
    }

    private void setUpEditorsRenders() {
        ComponentCellRender colorRender = new ComponentCellRender(false);
        getTable().getColumn(COL_LIQUIDADO).setCellEditor(new ComponentCellEditor(colorRender, false));
        getTable().getColumn(COL_LIQUIDADO).setCellRenderer(colorRender);

        getTable().getColumn(COL_DEBITO1).setCellRenderer(new MoneyCellRender());
        getTable().getColumn(COL_CREDITO1).setCellRenderer(new MoneyCellRender());
        //getTable().getColumn(COL_DEBITO2).setCellRenderer(new MoneyCellRender());
        //getTable().getColumn(COL_CREDITO2).setCellRenderer(new MoneyCellRender());
    }

    private void addActionsExtra() {
        this.addActionExtra(new AbstractAction("Liquidar", ContabilidadModuleNavigator.ICON_LIQUIDACIONES.deriveIcon(18f)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLiquidarCuadreActionPerformed();
            }
        });
    }

    private void onLiquidarCuadreActionPerformed() {
        try {
            CuadreDomain obj = getSelectedElement();
            if (obj.getLiquidada()) {
                Notification.showConfirmDialog(NotificationsGeneralType.CONFIRM_INFO, "Ya este cuadre se liquidó.");
            } else {
                LiquidacionDomain liq = ContabilidadSwingModule.liquicadionUC.getLiquidacion(obj);
                new DialogModelInput(this, LiquidacionInputView.fromBase(liq));
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

}

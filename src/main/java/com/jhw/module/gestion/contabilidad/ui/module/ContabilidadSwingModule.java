package com.jhw.module.gestion.contabilidad.ui.module;

import com.clean.swing.app.AbstractSwingApplication;
import com.clean.swing.app.DefaultAbstractSwingMainModule;
import com.clean.swing.app.dashboard.DashBoardSimple;
import com.clean.swing.app.dashboard.DashboardConstants;
import com.jhw.module.gestion.contabilidad.consume.module.ContabilidadConsumeCoreModule;
import com.jhw.module.gestion.contabilidad.core.usecase_def.CuadreUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.CuentaBancariaUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.CuentaContableUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.FormaPagoUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.InfoOperacionContableUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.LiquidacionUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.MonedaUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.OperacionContableUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.SubcuentaUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.TipoCuentaUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.TipoOperacionContableUseCase;
import com.jhw.module.gestion.contabilidad.core.usecase_def.TitularUseCase;
import com.jhw.module.gestion.contabilidad.service.ResourceServiceImplementation;
import com.jhw.module.gestion.contabilidad.ui.cuadre.CuadreDetailView;
import com.jhw.module.gestion.contabilidad.ui.cuenta.CuentasMainPanel;
import com.jhw.module.gestion.contabilidad.ui.moneda.MonedaDetailView;
import com.jhw.module.gestion.contabilidad.ui.forma_pago.FormaPagoDetailView;
import com.jhw.module.gestion.contabilidad.ui.tipo_cuenta.TipoCuentaDetailView;
import com.jhw.module.gestion.contabilidad.ui.tipo_operacion.TipoOperacionContableDetailView;
import com.jhw.module.gestion.contabilidad.ui.titular.TitularDetailView;
import com.jhw.swing.material.components.taskpane.CollapseMenu;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ContabilidadSwingModule extends DefaultAbstractSwingMainModule {

    private final ContabilidadModuleNavigator navigator = new ContabilidadModuleNavigator();

    public final static CuadreUseCase cuadreUC;
    public final static CuentaBancariaUseCase cuentaBancariaUC;
    public final static CuentaContableUseCase cuentaContableUC;
    public final static InfoOperacionContableUseCase infoOpUC;
    public final static LiquidacionUseCase liquicadionUC;
    public final static MonedaUseCase monedaUC;
    public final static OperacionContableUseCase operacionContableUC;
    public final static TipoCuentaUseCase tipoCuentaUC;
    public final static TipoOperacionContableUseCase tipoOperacionContableUC;
    public final static FormaPagoUseCase formaPagoUC;
    public final static TitularUseCase titularUC;

    static {
        ContabilidadConsumeCoreModule.init();

        cuadreUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuadreUseCase.class);
        cuentaBancariaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaBancariaUseCase.class);
        cuentaContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaContableUseCase.class);
        infoOpUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(InfoOperacionContableUseCase.class);
        liquicadionUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(LiquidacionUseCase.class);
        formaPagoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(FormaPagoUseCase.class);
        monedaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(MonedaUseCase.class);
        operacionContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(OperacionContableUseCase.class);
        tipoCuentaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TipoCuentaUseCase.class);
        tipoOperacionContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TipoOperacionContableUseCase.class);
        titularUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TitularUseCase.class);

        ResourceServiceImplementation.init();
    }

    private ContabilidadSwingModule() {
    }

    public static ContabilidadSwingModule init() {
        return new ContabilidadSwingModule();
    }

    @Override
    public void register(AbstractSwingApplication app) {
        System.out.println("Creando 'Cuentas'");
        registerMainElements(app);
    }

    private void registerMainElements(AbstractSwingApplication app) {
        DashBoardSimple dash = app.rootView().dashboard();

        CollapseMenu menu = new CollapseMenu(ContabilidadModuleNavigator.ICON_CONTABILIDAD, ContabilidadModuleNavigator.CONTABILIDAD);
        dash.addKeyValue(DashboardConstants.MAIN_ELEMENT, menu);

        dash.addView(ContabilidadModuleNavigator.NAV_CUENTA, new CuentasMainPanel());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.CUENTA, ContabilidadModuleNavigator.ICON_CUENTA_BANCARIA) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_CUENTA);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_CUADRE, new CuadreDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.CUADRE, ContabilidadModuleNavigator.ICON_CUADRE) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_CUADRE);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_MONEDA, new MonedaDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.MONEDA, ContabilidadModuleNavigator.ICON_MONEDA) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_MONEDA);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_FORMA_PAGO, new FormaPagoDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.FORMA_PAGO, ContabilidadModuleNavigator.ICON_FORMA_PAGO) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_FORMA_PAGO);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_TIPO_CUENTA, new TipoCuentaDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.TIPO_CUENTA, ContabilidadModuleNavigator.ICON_TIPO_CUENTA) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_TIPO_CUENTA);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_TITULAR, new TitularDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.TITULAR, ContabilidadModuleNavigator.ICON_TITULAR) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_TITULAR);
            }
        });

        dash.addView(ContabilidadModuleNavigator.NAV_TIPO_OPERACION, new TipoOperacionContableDetailView());
        menu.addMenuItem(new AbstractAction(ContabilidadModuleNavigator.TIPO_OPERACION, ContabilidadModuleNavigator.ICON_TIPO_OPERACION) {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.navigateTo(ContabilidadModuleNavigator.NAV_TIPO_OPERACION);
            }
        });
    }

    @Override
    public void navigateTo(String string, Object... o) {
        navigator.navigateTo(string, o);
    }

}

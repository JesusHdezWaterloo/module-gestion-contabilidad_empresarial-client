/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.gestion.contabilidad.ui.module;

import com.root101.clean.swing.app.AbstractSwingApplication;
import com.root101.clean.swing.app.DefaultAbstractSwingMainModule;
import com.root101.clean.swing.app.dashboard.DashBoardSimple;
import com.root101.clean.swing.app.dashboard.DashboardConstants;
import com.root101.module.gestion.contabilidad.service.ResourceServiceImplementation;
import com.root101.module.gestion.contabilidad.consume.module.ContabilidadConsumeCoreModule;
import com.root101.module.gestion.contabilidad.service.ResourceServiceClientImplementation;
import com.root101.module.gestion.contabilidad.ui.cuadre.CuadreDetailView;
import com.root101.module.gestion.contabilidad.ui.cuenta.CuentasMainPanel;
import com.root101.module.gestion.contabilidad.ui.moneda.MonedaDetailView;
import com.root101.module.gestion.contabilidad.ui.forma_pago.FormaPagoDetailView;
import com.root101.module.gestion.contabilidad.ui.tipo_cuenta.TipoCuentaDetailView;
import com.root101.module.gestion.contabilidad.ui.tipo_operacion.TipoOperacionContableDetailView;
import com.root101.module.gestion.contabilidad.ui.titular.TitularDetailView;
import com.root101.swing.material.components.taskpane.CollapseMenu;
import com.root101.module.gestion.contabilidad.consume.usecase_def.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ContabilidadSwingModule extends DefaultAbstractSwingMainModule {

    private final ContabilidadModuleNavigator navigator = new ContabilidadModuleNavigator();

    public final static CuadreUseCaseConsume cuadreUC;
    public final static CuentaBancariaUseCaseConsume cuentaBancariaUC;
    public final static CuentaContableUseCaseConsume cuentaContableUC;
    public final static InfoOperacionContableUseCaseConsume infoOpUC;
    public final static LiquidacionUseCaseConsume liquicadionUC;
    public final static MonedaUseCaseConsume monedaUC;
    public final static OperacionContableUseCaseConsume operacionContableUC;
    public final static TipoCuentaUseCaseConsume tipoCuentaUC;
    public final static TipoOperacionContableUseCaseConsume tipoOperacionContableUC;
    public final static FormaPagoUseCaseConsume formaPagoUC;
    public final static TitularUseCaseConsume titularUC;

    static {
        cuadreUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuadreUseCaseConsume.class);
        cuentaBancariaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaBancariaUseCaseConsume.class);
        cuentaContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(CuentaContableUseCaseConsume.class);
        infoOpUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(InfoOperacionContableUseCaseConsume.class);
        liquicadionUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(LiquidacionUseCaseConsume.class);
        formaPagoUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(FormaPagoUseCaseConsume.class);
        monedaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(MonedaUseCaseConsume.class);
        operacionContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(OperacionContableUseCaseConsume.class);
        tipoCuentaUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TipoCuentaUseCaseConsume.class);
        tipoOperacionContableUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TipoOperacionContableUseCaseConsume.class);
        titularUC = ContabilidadConsumeCoreModule.getInstance().getImplementation(TitularUseCaseConsume.class);
    }

    private ContabilidadSwingModule() {
    }

    public static ContabilidadSwingModule init() {
        System.out.println("Iniciando 'Cuentas'");
        ResourceServiceClientImplementation.init();
        ResourceServiceImplementation.init();

        return new ContabilidadSwingModule();
    }

    @Override
    public void register(AbstractSwingApplication app) {
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

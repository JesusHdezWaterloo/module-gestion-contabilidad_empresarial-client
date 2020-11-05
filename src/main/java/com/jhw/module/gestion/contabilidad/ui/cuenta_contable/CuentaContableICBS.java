package com.jhw.module.gestion.contabilidad.ui.cuenta_contable;

import com.jhw.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.jhw.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.jhw.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class CuentaContableICBS extends InputComboBoxSelection<CuentaContableDomain> {

    private List<CuentaContableDomain> actualList;

    public CuentaContableICBS() {
        setLabel("Cuenta Contable");
        setIcon(ContabilidadModuleNavigator.ICON_CUENTA_CONTABLE);
    }

    @Override
    public List<CuentaContableDomain> getList() throws Exception{
        actualList = ContabilidadSwingModule.cuentaContableUC.findAll();
        return actualList;
    }

    @Override
    public ModelPanel<CuentaContableDomain> inputPanel() {
        return CuentaContableInputView.from();
    }
    
    public void setMatchingItem(TipoCuentaDomain tipo, MonedaDomain moneda) {
        for (CuentaContableDomain c : actualList) {
            if (c.getTipoCuentaFk().equals(tipo) && c.getMonedaFk().equals(moneda)) {
                setObject(c);
                break;
            }
        }
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.cuentaContableUC.addPropertyChangeListener(this);
    }
}

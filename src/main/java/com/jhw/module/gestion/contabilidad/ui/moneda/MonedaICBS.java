package com.jhw.module.gestion.contabilidad.ui.moneda;

import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class MonedaICBS extends InputComboBoxSelection<MonedaDomain> {

    public MonedaICBS() {
        setLabel("Moneda");
        setIcon(null);
    }

    @Override
    public List<MonedaDomain> getList() throws Exception {
        return ContabilidadSwingModule.monedaUC.findAll();
    }

    @Override
    public ModelPanel<MonedaDomain> inputPanel() {
        return MonedaInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.monedaUC.addPropertyChangeListener(this);
    }
}

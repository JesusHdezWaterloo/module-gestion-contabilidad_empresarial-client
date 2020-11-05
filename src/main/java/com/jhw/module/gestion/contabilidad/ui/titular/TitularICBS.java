package com.jhw.module.gestion.contabilidad.ui.titular;

import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.module.gestion.contabilidad.core.domain.TitularDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class TitularICBS extends InputComboBoxSelection<TitularDomain> {

    public TitularICBS() {
        setLabel("Titular");
        setIcon(ContabilidadModuleNavigator.ICON_TITULAR);
    }

    @Override
    public List<TitularDomain> getList() throws Exception{
        return ContabilidadSwingModule.titularUC.findAll();
    }

    @Override
    public ModelPanel<TitularDomain> inputPanel() {
        return TitularInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.titularUC.addPropertyChangeListener(this);
    }
}

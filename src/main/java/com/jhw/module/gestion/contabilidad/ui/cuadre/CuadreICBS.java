package com.jhw.module.gestion.contabilidad.ui.cuadre;

import com.jhw.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.swing.models.input.icbs.InputComboBoxSelection;
import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadModuleNavigator;
import com.jhw.swing.models.input.panels.ModelPanel;
import java.util.List;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class CuadreICBS extends InputComboBoxSelection<CuadreDomain> {

    public CuadreICBS() {
        setLabel("Cuadre");
        setIcon(ContabilidadModuleNavigator.ICON_CUADRE);
    }

    @Override
    public List<CuadreDomain> getList() throws Exception {
        return ContabilidadSwingModule.cuadreUC.findAllPending();
    }

    @Override
    public ModelPanel<CuadreDomain> inputPanel() {
        return CuadreInputView.from();
    }

    @Override
    protected void addPropertyChange() {
        ContabilidadSwingModule.cuadreUC.addPropertyChangeListener(this);
    }
}

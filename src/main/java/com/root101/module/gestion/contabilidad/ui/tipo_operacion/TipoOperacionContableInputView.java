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
package com.root101.module.gestion.contabilidad.ui.tipo_operacion;

import com.root101.module.gestion.contabilidad.core.domain.TipoOperacionContableDomain;
import com.root101.swing.material.components.container.layout.VerticalLayoutContainer;
import com.root101.swing.models.clean.CleanCRUDInputView;
import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.root101.module.gestion.contabilidad.ui.tipo_cuenta.TipoCuentaICBS;
import com.root101.swing.material.components.textarea.MaterialTextArea;
import com.root101.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import com.root101.swing.material.components.textfield.MaterialTextFactory;
import com.root101.swing.material.components.textfield.MaterialTextFieldIcon;
import com.root101.swing.material.standards.MaterialIcons;
import java.util.Map;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TipoOperacionContableInputView extends CleanCRUDInputView<TipoOperacionContableDomain> {

    public static TipoOperacionContableInputView fromModel(TipoOperacionContableDomain model) {
        return new TipoOperacionContableInputView(model);
    }

    public static TipoOperacionContableInputView from() {
        return new TipoOperacionContableInputView(null);
    }

    private TipoOperacionContableInputView(TipoOperacionContableDomain model) {
        super(model, ContabilidadSwingModule.tipoOperacionContableUC, TipoOperacionContableDomain.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("Crear Tipo de Operación", "Editar Tipo de Operación");

        //tipo
        textFieldTipo = MaterialTextFactory.buildIcon();
        textFieldTipo.setLabel("Tipo de operación");
        textFieldTipo.setHint("Nombre del tipo de operación. Ej.: Gasto");
        textFieldTipo.setIcon(MaterialIcons.PRIORITY_HIGH);

        //cuenta
        cuentaDefecto = new TipoCuentaICBS();
        cuentaDefecto.setLabel("Tipo de cuenta por defecto");

        //cuadre
        cuentaDefectoCuadre = new TipoCuentaICBS();
        cuentaDefectoCuadre.setLabel("Tipo de cuenta de cuadre");

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(textFieldTipo);
        vlc.add(cuentaDefecto);
        vlc.add(cuentaDefectoCuadre);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialTextFieldIcon textFieldTipo;
    private TipoCuentaICBS cuentaDefecto;
    private TipoCuentaICBS cuentaDefectoCuadre;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("nombreOperacion", textFieldTipo);
        bindFields.put("tipoCuentaDefectoFk", cuentaDefecto);
        bindFields.put("tipoCuentaCuadreDefectoFk", cuentaDefectoCuadre);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }

}

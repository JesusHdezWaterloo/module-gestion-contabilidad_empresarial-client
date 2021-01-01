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
package com.root101.module.gestion.contabilidad.ui.cuadre.pedazos;

import com.jhw.swing.material.components.container.layout.VerticalLayoutContainer;
import com.jhw.swing.models.clean.CleanCRUDInputView;
import com.root101.module.gestion.contabilidad.core.domain.facade.FechaDescUI;
import com.root101.module.gestion.contabilidad.ui.forma_pago.FormaPagoICBS;
import com.jhw.swing.material.components.datepicker.MaterialDatePickerIcon;
import com.jhw.swing.material.components.datepicker.MaterialDatePickersFactory;
import com.jhw.swing.material.components.textarea.MaterialTextArea;
import com.jhw.swing.prepared.textarea.MaterialPreparedTextAreaFactory;
import java.util.Map;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class FechaDescInputView extends CleanCRUDInputView<FechaDescUI> {

    public static FechaDescInputView from() {
        return new FechaDescInputView(null);
    }

    private FechaDescInputView(FechaDescUI model) {
        super(model, null, FechaDescUI.class);
        initComponents();
        update();
    }

    private void initComponents() {
        setHeader("", "");

        //fecha
        datePickerFecha = MaterialDatePickersFactory.buildIcon();
        datePickerFecha.setLabel("Fecha");

        //forma de pago
        formaPagoICBS = new FormaPagoICBS();

        //descripcion
        textAreaDescripcion = MaterialPreparedTextAreaFactory.buildDescripcion();

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(datePickerFecha);
        vlc.add(formaPagoICBS);
        vlc.add(textAreaDescripcion, true);

        this.setComponent(vlc.build());
    }

    // Variables declaration - do not modify
    private MaterialDatePickerIcon datePickerFecha;
    private FormaPagoICBS formaPagoICBS;
    private MaterialTextArea textAreaDescripcion;
    // End of variables declaration                   

    @Override
    public Map<String, Object> bindFields() {
        Map<String, Object> bindFields = super.bindFields();
        bindFields.put("fecha", datePickerFecha);
        bindFields.put("pago", formaPagoICBS);
        bindFields.put("descripcion", textAreaDescripcion);
        return bindFields;
    }
}

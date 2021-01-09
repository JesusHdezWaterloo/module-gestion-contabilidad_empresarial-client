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
package com.root101.module.gestion.contabilidad;

import com.root101.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.root101.module.util.default_config.DefaultConfigSwingModule;
import com.root101.module.util.personalization.ui.module.PersonalizationSwingModule;
import com.root101.swing.ui.MaterialLookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MainTest {

    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        PersonalizationSwingModule.init();
        DefaultConfigSwingModule.init();

        ContabilidadSwingModule.init();

        System.out.println("antes");
        System.out.println(ContabilidadSwingModule.cuadreUC.findAll());
        System.out.println("ya");
    }
}

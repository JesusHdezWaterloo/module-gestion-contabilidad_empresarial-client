/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad;

import com.jhw.module.gestion.contabilidad.ui.module.ContabilidadSwingModule;
import com.jhw.module.util.default_config.DefaultConfigSwingModule;
import com.jhw.module.util.personalization.ui.module.PersonalizationSwingModule;
import com.jhw.swing.ui.MaterialLookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MainTest {

    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        PersonalizationSwingModule.init();
        DefaultConfigSwingModule.init();

        ContabilidadSwingModule.init();
        
        System.out.println("antes");
        System.out.println(ContabilidadSwingModule.monedaUC.findAll().get(0).getVenta());
        System.out.println("ya");
    }
}

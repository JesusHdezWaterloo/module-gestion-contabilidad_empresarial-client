/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.repo_impl;

import com.jhw.example.spring_a.core.SpringAConstants;
import com.jhw.example.spring_a.core.domain.TrabajoDomain;
import com.jhw.example.spring_a.core.usecase_def.TrabajoUseCase;
import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.ConsumerRepoTemplate;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class TrabajoRepoImpl extends ConsumerRepoTemplate<TrabajoDomain> implements TrabajoUseCase {

    public TrabajoRepoImpl() {
        super(RESTHandler.restTemplate(), TrabajoDomain.class, RESTHandler.urlActual() + SpringAConstants.PATH_TRABAJO_GENERAL);
    }

    @Override
    public List<TrabajoDomain> upperBasico() throws Exception {
        ParameterizedTypeReference<List<TrabajoDomain>> type = new ParameterizedTypeReference<List<TrabajoDomain>>() {
        };
        ResponseEntity<List<TrabajoDomain>> response = template.exchange(urlGeneral + SpringAConstants.PATH_TRABAJO_UPPER_BASICO, HttpMethod.GET, null, type);
        return response.getBody();
    }

}

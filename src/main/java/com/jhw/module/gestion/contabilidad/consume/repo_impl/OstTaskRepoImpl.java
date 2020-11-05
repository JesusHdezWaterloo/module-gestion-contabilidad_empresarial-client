/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.repo_impl;

import com.jhw.module.util.rest_config.services.RESTHandler;
import com.jhw.utils.spring.client.RestTemplateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class OstTaskRepoImpl implements OstTaskUseCase {

    protected final RestTemplate template = RESTHandler.restTemplate();
    protected final String urlGeneral = RESTHandler.urlActualREST()+ PlanTrabajoConstants.TASK_PATH;

    public OstTaskRepoImpl() {
    }

    @Override
    public List<OstTaskDomain> findTasks(int staff_id, Date month) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(PlanTrabajoConstants.STAFF_ID, staff_id);
        map.put(PlanTrabajoConstants.MONTH, PlanTrabajoConstants.SDF_MONTH.format(month));
        return RestTemplateUtils.getForList(template, urlGeneral + PlanTrabajoConstants.TASK_FIND_TASKTS_PATH, map, OstTaskDomain.class);
    }

    @Override
    public PlanTrabajo findPlan(String user_all, Date month) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(PlanTrabajoConstants.USER_ALL, user_all);
        map.put(PlanTrabajoConstants.MONTH, PlanTrabajoConstants.SDF_MONTH.format(month));
        return template.getForObject(urlGeneral + PlanTrabajoConstants.TASK_FIND_PLAN_PATH, PlanTrabajo.class, map);
    }

}

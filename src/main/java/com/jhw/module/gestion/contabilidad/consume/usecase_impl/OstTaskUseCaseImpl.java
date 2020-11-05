/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.module.gestion.contabilidad.consume.usecase_impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class OstTaskUseCaseImpl implements OstTaskUseCaseConsume {

    private final OstTaskRepoImpl repoUC = PlanTrabajoConsumeCoreModule.getInstance().getImplementation(OstTaskRepoImpl.class);

    public OstTaskUseCaseImpl() {
    }

    @Override
    public List<OstTaskDomain> findTasks(int staff_id, Date month) throws Exception {
        return repoUC.findTasks(staff_id, month);
    }

    @Override
    public PlanTrabajo findPlan(String user_all, Date month) throws Exception {
        return repoUC.findPlan(user_all, month);
    }

    @Override
    public List<TaskFixed> tasksOfDay(int day, PlanTrabajo plan) {
        return plan.getTasks().stream().filter(
                (TaskFixed t) -> t.getDuedate().getDate() == day
        ).sorted((TaskFixed o1, TaskFixed o2) -> {
            return o1.getDuedate().compareTo(o2.getDuedate());
        }).collect(Collectors.toList());
    }
}

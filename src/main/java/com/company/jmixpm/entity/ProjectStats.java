package com.company.jmixpm.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

@JmixEntity(annotatedPropertiesOnly = true)
public class ProjectStats {

    @JmixProperty
    private String projectName;

    @JmixProperty
    private Integer tasksCount;

    @JmixProperty
    private Integer plannedEfforts;

    @JmixProperty
    private Integer actualEfforts;

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlannedEfforts() {
        return plannedEfforts;
    }

    public void setPlannedEfforts(Integer plannedEfforts) {
        this.plannedEfforts = plannedEfforts;
    }

    public Integer getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
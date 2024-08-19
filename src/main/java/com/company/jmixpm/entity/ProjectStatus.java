package com.company.jmixpm.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ProjectStatus implements EnumClass<Integer> {

    NEW(10),
    IN_PROGRESS(20),
    COMPLETED(30);

    private final Integer id;

    ProjectStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ProjectStatus fromId(Integer id) {
        for (ProjectStatus at : ProjectStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
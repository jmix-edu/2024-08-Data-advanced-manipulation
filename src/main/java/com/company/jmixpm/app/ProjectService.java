package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import io.jmix.core.DataManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ProjectService {

    private final DataManager dataManager;

    public ProjectService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void save(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}
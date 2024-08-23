package com.company.jmixpm.validation;

import com.company.jmixpm.entity.Project;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class ValidDatesProjectValidator implements ConstraintValidator<ValidDatesProject, Project> {

    @Override
    public boolean isValid(Project project, ConstraintValidatorContext context) {
        if (project == null) {
            return false;
        }

        LocalDateTime startDate = project.getStartDate();
        LocalDateTime endDate = project.getEndDate();

        if (startDate == null || endDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }
}

package com.company.jmixpm.validation;

import com.company.jmixpm.datatype.ProjectLabels;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ProjectLabelsSizeValidator implements ConstraintValidator<ProjectLabelsSize, ProjectLabels> {

    private int min;
    private int max;

    @Override
    public void initialize(ProjectLabelsSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(ProjectLabels value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        List<String> labels = value.getLabels();

        return labels.size() >= min && labels.size() <= max;
    }
}

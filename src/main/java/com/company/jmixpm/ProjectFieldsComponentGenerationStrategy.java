package com.company.jmixpm;

import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.datatype.ProjectLabelsDatatype;
import com.company.jmixpm.entity.Project;
import com.vaadin.flow.component.Component;
import io.jmix.core.JmixOrder;
import io.jmix.core.metamodel.datatype.Datatype;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.core.metamodel.model.MetaProperty;
import io.jmix.core.metamodel.model.Range;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.ComponentGenerationContext;
import io.jmix.flowui.component.ComponentGenerationStrategy;
import io.jmix.flowui.component.textfield.TypedTextField;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

@org.springframework.stereotype.Component
public class ProjectFieldsComponentGenerationStrategy implements ComponentGenerationStrategy, Ordered {

    private UiComponents uiComponents;

    public ProjectFieldsComponentGenerationStrategy(UiComponents uiComponents) {
        this.uiComponents = uiComponents;
    }

    @Nullable
    @Override
    public Component createComponent(ComponentGenerationContext context) {
        if (!"projectLabels".equals(context.getProperty())) {
            return null;
        }

        MetaClass metaClass = context.getMetaClass();
        if (!metaClass.getJavaClass().equals(Project.class)) {
            return null;
        }

        MetaProperty property = metaClass.getProperty(context.getProperty());
        Range range = property.getRange();

        if (range.isDatatype()) {
            Datatype projectLabelsDataType = range.asDatatype();
            if (projectLabelsDataType instanceof ProjectLabelsDatatype) {
                return create(context);
            }
        }
        return null;
    }

    private Component create(ComponentGenerationContext context) {
        TypedTextField<ProjectLabels> component = uiComponents.create(TypedTextField.class);
        component.setValueSource(context.getValueSource());
        component.setReadOnly(true);
        return component;
    }

    @Override
    public int getOrder() {
        return JmixOrder.HIGHEST_PRECEDENCE + 10;
    }
}

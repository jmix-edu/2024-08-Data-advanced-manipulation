package com.company.jmixpm.view.project;

import com.company.jmixpm.app.ProjectService;
import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.entity.Project;

import com.company.jmixpm.entity.Roadmap;
import com.company.jmixpm.view.main.MainView;

import com.company.jmixpm.view.user.UserListView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController("Project.detail")
@ViewDescriptor("project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @ViewComponent
    private DataContext dataContext;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private TypedTextField<ProjectLabels> projectLabelsField;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Validator validator;
    @ViewComponent
    private TypedTextField<String> nameField;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {
        Roadmap roadmap = dataContext.create(Roadmap.class);
        event.getEntity().setRoadmap(roadmap);

        projectLabelsField.setReadOnly(false);
        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "enhancement", "task")));

    }

    @Subscribe(id = "commitWithBeanValidation", subject = "clickListener")
    public void onCommitWithBeanValidationClick(final ClickEvent<JmixButton> event) {
        try {
            projectService.save(getEditedEntity());
            close(StandardOutcome.CLOSE);
        } catch (ConstraintViolationException e) {

            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }

            notifications.create(sb.toString())
                    .withPosition(Notification.Position.TOP_CENTER)
                    .show();
        }
    }

    @Subscribe(id = "performBeanValidationBtn", subject = "clickListener")
    public void onPerformBeanValidationBtnClick(final ClickEvent<JmixButton> event) {
       Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity(),
               Default.class, UiComponentChecks.class);

       showExceptions(violations);
    }

    private void showExceptions(Set<ConstraintViolation<Project>> constraintViolations) {
        StringBuilder sb = new StringBuilder();

        for (ConstraintViolation<Project> constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }

        notifications.create(sb.toString())
                .withPosition(Notification.Position.TOP_CENTER)
                .show();

    }

    @Install(to = "participantsDataGrid.add", subject = "viewConfigurer")
    private void participantsDataGridAddViewConfigurer(final UserListView userListView) {
            userListView.setFilterProject(getEditedEntity());
    }
}

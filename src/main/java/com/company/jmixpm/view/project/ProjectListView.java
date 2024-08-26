package com.company.jmixpm.view.project;

import com.company.jmixpm.datatype.ProjectLabels;
import com.company.jmixpm.entity.Project;

import com.company.jmixpm.entity.Roadmap;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "projects", layout = MainView.class)
@ViewController("Project.list")
@ViewDescriptor("project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {

    @ViewComponent
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe(id = "dmCreate", subject = "clickListener")
    public void onDmCreateClick(final ClickEvent<JmixButton> event) {
        Project project = dataManager.create(Project.class);
        project.setName("Project " + RandomStringUtils.randomAlphabetic(5));

        User user = (User) currentAuthentication.getUser();
        project.setManager(user);
        Roadmap roadmap = dataManager.create(Roadmap.class);
        roadmap.setName("Roadmap: " + project.getName());
        project.setRoadmap(roadmap);
        // Bean validation is invoked while saving entity instance
        project.setProjectLabels(new ProjectLabels(List.of("task", "enhancement", "bug")));

        Project saved = unconstrainedDataManager.save(project, roadmap).get(project);
        projectsDc.getMutableItems().add(saved);
    }
}
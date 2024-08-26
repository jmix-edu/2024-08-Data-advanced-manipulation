package com.company.jmixpm.view.userprojectsdialog;


import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "user-projects-dialog", layout = MainView.class)
@ViewController("UserProjectsDialog")
@ViewDescriptor("user-projects-dialog.xml")
@DialogMode(width = "50em", height = "37.5em")
public class UserProjectsDialog extends StandardView {

    @ViewComponent
    private CollectionLoader<Project> projectDl;

    private User user;

    public User getUser() {
        return user;
    }

    public UserProjectsDialog withUser(User user) {
        this.user = user;

        projectDl.setParameter("user", user);
        projectDl.load();

        return this;
    }
}
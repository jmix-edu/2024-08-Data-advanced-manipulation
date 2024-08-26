package com.company.jmixpm.view.task;

import com.company.jmixpm.entity.Task;
import com.company.jmixpm.repository.TaskRepository;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static io.jmix.core.repository.JmixDataRepositoryUtils.buildPageRequest;
import static io.jmix.core.repository.JmixDataRepositoryUtils.buildRepositoryContext;

@Route(value = "tasks-repo", layout = MainView.class)
@ViewController("Task_repo.list")
@ViewDescriptor("task-repo-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskRepoListView extends StandardListView<Task> {

    @Autowired
    private TaskRepository repository;

    @Install(to = "tasksDl", target = Target.DATA_LOADER)
    private List<Task> loadDelegate(LoadContext<Task> context) {
        return repository.findAll(buildPageRequest(context), buildRepositoryContext(context)).getContent();
    }

    @Install(to = "tasksDataGrid.remove", subject = "delegate")
    private void tasksDataGridRemoveDelegate(final Collection<Task> collection) {
        repository.deleteAll(collection);
    }
}
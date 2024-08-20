package com.company.jmixpm.view.workloadinfo;


import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.entity.KeyValueEntity;
import io.jmix.flowui.model.KeyValueContainer;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "workload-info-view", layout = MainView.class)
@ViewController("WorkloadInfoView")
@ViewDescriptor("workload-info-view.xml")
public class WorkloadInfoView extends StandardView {

    @ViewComponent
    private KeyValueContainer workloadDc;

    public WorkloadInfoView withItem (KeyValueEntity item) {
        workloadDc.setItem(item);
        return this;
    }
}
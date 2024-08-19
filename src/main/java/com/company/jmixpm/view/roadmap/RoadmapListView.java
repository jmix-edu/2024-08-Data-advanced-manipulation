package com.company.jmixpm.view.roadmap;

import com.company.jmixpm.entity.Roadmap;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "roadmaps", layout = MainView.class)
@ViewController("Roadmap.list")
@ViewDescriptor("roadmap-list-view.xml")
@LookupComponent("roadmapsDataGrid")
@DialogMode(width = "64em")
public class RoadmapListView extends StandardListView<Roadmap> {
}
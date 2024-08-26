package com.company.jmixpm.view.customsearh;


import com.company.jmixpm.entity.Customer;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "custom-searh-view", layout = MainView.class)
@ViewController("CustomSearhView")
@ViewDescriptor("custom-searh-view.xml")
public class CustomSearhView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(CustomSearhView.class);
    @ViewComponent
    private CollectionContainer<Customer> customersDc;

    @Subscribe
    public void onInit(final InitEvent event) {
        log.info("InitEvent - cstomers size: " + customersDc.getItems().size());
    }
    
    
}
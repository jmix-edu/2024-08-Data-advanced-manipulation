package com.company.jmixpm.view.order;

import com.company.jmixpm.entity.Order;
import com.company.jmixpm.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "orders", layout = MainView.class)
@ViewController("Order_.list")
@ViewDescriptor("order-list-view.xml")
@LookupComponent("ordersDataGrid")
@DialogMode(width = "64em")
public class OrderListView extends StandardListView<Order> {
}
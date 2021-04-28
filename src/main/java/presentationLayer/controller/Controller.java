package presentationLayer.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentationLayer.view.FxmlLoader;
import presentationLayer.view.Interface;

/**
 *  Controller class used for the main stage of the application and for managing the choice of the next stage
 *  @author Anca
 */
public class Controller {

    public Button btnCustomers;
    public Button btnProducts;
    public Button btnOrders;
    public BorderPane mainPane;

    /**
     * starts the application by launching the start method from the Interface
     */
    public void start(){ new Thread(() -> Application.launch(Interface.class)).start(); }

    /**
     * Method to open window for managing customers
     * @param actionEvent
     */
    public void manageCustomers(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("customers");
        mainPane.setCenter(view);
    }

    /**
     * Method to open window for managing products
     * @param actionEvent
     */
    public void manageProducts(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("products");
        mainPane.setCenter(view);
    }

    /**
     * Method to open window for managing orders
     * @param actionEvent
     */
    public void manageOrders(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("orders");
        mainPane.setCenter(view);
    }
}

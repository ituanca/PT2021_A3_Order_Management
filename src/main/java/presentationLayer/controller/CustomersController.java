package presentationLayer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentationLayer.view.FxmlLoader;

/**
 *  Controller class used for the customers management stage
 *  @author Anca
 */
public class CustomersController {

    public BorderPane customersPane;
    public Button btnAddCustomer;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
    public Button btnViewCustomersTable;

    /**
     * Method to open window where user can insert data of a new customer
     * @param actionEvent
     */
    public void addCustomer(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addCustomer");
        customersPane.setCenter(view);
    }

    /**
     * Method to open window where user can update data of an existing customer
     * @param actionEvent
     */
    public void editCustomer(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("editCustomer");
        customersPane.setCenter(view);
    }

    /**
     * Method to open window where user can select a customer to be deleted from the table
     * @param actionEvent
     */
    public void deleteCustomer(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("deleteCustomer");
        customersPane.setCenter(view);
    }

    /**
     * Method to open window where user can view all data in the table
     * @param actionEvent
     */
    public void viewCustomersTable(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("tableCustomers");
        customersPane.setCenter(view);
    }

}

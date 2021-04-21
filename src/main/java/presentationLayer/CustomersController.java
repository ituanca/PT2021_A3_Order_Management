package presentationLayer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CustomersController {

    public BorderPane customersPane;
    public Button btnAddCustomer;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
    public Button btnViewCustomersTable;

    public void addCustomer(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("addCustomer");
        customersPane.setCenter(view);
    }

    public void editCustomer(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("editCustomer");
        customersPane.setCenter(view);
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void viewCustomersTable(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) {
    }
}

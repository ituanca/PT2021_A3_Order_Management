package presentationLayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Controller {

    public Button btnCustomers;
    public Button btnProducts;
    public Button btnOrders;
    public BorderPane mainPane;

    public void start(){ new Thread(() -> Application.launch(Interface.class)).start(); }

    public void manageCustomers(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("customers");
        mainPane.setCenter(view);
    }

    public void manageProducts(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("products");
        mainPane.setCenter(view);
    }

    public void manageOrders(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("orders");
        mainPane.setCenter(view);
    }
}

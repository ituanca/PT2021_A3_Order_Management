package presentationLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectsController<T> {

    public void createTable(ObservableList<T> objects, ArrayList<TableColumn<Object, String>> columns, ScrollPane scrollPane, TableView tableView ) throws SQLException {
        int index = 0;
        for (Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                columns.add(new TableColumn<>(field.getName()));
                columns.get(index).setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                index++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        tableView.getColumns().addAll(columns);
        tableView.setItems(objects);
        scrollPane.setContent(tableView);
    }

}

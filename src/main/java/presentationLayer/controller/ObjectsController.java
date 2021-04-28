package presentationLayer.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Controller class used for creating tables through a reflection mechanism
 *  @author Anca
 */
public class ObjectsController<T> {

    /**
     * Method to create table to be displayed in the application interface using reflection
     * @param objects
     * @param columns
     * @param scrollPane
     * @param tableView
     * @throws SQLException
     */
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

package start;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionExample {

    public static void retrieveProperties( ObservableList<Object> objects, ArrayList<TableColumn<Object, Object>> columns) {
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
    }

}

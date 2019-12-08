import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.List;

public class TableViewHelper extends TableView {

    public TableViewHelper(){
    }

    public TableViewHelper(List<?> list){
        for(Object o : list){

        }
        list.forEach(o -> {
            for(Field field : o.getClass().getDeclaredFields()){
                Column c = field.getAnnotation(Column.class);
                if(c!=null){
                    TableColumn a = new TableColumn(c.name());
                    a.setCellValueFactory(new PropertyValueFactory<>(c.name()));
                    this.getColumns().add(a);
                }
            }
        });
    }
}

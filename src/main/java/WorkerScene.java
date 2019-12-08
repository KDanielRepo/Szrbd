import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

import java.util.List;

public class WorkerScene extends BorderPane {
    public WorkerScene(){
        this.setPrefSize(1000, 800);
        //oznacz tryby ktory jest aktywny, user czy administrator
        /*Session session = HibernateSession.getSessionFactory().openSession();
        List<Kierowcy> kierowcies = session.createQuery("from Kierowcy",Kierowcy.class).list();
        final ObservableList<Kierowcy> data = FXCollections.observableArrayList(kierowcies);
        System.out.println(data.get(1).getIdAutokaru());*/
        TableView tableView = new TableView();
        TableColumn one = new TableColumn("Imie");
        one.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        TableColumn two = new TableColumn("Nazwisko");
        two.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        TableColumn three = new TableColumn("Pensja");
        three.setCellValueFactory(new PropertyValueFactory<>("Pensja"));
        TableColumn four = new TableColumn("Autokar");
        four.setCellValueFactory(new PropertyValueFactory<>("Autokar"));
        //tableView.setItems(data);
        tableView.getColumns().addAll(one,two,three,four);

        VBox vBox = new VBox();
        /*vBox.setPadding(new Insets(200,0,0,0));
        Label from = new Label("Trasa z: ");
        TextArea a = new TextArea();
        a.setPrefSize(100,100);
        Label to = new Label("Trasa do: ");
        TextArea b = new TextArea();
        b.setPrefSize(100,100);
        vBox.getChildren().addAll(from,a,to,b);*/
        vBox.setVisible(false);

        MenuBar menuBar = new MenuBar();
        Menu edit = new Menu("Edycja");
        MenuItem add = new MenuItem("Dodaj rekord");
        MenuItem delete = new MenuItem("Usuń rekord");
        MenuItem save = new MenuItem("Zapisz zmiany");
        MenuItem discard = new MenuItem("Odrzuć zmiany");
        edit.getItems().addAll(add,delete,save,discard);

        Menu navigate = new Menu("Nawigacja");
        MenuItem kurs = new MenuItem("Lista kursów");
        kurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        MenuItem drivers = new MenuItem("Lista kierowców");
        drivers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Session session = HibernateSession.getSessionFactory().openSession();
                List<Kierowcy> kierowcies = session.createQuery("from Kierowcy",Kierowcy.class).list();

            }
        });
        MenuItem kursy = new MenuItem("Lista Kursów");
        kursy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Session session = HibernateSession.getSessionFactory().openSession();
                //List<Kierowcy> kierowcies = session.createQuery("from Kursy",Kursy.class).list();

            }
        });
        MenuItem autokary = new MenuItem("Lista Autokarów");
        autokary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Session session = HibernateSession.getSessionFactory().openSession();
                List<Autokary> autokars = session.createQuery("from Autokary",Autokary.class).list();
                final ObservableList<Autokary> data = FXCollections.observableArrayList(autokars);*/
                WorkerScene.this.getChildren().remove(tableView);
                /*TableViewHelper a = new TableViewHelper(data);
                a.setItems(data);
                AdminScene.this.setCenter(a);*/

            }
        });
        navigate.getItems().addAll(drivers,autokary,kursy);

        Menu raports = new Menu("Generacja raportów");
        MenuItem week = new MenuItem("Tygodniowy");
        MenuItem month = new MenuItem("Miesięczny");
        raports.getItems().addAll(week,month);

        menuBar.getMenus().addAll(edit,navigate,raports);

        this.setLeft(vBox);
        this.setCenter(tableView);
        this.setTop(menuBar);

    }
}

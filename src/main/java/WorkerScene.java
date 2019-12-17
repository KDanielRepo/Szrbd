import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class WorkerScene extends BorderPane {
    TableViewHelper tableView = new TableViewHelper();
    //dodaj liste biletow
    //przemapuj id na nazwy
    public void tableCreate(Object o){
        Session session = HibernateSession.getSessionFactory().openSession();
        List<?> list = session.createQuery("from "+o.getClass().getName(),o.getClass()).list();
        final ObservableList<?> data = FXCollections.observableArrayList(list);
        this.getChildren().remove(tableView);
        try {
            tableView = new TableViewHelper(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tableView.setItems(data);
        this.setCenter(tableView);
    }
    public void tableTransaction(List<?> a){
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSession.getSessionFactory().openSession();
            tx = session.beginTransaction();
            for (int i = 0; i < a.size(); i++) {
                if(session.get(a.get(i).getClass(),new Integer(i+1))!=null){
                    session.merge(a.get(i));
                }else{
                    session.save(a.get(i));
                }
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            session.close();
        }
    }
    public void removeRecord(Integer a,Object b){
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateSession.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Object o = session.get(b.getClass(),(a+1));
            session.delete(o);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }
        finally {
            session.close();
        }
    }
    public WorkerScene(Stage stage){
        this.setPrefSize(1000, 800);
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
        vBox.setVisible(false);

        MenuBar menuBar = new MenuBar();
        Menu edit = new Menu("Edycja");
        MenuItem add = new MenuItem("Dodaj rekord");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Class<?> clazz = tableView.getItems().get(0).getClass();
                    Object obj = clazz.newInstance();
                    ObservableList obl = FXCollections.observableArrayList(tableView.getItems());
                    obl.add(obj);
                    WorkerScene.this.getChildren().remove(tableView);
                    tableView = new TableViewHelper(obl);
                    tableView.setItems(obl);
                    WorkerScene.this.setCenter(tableView);
                }catch (IllegalAccessException | InstantiationException e){

                }

            }
        });
        MenuItem delete = new MenuItem("Usuń rekord");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Class<?> clazz = tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()).getClass();
                Object o;
                removeRecord(tableView.getSelectionModel().getFocusedIndex(),tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()));
                try {
                    tableCreate(o = clazz.newInstance());
                }catch (IllegalAccessException | InstantiationException e){
                    e.printStackTrace();
                }
            }
        });
        MenuItem save = new MenuItem("Zapisz zmiany");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableTransaction(tableView.prepareList());
            }
        });
        MenuItem discard = new MenuItem("Odrzuć zmiany");
        edit.getItems().addAll(add,delete,save,discard);

        Menu navigate = new Menu("Nawigacja");
        MenuItem drivers = new MenuItem("Lista kierowców");
        drivers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableCreate(new Kierowcy());

            }
        });
        MenuItem users = new MenuItem("Lista użytkowników");
        users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Session session = HibernateSession.getSessionFactory().openSession();
                List<Kierowcy> kierowcies = session.createQuery("from Kierowcy",Kierowcy.class).list();
                final ObservableList<Kierowcy> data = FXCollections.observableArrayList(kierowcies);*/
                WorkerScene.this.getChildren().remove(tableView);
                /*tableView = new TableViewHelper(data);
                tableView.setItems(data);
                WorkerScene.this.setCenter(tableView);*/

            }
        });
        MenuItem kursy = new MenuItem("Lista Kursów");
        kursy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableCreate(new Kursy());

            }
        });
        MenuItem autokary = new MenuItem("Lista Autokarów");
        autokary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableCreate(new Autokary());
            }
        });

        navigate.getItems().addAll(drivers,autokary,kursy);

        Menu raports = new Menu("Generacja raportów");
        MenuItem week = new MenuItem("Tygodniowy");
        MenuItem month = new MenuItem("Miesięczny");
        raports.getItems().addAll(week,month);

        Menu account = new Menu("Moje konto");
        MenuItem info = new MenuItem("Moje dane");
        MenuItem logout = new MenuItem("Wyloguj");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Method a = Ui.class.getDeclaredMethod("ini", Stage.class);
                    Ui ui = new Ui();
                    a.invoke(ui,stage);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        account.getItems().addAll(info,logout);

        menuBar.getMenus().addAll(edit,navigate,raports,account);

        this.setLeft(vBox);
        this.setCenter(tableView);
        this.setTop(menuBar);

    }
}

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class UserScene extends BorderPane {
    TableViewHelper tableView = new TableViewHelper();
    TextField a;
    TextField b;
    public void tableCreate(Object o){
        Session session = HibernateSession.getSessionFactory().openSession();
        List<?> list = session.createQuery("from "+o.getClass().getName(),o.getClass()).list();
        ObservableList<?> data = FXCollections.observableArrayList(list);
        this.getChildren().remove(tableView);
        try {
            tableView = new TableViewHelper(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        tableView.setItems(data);
        this.setCenter(tableView);
    }
    public void searchFor(){
        Session session = HibernateSession.getSessionFactory().openSession();
        String query = "select Id_kursy,Trasy.Id_trasy,Data_odjazdu,Data_przyjazdu from Kursy\n" +
                "inner join Trasy on Kursy.Id_trasy = Trasy.Id_trasy\n" +
                "inner join Miejscowosci on Trasy.Id_miejscowosci = Miejscowosci.Id_miejscowosci\n" +
                "inner join Miejscowosci m on Trasy.Mie_Id_miejscowosci = m.Id_miejscowosci\n" +
                "where Miejscowosci.Nazwa_miejsc = '"+a.getText()+"' and m.Nazwa_miejsc = '"+b.getText()+"'\n";
        List<?> list = session.createNativeQuery(query,Kursy.class).list();
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
    public void showTickets(Kursy k){
        Session session = null;
        if(k != null){
            session = HibernateSession.getSessionFactory().openSession();
            String query = "select Siedzenie.Id_siedzenia,Siedzenie.Id_biletu,Siedzenie.Id_autokaru from Kursy\n" +
                    "inner join Autokar_Kurs on Kursy.Id_kursy = Autokar_Kurs.Id_kursy\n" +
                    "inner join Autokary on Autokar_Kurs.Id_autokaru = Autokary.Id_autokaru\n" +
                    "inner join Siedzenie on Autokary.Id_autokaru = Siedzenie.Id_autokaru\n" +
                    "where Kursy.Id_kursy = "+k.getIdKursu()+" and Siedzenie.Id_biletu is null ";
            List<?> list = session.createNativeQuery(query,Siedzenie.class).list();
            ObservableList<?> data = FXCollections.observableArrayList(list);
            this.getChildren().remove(tableView);
            try {
                tableView = new TableViewHelper(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            tableView.setItems(data);
            this.setCenter(tableView);
        }
    }
    public void buyTicket(Siedzenie siedzenie){
        Session session = null;
        Transaction tx = null;
        int test = 1;
        if(siedzenie!=null){
            try {
                session = HibernateSession.getSessionFactory().openSession();
                tx = session.beginTransaction();
                while (session.get(Bilet.class,test)!=null){
                    test++;
                }
                Bilet bilet = new Bilet(test,siedzenie.getIdSiedzenia(),2.50f);
                session.save(bilet);
                tx.commit();
            }catch (Exception e){
                tx.rollback();
            }finally {
                session.close();
            }
        }else{
            System.out.println("pusto");
        }
    }
    public UserScene(Stage stage){
        this.setPrefSize(1000, 800);
        //oznacz tryby ktory jest aktywny, user czy administrator

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30,0,0,0));
        Label from = new Label("Trasa z: ");
        a = new TextField();
        a.setPrefSize(100,100);
        Label to = new Label("Trasa do: ");
        b = new TextField();
        b.setPrefSize(100,100);
        Button search = new Button("Szukaj");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchFor();
            }
        });
        Button checkTickets = new Button("Sprawdź miejsca");
        checkTickets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showTickets((Kursy) tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()));
            }
        });
        Button buy = new Button("Zamów bilet");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buyTicket((Siedzenie)tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex()));
            }
        });
        vBox.getChildren().addAll(from,a,to,b,search,checkTickets,buy);
        vBox.setVisible(true);

        MenuBar menuBar = new MenuBar();

        Menu navigate = new Menu("Nawigacja");
        MenuItem kursy = new MenuItem("Lista Kursów");
        kursy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tableCreate(new Kursy());
            }
        });
        navigate.getItems().addAll(kursy);

        Menu account = new Menu("Moje konto");
        //MenuItem info = new MenuItem("Moje dane");
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
        account.getItems().addAll(logout);

        menuBar.getMenus().addAll(navigate,account);

        this.setLeft(vBox);
        this.setCenter(tableView);
        this.setTop(menuBar);
    }
}

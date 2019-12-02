import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application {
    BorderPane login;
    BorderPane admin;
    BorderPane user;


    public void prepareLoginScene(Scene scene){
        login = new BorderPane();
        login.setPrefSize(1000, 800);
        login.setPadding(new Insets(300,300,300,300));
        scene.setRoot(login);
        VBox vBox = new VBox();
        Label loginLabel = new Label("login: ");
        TextArea loginArea = new TextArea();
        loginArea.setPrefSize(150,150);
        Label passwordLabel = new Label("password: ");
        TextArea passwordArea = new TextArea();
        passwordArea.setPrefSize(150,150);
        Label status = new Label();
        Button button = new Button("connect");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(loginArea.getText().equals("a")&&passwordArea.getText().equals("b")){
                    status.setText("nice");
                    prepareAdminScene();
                    scene.setRoot(admin);
                }else if(loginArea.getText().equals("a")&&passwordArea.getText().equals("c")){
                    prepareUserScene();
                    scene.setRoot(user);
                }else{
                    status.setText("not nice");
                }
            }
        });
        vBox.getChildren().add(loginLabel);
        vBox.getChildren().add(loginArea);
        vBox.getChildren().add(passwordLabel);
        vBox.getChildren().add(passwordArea);
        vBox.getChildren().add(button);
        vBox.getChildren().add(status);
        login.setCenter(vBox);
    }
    public void prepareUserScene(){
        user = new BorderPane();
        user.setPrefSize(1000, 800);
        HBox hBox = new HBox();
        Button button = new Button("lista kursów");
        Button button1 = new Button("kup bilet");
        //Button button2 = new Button("cos jeszcze");
        hBox.getChildren().add(button);
        hBox.getChildren().add(button1);
        //hBox.getChildren().add(button2);
        user.setTop(hBox);

        VBox center = new VBox();
        Label label = new Label("Lista kursów: ");
        center.getChildren().add(label);
        user.setCenter(center);
        VBox left = new VBox();
        left.setPrefSize(200,200);
        Label label1 = new Label("kurs z: ");
        Label label2 = new Label("kurs do: ");
        TextArea area = new TextArea();
        area.setPrefSize(200,100);
        TextArea area1 = new TextArea();
        area1.setPrefSize(200,100);
        left.getChildren().add(label1);
        left.getChildren().add(area);
        left.getChildren().add(label2);
        left.getChildren().add(area1);
        user.setLeft(left);
        //user.setCenter(button);
    }
    public void prepareAdminScene(){
        admin = new BorderPane();
        admin.setPrefSize(1000, 800);

        TableView tableView = new TableView();
        TableColumn one = new TableColumn("Imie");
        TableColumn two = new TableColumn("Nazwisko");
        TableColumn three = new TableColumn("Pensja");
        TableColumn four = new TableColumn("Autokar");
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

            }
        });
        navigate.getItems().addAll(kurs,drivers);

        Menu raports = new Menu("Generacja raportów");
        MenuItem week = new MenuItem("Tygodniowy");
        MenuItem month = new MenuItem("Miesięczny");
        raports.getItems().addAll(week,month);

        menuBar.getMenus().addAll(edit,navigate,raports);

        admin.setLeft(vBox);
        admin.setCenter(tableView);
        admin.setTop(menuBar);
    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        VBox a = new VBox();
        Scene scene = new Scene(borderPane);
        prepareLoginScene(scene);
        Button button = new Button("test");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot(a);
            }
        });
        Button button1 = new Button("test2");

        a.getChildren().add(button1);
        borderPane.setCenter(button);
        borderPane.setPrefSize(1000, 800);
        //Scene scene = new Scene(borderPane);
        primaryStage.setTitle("SZRBD");
        primaryStage.setScene(scene);
        primaryStage.show();
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Scene scene = new Scene(borderPane);
                scene.setRoot(borderPane);
            }
        });
    }

    public static void main(String[] args) {
        launch(Ui.class,args);
    }
}

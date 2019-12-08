import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.List;

public class Ui extends Application {
    private LoginScene login;
    private AdminScene admin;
    private WorkerScene worker;
    private UserScene user;
    private Stage pStage;


    public void prepareLoginScene(Scene scene) {
        login = new LoginScene(scene);
        login.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if  (login.getLoginArea().getText().equals("a") && (login.getPasswordArea().getText().equals("b"))) {
                    prepareAdminScene();
                    scene.setRoot(admin);
                } else if (login.getLoginArea().getText().equals("a") && ( login.getPasswordArea().getText().equals("c"))) {
                    prepareUserScene();
                    scene.setRoot(user);
                } else if(login.getLoginArea().getText().equals("a") && ( login.getPasswordArea().getText().equals("d"))){
                    prepareWorkerScene();
                    scene.setRoot(worker);
                }else{
                    //status.setText("not nice");
                }
            }
        });
    }

    public void prepareUserScene() {
        user = new UserScene();
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: UŻYTKOWNIK");
        //user.setCenter(button);
    }

    public void prepareAdminScene() {
        admin = new AdminScene();
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: ADMINISTRATOR");

    }
    public void prepareWorkerScene(){
        worker = new WorkerScene();
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: PRACOWNIK");
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
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
        launch(Ui.class, args);
    }
}

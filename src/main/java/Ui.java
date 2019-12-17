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
    private Scene scene;

    public void prepareLoginScene(Scene scene) {
        login = new LoginScene(scene);
        login.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if  (login.getLoginArea().getText().equals("admin") && (login.getPasswordArea().getText().equals("admin"))) {
                    prepareAdminScene();
                    scene.setRoot(admin);
                } else if (login.getLoginArea().getText().equals("user") && ( login.getPasswordArea().getText().equals("user"))) {
                    prepareUserScene();
                    scene.setRoot(user);
                } else if(login.getLoginArea().getText().equals("worker") && ( login.getPasswordArea().getText().equals("worker"))){
                    prepareWorkerScene();
                    scene.setRoot(worker);
                }else{
                    //status.setText("not nice");
                }
            }
        });
    }

    public void prepareUserScene() {
        user = new UserScene(this.pStage);
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: UŻYTKOWNIK");
        //user.setCenter(button);
    }

    public void prepareAdminScene() {
        admin = new AdminScene(this.pStage);
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: ADMINISTRATOR");
    }
    public void prepareWorkerScene(){
        worker = new WorkerScene(this.pStage);
        pStage.setTitle("SZRBD  ZALOGOWANY JAKO: PRACOWNIK");
    }
    public void ini(Stage s){
        pStage = s;
        BorderPane borderPane = new BorderPane();
        scene = new Scene(borderPane);
        prepareLoginScene(scene);
        borderPane.setPrefSize(1000, 800);
        pStage.setTitle("SZRBD");
        pStage.setScene(scene);
        pStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        ini(primaryStage);

    }

    public static void main(String[] args) {
        launch(Ui.class, args);
    }
}

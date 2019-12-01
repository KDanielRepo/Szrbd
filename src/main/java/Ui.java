import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
        Button button2 = new Button("cos jeszcze");
        hBox.getChildren().add(button);
        hBox.getChildren().add(button1);
        hBox.getChildren().add(button2);
        user.setTop(hBox);
        //user.setCenter(button);
    }
    public void prepareAdminScene(){
        admin = new BorderPane();
        admin.setPrefSize(1000, 800);
        Button button = new Button("b");
        admin.setCenter(button);
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
        primaryStage.setTitle("ZSI");
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

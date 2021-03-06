import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginScene extends BorderPane {
    private Button loginButton;
    private TextArea loginArea;
    private TextArea passwordArea;
    public LoginScene(Scene scene){
        //this = new BorderPane();
        this.setPrefSize(1000, 800);
        this.setPadding(new Insets(300,300,300,300));
        scene.setRoot(this);
        VBox vBox = new VBox();
        Label loginLabel = new Label("login: ");
        loginArea = new TextArea();
        loginArea.setPrefSize(150,150);
        Label passwordLabel = new Label("password: ");
        passwordArea = new TextArea();
        passwordArea.setPrefSize(150,150);
        Label status = new Label();
        loginButton = new Button("connect");
        vBox.getChildren().add(loginLabel);
        vBox.getChildren().add(loginArea);
        vBox.getChildren().add(passwordLabel);
        vBox.getChildren().add(passwordArea);
        vBox.getChildren().add(loginButton);
        vBox.getChildren().add(status);
        this.setCenter(vBox);
    }

}

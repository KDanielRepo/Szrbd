import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserScene extends BorderPane {
    public UserScene(){
        this.setPrefSize(1000, 800);
        HBox hBox = new HBox();
        Button button = new Button("lista kursów");
        Button button1 = new Button("kup bilet");
        hBox.getChildren().add(button);
        hBox.getChildren().add(button1);
        this.setTop(hBox);

        VBox center = new VBox();
        Label label = new Label("Lista kursów: ");
        center.getChildren().add(label);
        this.setCenter(center);
        VBox left = new VBox();
        left.setPrefSize(200, 200);
        Label label1 = new Label("kurs z: ");
        Label label2 = new Label("kurs do: ");
        TextArea area = new TextArea();
        area.setPrefSize(200, 100);
        TextArea area1 = new TextArea();
        area1.setPrefSize(200, 100);
        left.getChildren().add(label1);
        left.getChildren().add(area);
        left.getChildren().add(label2);
        left.getChildren().add(area1);
        this.setLeft(left);
    }
}

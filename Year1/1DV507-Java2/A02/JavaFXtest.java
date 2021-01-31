package lg222sv_assign2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXtest extends Application {

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text(20, 50, "Any sufficiently advanced technology is indistinguishable from magic.");
        Group root = new Group();
        root.getChildren().add(text);
        Scene scene = new Scene(root, 500, 100);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}

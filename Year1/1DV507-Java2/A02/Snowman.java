package lg222sv_assign2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Snowman extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Circle sun = new Circle(700,100,50);
        sun.setFill(Color.YELLOW);
        //sun.setStroke(Color.YELLOW);
        //sun.setStrokeWidth(10.0);'
        Rectangle background = new Rectangle(800,450,Color.SKYBLUE);


        Circle legs = new Circle(400,410,50);
        legs.setFill(Color.WHITE);

        Circle middleBody = new Circle(400,340,30);
        middleBody.setFill(Color.WHITE);

        Circle head = new Circle(400,300,20);
        head.setFill(Color.WHITE);

        Circle leftEye = new Circle(390,295,3);
        leftEye.setFill(Color.BLACK);
        Circle rightEye = new Circle(410,295,3);
        leftEye.setFill(Color.BLACK);
        Circle Topbutton = new Circle(400,325,3);
        Topbutton.setFill(Color.BLACK);
        Circle Middlebutton = new Circle(400,340,3);
        Middlebutton.setFill(Color.BLACK);
        Circle Botbutton = new Circle(400,355,3);
        Botbutton.setFill(Color.BLACK);

        Arc mount2 = new Arc(400,300,11,11,180,180);
        mount2.setStroke(Color.BLACK);
        Line mouth = new Line(390,305,410,305);
        mouth.setStroke(Color.BLACK);
        mouth.setStrokeWidth(2.0);

        Polygon hat = new Polygon();
        hat.getPoints().addAll(new Double[]{
                370.0,285.0,
                430.0,285.0,
                410.0,275.0,
                400.0,235.0,
                390.0,275.0
        });

        Group root = new Group();
        root.getChildren().addAll(background,sun,legs,middleBody,head,leftEye,rightEye,Topbutton,Middlebutton,Botbutton,mount2,hat);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snowman");
        primaryStage.show();
    }
}

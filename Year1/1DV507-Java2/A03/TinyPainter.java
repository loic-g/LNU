package lg222sv_assign3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TinyPainter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        Group root = new Group();

        ComboBox<String> shapes = new ComboBox<>();
        shapes.getItems().addAll("Rectangle","Dot","Circle","Line");
        shapes.setValue("Rectangle");
        pane.add(shapes,0,0);

        ComboBox<Double> size = new ComboBox<>();
        size.getItems().addAll(1.0,2.0,5.0,10.0,15.0,20.0,25.0,30.0,35.0,40.0);
        size.setValue(1.0);
        pane.add(size,1,0);

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        pane.add(colorPicker,2,0);

        //Canvas canvas = new Canvas(600,600);

        //GraphicsContext GC = canvas.getGraphicsContext2D();
        //pane.add(canvas,0,1,3,1);
        Rectangle rectangle = new Rectangle();
        Circle circle = new Circle();
        Line line = new Line();
        root.getChildren().addAll(pane);
        Scene scene = new Scene(root,600,600);
        primaryStage.setTitle("TinyPainter");
        primaryStage.setScene(scene);
        primaryStage.show();



        scene.setOnMousePressed(event -> {
            if (shapes.getValue().equals("Rectangle")) {
                rectangle.setX(event.getX());
                rectangle.setY(event.getY());
                rectangle.setFill(colorPicker.getValue());
            } else if (shapes.getValue().equals("Circle")) {
                circle.setFill(colorPicker.getValue());
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());

            } else if (shapes.getValue().equals("Line")) {
                line.setStroke(colorPicker.getValue());
                line.setStrokeWidth(size.getValue());
                line.setStartX(event.getX());
                line.setStartY(event.getY());


            }else if(shapes.getValue().equals("Dot")){

                Rectangle dot = new Rectangle(event.getX(),event.getY(),size.getValue(),size.getValue());
                dot.setFill(colorPicker.getValue());
                root.getChildren().add(dot);
            }
        });
        scene.setOnMouseDragged(event1 -> {
            if (shapes.getValue().equals("Rectangle")) {
                rectangle.setWidth(event1.getX() - rectangle.getX());
                rectangle.setHeight(event1.getY() - rectangle.getY());
                Rectangle rc = new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                rc.setFill(rectangle.getFill());
                root.getChildren().add(rc);

            } else if (shapes.getValue().equals("Circle")) {

                double radius = Math.sqrt(Math.pow((event1.getX()-circle.getCenterX()),2)+(Math.pow((event1.getY()-circle.getCenterY()),2)));
                circle.setRadius(radius);
                //event1.getX() - circle.getCenterX()
                Circle c1 = new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
                c1.setFill(circle.getFill());
                root.getChildren().add(c1);

            }
        });



        scene.setOnMouseReleased(event -> {
            if (shapes.getValue().equals("Line")) {
                line.setEndX(event.getX());
                line.setEndY(event.getY());


                Line l1 = new Line(line.getStartX(),line.getStartY(),line.getEndX(),line.getEndY());
                l1.setStrokeWidth(line.getStrokeWidth());
                l1.setStroke(line.getStroke());
                root.getChildren().add(l1);

            }

        });
    }
    public void drawRectangle(GraphicsContext g, Rectangle rec){
        g.fillRect(rec.getX(),rec.getY(),rec.getWidth(),rec.getHeight());
    }
}

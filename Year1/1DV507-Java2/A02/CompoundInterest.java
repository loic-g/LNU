package lg222sv_assign2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

//--module-path "C:\Users\Loic PC\Documents\java_course_2\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.fxml


public class CompoundInterest extends Application {



    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Compound Interest");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,14.5,15));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        final Label header= new Label("Compound Interest");
        header.setFont(Font.font(22));
        header.setAlignment(Pos.CENTER_RIGHT);
        pane.add(header,0,0,2,1);

        pane.add(new Label("Start Amount"),0,1);
        final TextField startAmount = new TextField();
        pane.add(startAmount,1,1);

        pane.add(new Label("Interest: "),0,2);
        final TextField Interest = new TextField();
        pane.add(Interest,1,2);

        pane.add(new Label("Number of years: "),0,3);
        final TextField NbYears = new TextField();
        pane.add(NbYears,1,3);

        final Label text = new Label();
        pane.add(text,0,5,2,1);

        Button calculate = new Button("Calculate");
        pane.add(calculate,1,4);
        GridPane.setHalignment(calculate, HPos.RIGHT);


        calculate.setOnAction(event -> {
            if (startAmount.getText().isEmpty() || Interest.getText().isEmpty() || NbYears.getText().isEmpty()){
                text.setText("Please enter a number in each row");
            }else {
                try {
                    double help = (Double.valueOf(startAmount.getText())) * Math.pow(((Double.valueOf(Interest.getText()) / 100) + 1), Double.valueOf(NbYears.getText()));
                    int help2 = (int) Math.round(help);
                    text.setText("In total that will be " + help2);
                }catch (NumberFormatException e) {
                    text.setText("Please write down letters");
                }
            }
        });

        Scene scene = new Scene(pane,300,200);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

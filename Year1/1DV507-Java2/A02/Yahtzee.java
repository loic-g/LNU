package lg222sv_assign2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Yahtzee extends Application {
    private int CountRollDice=3;

    public Yahtzee(){

    }
    public int getCountRollDice(){return CountRollDice;}
    public void minusCountRoll(){CountRollDice--;}
    public static void main(String[] args) {
        launch(args);
    }

    public boolean isLargeStraight(ArrayList<Integer> arr){
        int countL =0;
        int countR=0;
        for (int i=0;i<arr.size()-1;i++){
            if(arr.get(i)>=1)
                countL++;
        }
        for(int j=1;j<arr.size();j++){
            if (arr.get(j)>=1)
                countR++;
        }
        if (countL==5||countR==5)
            return true;
        else
            return false;
    }

    public boolean isSmallStraight(ArrayList<Integer> arr){
        int count1=0;
        int count2=0;
        int count3=0;
        for(int i=0;i<=3;i++){
            if(arr.get(i)>=1)
                count1++;
        }
        for(int j=1;j<=4;j++){
            if(arr.get(j)>=1){
                count2++;
            }
        }
        for(int k=2;k<arr.size();k++){
            if(arr.get(k)>=1){
                count3++;
            }
        }
        if (count1==4 || count2==4 || count3==4)
            return true;
        else
            return false;
    }




    @Override
    public void start(Stage primaryStage) {



        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(2,2,2,2));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        //Group root = new Group();


        Image dice1 = new Image(getClass().getResource("dice1.png").toExternalForm());
        ImageView dice1view = new ImageView(dice1);
        dice1view.setPreserveRatio(true);
        dice1view.setFitHeight(80);
        pane.add(dice1view,0,1);

        Image dice2 = new Image(getClass().getResource("dice2.png").toExternalForm());
        ImageView dice2view = new ImageView(dice2);
        dice2view.setPreserveRatio(true);
        dice2view.setFitHeight(80);
        pane.add(dice2view,1,1);

        Image dice3 = new Image(getClass().getResource("dice3.png").toExternalForm());
        ImageView dice3view = new ImageView(dice3);
        dice3view.setPreserveRatio(true);
        dice3view.setFitHeight(80);
        pane.add(dice3view,2,1);

        Image dice4 = new Image(getClass().getResource("dice4.png").toExternalForm());
        ImageView dice4view = new ImageView(dice4);
        dice4view.setPreserveRatio(true);
        dice4view.setFitHeight(80);
        pane.add(dice4view,3,1);

        Image dice5 = new Image(getClass().getResource("dice5.png").toExternalForm());
        ImageView dice5view = new ImageView(dice5);
        dice5view.setPreserveRatio(true);
        dice5view.setFitHeight(80);
        pane.add(dice5view,4,1);

        Image dice6 = new Image(getClass().getResource("dice6.png").toExternalForm());
        ImageView dice6view = new ImageView(dice6);
        dice6view.setPreserveRatio(true);
        dice5view.setFitHeight(80);

        ImageView[] AllImageView= {dice1view,dice2view,dice3view,dice4view,dice5view,dice6view};
        Image[] AllImage = {dice1,dice2,dice3,dice4,dice5,dice6};

        Label Yahtzee = new Label("Yahtzee");
        Yahtzee.setFont(Font.font(40));
        pane.add(Yahtzee,0,0,5,1);

        CheckBox pos1 = new CheckBox();
        pos1.setDisable(true);
        GridPane.setHalignment(pos1, HPos.CENTER);
        pane.add(pos1,0,2);

        CheckBox pos2 = new CheckBox();
        pos2.setDisable(true);
        GridPane.setHalignment(pos2,HPos.CENTER);
        pane.add(pos2,1,2);

        CheckBox pos3 = new CheckBox();
        pos3.setDisable(true);
        GridPane.setHalignment(pos3,HPos.CENTER);
        pane.add(pos3,2,2);

        CheckBox pos4 = new CheckBox();
        pos4.setDisable(true);
        GridPane.setHalignment(pos4,HPos.CENTER);
        pane.add(pos4,3,2);

        CheckBox pos5 = new CheckBox();
        pos5.setDisable(true);
        GridPane.setHalignment(pos5,HPos.CENTER);
        pane.add(pos5,4,2);

        Button RollDice = new Button("Roll the Dice!");
        pane.add(RollDice,0,3,2,1);
        Label text = new Label("You have 3 rolls left.");
        pane.add(text,2,3,3,1);
        CheckBox[] arraycheckbox = {pos1,pos2,pos3,pos4,pos5};
        RollDice.setOnAction(e-> {
            Random rd = new Random();
            if (getCountRollDice()==1){
                minusCountRoll();

                for (int i = 0; i < arraycheckbox.length; i++) {
                    arraycheckbox[i].setDisable(true);
                    int rand = rd.nextInt(6);
                    if (arraycheckbox[i].isSelected()){
                    }else
                        AllImageView[i].setImage(AllImage[rand]);
                }


                ArrayList<Integer> CountPerFace = new ArrayList<>();
                for (int i=0;i<6;i++)
                    CountPerFace.add(0);
                //To fix
                for (int q=0;q<AllImageView.length-1;q++){
                    for (int l=0;l<AllImage.length;l++) {
                        if (AllImageView[q].getImage().equals(AllImage[l])) {
                            CountPerFace.set(l,(CountPerFace.get(l)+1));
                            break;
                        }
                    }
                }

                if (CountPerFace.contains(5))
                    text.setText("Yahtzee");
                else if (CountPerFace.contains(4))
                    text.setText("Four of a kind");
                else if(CountPerFace.contains(3)&&CountPerFace.contains(2))
                    text.setText("Full House");
                else if (CountPerFace.contains(3))
                    text.setText("Three of a kind");
                else if(isLargeStraight(CountPerFace))
                    text.setText("Large Straight");
                else if(isSmallStraight(CountPerFace))
                    text.setText("Small Straight");
                else
                    text.setText("Pair");

                }
            else if (getCountRollDice()<1){

            }else {
                minusCountRoll();
                for (int k = 0; k < arraycheckbox.length; k++)
                    arraycheckbox[k].setDisable(false);


                text.setText("You have " + (CountRollDice) + " rolls left.");
                for (int j = 0; j < arraycheckbox.length; j++) {
                    if (arraycheckbox[j].isSelected()) {
                    } else {
                        int help = rd.nextInt(6);
                        AllImageView[j].setImage(AllImage[help]);

                    }
                }

            }
        });
        Scene scene = new Scene(pane,600,400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Yahtzee Game");
        primaryStage.show();

    }
}

package RunningSanta;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RunningSanta extends Application {

    private Image[] santaRunning=new Image[11];
    private Image[] santaSlidding = new Image[11];
    private Image[] santaJumping = new Image[16];
    private int changeSanta;
    private int count=0;
    private ImageView Santa;
    private boolean isgoingforward;

    public static void main(String[] args) {
        launch(args);
    }

    public void addCount(){count++;}
    public int getCount(){return count;}
    public int getChangeSanta(){return changeSanta;}
    public void setChangeSanta(int p){changeSanta=p;}

    public void setIsgoingforward(boolean t){
        isgoingforward = t;
    }
    public boolean getIsgoingforward(){return isgoingforward;}


    @Override
    public void start(Stage primaryStage) {
        CreateAllTheSantaRunning();
        CreateAllSlidingSanta();
        CreateAllSantaJump();

        setIsgoingforward(true);
        setChangeSanta(1);

        Group root = new Group();
        GridPane pane = new GridPane();
        ImageView BG = new ImageView(new Image(getClass().getResource("BG.png").toExternalForm()));
        BG.setFitWidth(1000);
        BG.setFitHeight(600);

        Santa = new ImageView(getSantaRunning(0));
        Santa.setPreserveRatio(true);
        Santa.setFitWidth(200);
        Santa.setX(55);
        Santa.setY(305);







        root.getChildren().addAll(BG,Santa);

        Scene scene = new Scene(root,1000,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Running Santa");
        primaryStage.show();



        int count=0;
        KeyFrame k = new KeyFrame(Duration.millis(20),event -> {

            scene.setOnKeyPressed(event1 -> {
                if(event1.getCode()== KeyCode.DOWN){
                    setChangeSanta(2);
                }else if(event1.getCode()==KeyCode.UP){
                    setChangeSanta(3);
                }
            });
            scene.setOnKeyReleased(event1 -> {
                setChangeSanta(1);
                Santa.setY(305);
            });
            if(Santa.getX()==850){
                setIsgoingforward(false);
                goBackward();
            }else if(Santa.getX()==30){
                setIsgoingforward(true);
                goForward();
            }else if (Santa.getX()>30 && Santa.getX()<850){
                if (getIsgoingforward() == true) {
                    goForward();
                } else if (getIsgoingforward() == false) {
                    goBackward();
                }
            }

        });

        Timeline timeline = new Timeline(k);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void goForward(){
        int x_speed = 5;
        if(getChangeSanta()==1) {
            Santa.setImage(getSantaRunning(getCount() % 11));
            Santa.setScaleX(1);
            Santa.setX(Santa.getX() + x_speed);
            addCount();
        }else if (getChangeSanta()==2){
            Santa.setImage(getSantaSliding(getCount()%11));
            Santa.setScaleX(1);
            Santa.setX(Santa.getX() + x_speed);
            addCount();
        }
        else if (getChangeSanta()==3){
            Santa.setImage(getSantaJumping(getCount()%16));
            Santa.setScaleX(1);
            Santa.setX(Santa.getX() + x_speed);
            Santa.setY(Santa.getY()-1);
            addCount();
        }
    }

    private void goBackward(){
        int x_speed=5;
        if(getChangeSanta()==1) {
            Santa.setImage(getSantaRunning(getCount() % 11));
            Santa.setScaleX(-1);
            Santa.setX(Santa.getX() - x_speed);
            addCount();
        }else if(getChangeSanta()==2){
            Santa.setImage(getSantaSliding(getCount() % 11));
            Santa.setScaleX(-1);
            Santa.setX(Santa.getX() - x_speed);
            addCount();
        }
        else if(getChangeSanta()==3){
            Santa.setImage(getSantaJumping(getCount() % 16));
            Santa.setScaleX(-1);
            Santa.setX(Santa.getX() - x_speed);
            Santa.setY(Santa.getY()-1);
            addCount();
        }
    }

    private void CreateAllTheSantaRunning(){

        for(int i=0;i<santaRunning.length;i++){
            santaRunning[i]= new Image(getClass().getResource("Run ("+(i+1)+").png").toExternalForm());
        }
    }

    public Image getSantaRunning(int pos){
        if(pos<0 || pos>santaRunning.length)
            throw new IndexOutOfBoundsException("");
        else{
            return santaRunning[pos];
        }

    }

    private void CreateAllSlidingSanta(){
        for(int i=0;i<santaSlidding.length;i++){
            santaSlidding[i]= new Image(getClass().getResource("Slide ("+(i+1)+").png").toExternalForm());
        }
    }
    public Image getSantaSliding(int pos){
        if(pos<0 || pos>santaSlidding.length)
            throw new IndexOutOfBoundsException("");
        else{
            return santaSlidding[pos];
        }
    }

    private void CreateAllSantaJump(){
        for (int i=0;i<santaJumping.length;i++){
            santaJumping[i]=new Image(getClass().getResource("Jump ("+(i+1)+").png").toExternalForm());
        }

    }
    public Image getSantaJumping(int pos){
        if(pos<0 || pos>santaJumping.length)
            throw new IndexOutOfBoundsException("");
        else{
            return santaJumping[pos];
        }
    }
}

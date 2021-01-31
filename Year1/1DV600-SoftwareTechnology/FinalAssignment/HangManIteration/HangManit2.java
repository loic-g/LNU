package HangManIteration2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class HangManit2 extends Application {
    private ArrayList<String> AllTheWords = new ArrayList<>();
    private String Path = "C:\\Users\\Loic PC\\Documents\\IntelliJ_java_Course\\1DV600\\src\\HangManIteration2\\Database.txt";
    private File DataBase = new File(Path);
    private int Wordsize;
    private String TheWord;
    private String[] WordArray;
    private String[] UnderScoreA;
    private ArrayList<String> WrongLettersList= new ArrayList<>();
    private int count;
    private Image[] HMImages = new Image[8];
    private List<String> Alphabet = new ArrayList<>();

    public String[] getWordArray(){return WordArray;};


    public void getrandomWord() {
        getAllTheWords(DataBase);
        Random rd = new Random();
        int randomnb = rd.nextInt(AllTheWords.size());
        TheWord = AllTheWords.get(randomnb);
        WordArray = TheWord.split("");
        UnderScoreA = new String[WordArray.length];
        for (int i = 0; i < UnderScoreA.length; i++)
            UnderScoreA[i] = "_";
        Wordsize = WordArray.length;
    }

    private void CreateImages(){
        for(int i=0;i<HMImages.length;i++){
            HMImages[i]= new Image(getClass().getResource((i+1)+".png").toExternalForm());
        }

    }
    private Image getImage(int pos){
        if(pos>=0 && pos<HMImages.length){
            return HMImages[pos];
        }else
            throw new IndexOutOfBoundsException();
    }

    public int getCount(){return count;}
    // HERE
    public String[] createUnderScoreArray(int length){
        String[] help=new String[length];
        for(int i=0;i<help.length;i++) {
           help[i] = "_";
        }
        return help;
    }

    private void getAllTheWords(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                AllTheWords.add(word);
            }
            sc.close();
        } catch (Exception ioe) {
            System.out.println("File not found");
        }

    }
    //JUnit
    public String getStringRepresentation(String[] array){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<array.length;i++){
            String help = array[i]+" ";
            sb.append(help);
        }
        return sb.toString();
    }
    //JUnit
    public boolean isLetterinWord(String c, int pos, String[] word) {
        if(pos>=0 && pos<word.length) {
            if (word[pos].equals(c))
                return true;
            else
                return false;
        }else
            throw new IndexOutOfBoundsException();

    }

    public int getWordSize() {
        return Wordsize;
    }


    public String[] getUnderscoreArray() {
        return UnderScoreA;
    }

    public String getWord() {
        return TheWord;
    }

    public boolean isEqual(){
        if (Arrays.equals(WordArray, UnderScoreA))
            return true;
        else
            return false;
    }

    public String getWrongLetter() {
        String help = "Wrong Letters: ";
        for (int i = 0; i < WrongLettersList.size(); i++)
            help += WrongLettersList.get(i) + " ";
        return help;
    }

    public void changeUnderscoreArray(int pos, String change) throws Exception {
        if (pos < 0 || pos > Wordsize)
            throw new Exception("The position is outside the boundries");
        else {
            UnderScoreA[pos] = change;
        }
    }

    private void changeWrongLetters(String str){
        if (Alphabet.contains(str.toUpperCase())) {
            str = str.toUpperCase();
            int pos = Alphabet.indexOf(str);
            WrongLettersList.set(pos, str);
        }
    }

    private void createWrongLetterLists(){
        String[] al = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","Y","Z"};
        for (int i=0;i<25;i++){
            WrongLettersList.add(" ");
        }
        Alphabet = Arrays.asList(al);
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        count=0;
        CreateImages();
        createWrongLetterLists();
        //Start Menu
        //getrandomWord();
        GridPane StartMenu = new GridPane();
        StartMenu.setAlignment(Pos.CENTER);
        StartMenu.setVgap(5.5);
        StartMenu.setHgap(5.5);
        StartMenu.setPadding(new Insets(2,2,2,2));

        Label title = new Label("Welcome to the Hang-Man Game");
        title.setFont(Font.font(30.0));
        StartMenu.add(title,0,1);
        GridPane.setHalignment(title,HPos.CENTER);

        Image HangManG = new Image(getClass().getResource("HangManGame.jpg").toExternalForm());
        ImageView HangManGame = new ImageView(HangManG);
        HangManGame.setFitWidth(400);
        HangManGame.setFitHeight(400);
        StartMenu.add(HangManGame,0,0);
        GridPane.setHalignment(HangManGame,HPos.CENTER);

        CheckBox istesting = new CheckBox("testing");
        StartMenu.add(istesting,0,4);
        //Game
        GridPane Game = new GridPane();
        Game.setVgap(5.5);
        Game.setHgap(5.5);
        Game.setPadding(new Insets(2,2,2,2));

        Label titleGame = new Label("Hang-Man Game");
        titleGame.setFont(Font.font(25.0));
        Game.add(titleGame,0,0,5,1);

        GridPane.setHalignment(titleGame,HPos.CENTER);
        //String UnderScore = getStringRepresentation(UnderScoreA);
        Label UnderscoreWord = new Label();
        UnderscoreWord.setFont(Font.font(25.0));
        Game.add(UnderscoreWord,0,2);




        Image Graphic = new Image(getClass().getResource("1.png").toExternalForm());
        ImageView Picture = new ImageView(Graphic);
        Picture.setPreserveRatio(true);
        Picture.setFitHeight(300);
        Game.add(Picture,0,1);



        Button ExitProg = new Button("Exit Program");
        GridPane.setHalignment(ExitProg, HPos.LEFT);
        StartMenu.add(ExitProg,0,3);

        Scene GameScene = new Scene(Game,600,600);

        GridPane ExitConfirmation = new GridPane();
        ExitConfirmation.setAlignment(Pos.CENTER);
        ExitConfirmation.setVgap(5.5);
        ExitConfirmation.setHgap(5.5);
        ExitConfirmation.setPadding(new Insets(2,2,2,2));

        Label text = new Label("Are you sure you wanna leave?");
        ExitConfirmation.add(text,0,0,2,1);
        Button Yes = new Button("Yes");
        GridPane.setHalignment(text,HPos.CENTER);
        ExitConfirmation.add(Yes,0,1);

        Button No = new Button("NO!!");
        ExitConfirmation.add(No,1,1);

        GridPane.setHalignment(Yes,HPos.CENTER);
        GridPane.setHalignment(No,HPos.CENTER);
        Scene ExitConfScene = new Scene(ExitConfirmation,400,150);

        Button StartGame = new Button("Start Game");
        StartMenu.add(StartGame,0,2);
        GridPane.setHalignment(StartGame,HPos.CENTER);

        TextField writeguess = new TextField();
        Game.add(writeguess,0,3);

        Button guess = new Button("GUESS");
        Game.add(guess,1,3);

        Label text2 = new Label();
        Game.add(text2,0,4,2,1);

        Label wrongletters = new Label();
        wrongletters.setFont(Font.font("Arial",FontWeight.BOLD,20));
        Game.add(wrongletters,0,5,3,1);

        Scene scene = new Scene(StartMenu,500,600);

        Button Restart = new Button("Restart");
        Restart.setVisible(false);
        Game.add(Restart,2,6);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HangMan");
        primaryStage.show();
        No.setOnAction(e->{
            primaryStage.close();
            primaryStage.setScene(scene);
            primaryStage.setTitle("HangMan Game");
            primaryStage.show();
        });
        Yes.setOnAction(e->{
            System.exit(-1);
        });
        ExitProg.setOnAction(e->{
            primaryStage.close();
            primaryStage.setScene(ExitConfScene);
            primaryStage.setTitle("Exit Game?");
            primaryStage.show();

        });
        StartGame.setOnAction(e->{

            if(istesting.isSelected()){
                getAllTheWords(DataBase);
                TheWord = AllTheWords.get(0);
                WordArray = TheWord.split("");
                UnderScoreA = createUnderScoreArray(WordArray.length);
                UnderscoreWord.setText(getStringRepresentation(UnderScoreA));
                Wordsize = WordArray.length;

            }else{
                getrandomWord();
                UnderscoreWord.setText(getStringRepresentation(UnderScoreA));
            }

            primaryStage.close();
            primaryStage.setScene(GameScene);
            primaryStage.setTitle("HangMan Game");
            primaryStage.show();


        });

        guess.setOnAction(event -> {
            String theguess = writeguess.getText();
            theguess = theguess.toLowerCase();
                //text2.setText(getWord());
                if(writeguess.getText().isBlank()){
                    writeguess.requestFocus();
                }else if (theguess.equals(getWord())){
                    UnderscoreWord.setText(getStringRepresentation(getWordArray()));
                    text2.setText("Congratulation, You have WON");
                    guess.setDisable(true);
                    writeguess.clear();
                    Restart.setVisible(true);
                }else {


                    boolean check = false;
                    for (int i = 0; i < getWordSize(); i++) {
                        if (isLetterinWord(theguess, i,getWordArray())) {
                            try {
                                changeUnderscoreArray(i, theguess);
                            } catch (Exception e) {
                                e.printStackTrace();
                                //UnderscoreWord.setText(getStringRepresentation());
                            }
                            check = true;

                        } else {

                        }
                    }
                    if (isEqual()) {
                        text2.setText("Congratulation, You have WON");
                        guess.setDisable(true);
                        Restart.setVisible(true);

                    }

                    if (check == false) {
                        count++;
                        if (count == 7) {
                            Picture.setImage(getImage(count));
                            text2.setText("You have Lost");
                            text2.setFont(Font.font(25));
                            guess.setDisable(true);
                            writeguess.setDisable(true);
                            Restart.setVisible(true);
                        }

                        changeWrongLetters(theguess);
                        wrongletters.setText(getWrongLetter());
                        Picture.setImage(getImage(count));


                    }
                    writeguess.clear();
                    writeguess.requestFocus();

                    UnderscoreWord.setText(getStringRepresentation(getUnderscoreArray()));

                }
        });

        Restart.setOnAction(event -> {
            count=0;
            Picture.setImage(getImage(getCount()));
            getrandomWord();
            text2.setText("");
            Restart.setVisible(false);
            WrongLettersList.clear();
            createWrongLetterLists();
            wrongletters.setText(getWrongLetter());
            UnderscoreWord.setText(getStringRepresentation(getUnderscoreArray()));
            writeguess.setDisable(false);
            guess.setDisable(false);
        });
    }

}

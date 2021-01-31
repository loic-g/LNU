package HangManIteration2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangManit2Test {
    HangManit2 sut;
    @BeforeEach
    void startB() {
        sut = new HangManit2();
    }

    @Test
    public void shouldReturnTrueIfTheLetterIsInTheWordAtCertainPosition () {
        //test-input-output definitions
        String[] inputWord = {"h", "e", "l", "l", "o"};
        String inputLetter = "o";
        int inputposition = 4;
        boolean ExpectedResult = true;

        //exercice the sut
        boolean MethodResult = sut.isLetterinWord(inputLetter, inputposition, inputWord);

        //compare actual result with the expected value and report
        assertEquals(ExpectedResult,MethodResult);


    }

    @Test
    public void shouldReturnFalseIfLetterIsNotInTheWordAtCertainPosition(){
        //test-input-output definition
        String[] inputEmptyWord ={"h","e","l","l","o"};
        String inputEmptyLetter ="e";
        int inputEmptyPosition=0;
        boolean ExpectedEmptyResult =false;

        //Exercise the sut
        boolean EmptyMethodResult = sut.isLetterinWord(inputEmptyLetter,inputEmptyPosition,inputEmptyWord);
        //Compare the actual result with the expected value and report it.
        assertEquals(ExpectedEmptyResult,EmptyMethodResult);
    }

    @Test
    public void shouldReturnStringOfStringArray1(){
        //FIRST TEST
        //Test-input-output definition
        String[] inputStringArray = {"h","e","l","l","o"};
        String ExpectedString = "h e l l o ";

        //Exercise the sut
        String ActualString = sut.getStringRepresentation(inputStringArray);

        //Compare the actual result with the expected value and report
        assertEquals(ExpectedString,ActualString);
    }
    @Test
    public void shouldReturnStringOfStringArray2(){
        //Test-input-output definition
        String[] inputEmptyArray ={};
        String ExpectedEmptyString="";

        //Exercise the sut
        String ActualEmptyString = sut.getStringRepresentation(inputEmptyArray);

        //Compare the actual result with the expected value and report
        assertEquals(ExpectedEmptyString,ActualEmptyString);
    }
    @Test
    public void shouldReturnStringArrayOfUnderscore(){
        //Test-input-output definition
        String[] inputArray = {"h","e","l","l","O"};
        String[] Expected = {"_","_","_","_","_"};

        //Exercise the sut
        String[] Actual = sut.createUnderScoreArray(inputArray.length);

        //Compare the actual result with the expected value and report
        assertArrayEquals(Expected,Actual);
    }

}
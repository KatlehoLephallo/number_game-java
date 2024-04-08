import number.game.NumberGame;
import number.game.ScannerWrapper;
import org.junit.Test;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NumberGameTest {


    @Test
    public void testGenerateRandomNumber() {
        NumberGame game = new NumberGame();
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            int number = game.generateRandomNumber();
            assertTrue("Number should be between 0 and 100", number >= 0 && number <= 100);
            uniqueNumbers.add(number);
        }
    }

    @Test
    public void testAnotherGame() {

        NumberGame game = new NumberGame();
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        game.setScanner(mockScanner);
        String decisionYes = "y";
        String decisionNo = "n";

        when(mockScanner.nextLine()).thenReturn(decisionYes).thenReturn(decisionNo);

        assertTrue("We are playing another round",game.anotherGame());
        assertFalse("We're not Playing another round", game.anotherGame());
    }


    @Test
    public void testAskUser(){
        NumberGame game = new NumberGame();
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        game.setScanner(mockScanner);
        String message = "Enter a number between 1 and 10";
        String invalidInput = "invalid";
        String validInput = "5";

        when(mockScanner.nextLine()).thenReturn(invalidInput).thenReturn(validInput);

        int result = game.askUser(message, 10);

        assertEquals(5, result);
        assertNotEquals("5", result);
    }

    @Test
    public void testResults(){
        NumberGame game = new NumberGame();
        assertTrue(game.results(7,7,2));
        assertFalse(game.results(7,8,5));
    }

}

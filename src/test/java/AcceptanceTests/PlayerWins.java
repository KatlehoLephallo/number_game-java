package AcceptanceTests;

import number.game.NumberGame;
import number.game.ScannerWrapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerWins {

    @Test
    public void PlayOneRound(){

        int numberOfGuesses = 4;
        int generatedNumber = 70;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "70", "no");

        assertTrue(game.play(numberOfGuesses, generatedNumber));
        assertEquals(game.getPlayer(), 1);
        assertEquals(game.getComputer(), 0);

        //end game
        assertFalse(game.anotherGame());

    }

    @Test
    public void PlayThreeRounds(){

        int numberOfGuesses = 4;
        int generatedNumber = 70;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "70", "yes");
        assertTrue(game.play(numberOfGuesses, generatedNumber));
        //Continue game
        assertFalse(game.isAnotherGame());

        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "7", "yes");
        assertTrue(game.play(numberOfGuesses, 7));
        //Continue game
        assertFalse(game.isAnotherGame());



        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "no");
        assertTrue(game.play(numberOfGuesses, generatedNumber));
        assertEquals(game.getPlayer(), 2);
        assertEquals(game.getComputer(), 1);

        //end game
        assertFalse(game.isAnotherGame());

    }

    @Test
    public void PlayFiveRounds(){

        int numberOfGuesses = 4;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        // first round. player wins
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "70", "yes");
        assertTrue(game.play(numberOfGuesses, 70));
        //Continue game
        assertFalse(game.isAnotherGame());


        //second round. computer wins
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "yes");
        assertTrue(game.play(numberOfGuesses, 10));
        //Continue game
        assertFalse(game.isAnotherGame());


        //third round. computer wins
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "6", "yes");
        assertTrue(game.play(numberOfGuesses, 7));
        //Continue game
        assertFalse(game.isAnotherGame());


        //fourth round. player wins
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "1", "yes");
        assertTrue(game.play(numberOfGuesses, 1));
        //Continue game
        assertFalse(game.isAnotherGame());


        //fifth round. player wins
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "99", "yes");
        assertTrue(game.play(numberOfGuesses, 99));
        //Continue game
        assertFalse(game.isAnotherGame());


        //final score
        assertEquals(game.getPlayer(), 3);
        assertEquals(game.getComputer(), 2);

        //end game
        assertFalse(game.isAnotherGame());
    }
}

package AcceptanceTests;

import number.game.NumberGame;
import number.game.ScannerWrapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputerWins {

    @Test
    public void PlayOneRound(){

        int numberOfGuesses = 4;
        int generatedNumber = 70;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "no");

        assertTrue(game.play(numberOfGuesses, generatedNumber));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 1);

        //end game
        assertFalse(game.isAnotherGame());



    }

    @Test
    public void PlayThreeRound(){

        int numberOfGuesses = 4;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "yes");

        assertTrue(game.play(4, 10));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 1);

        //Continue game
        assertFalse(game.isAnotherGame());

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "50", "yes");

        assertTrue(game.play(4, 51));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 2);

        //Continue game
        assertFalse(game.isAnotherGame());

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "yes");

        assertTrue(game.play(4, 90));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 3);
    }

    @Test
    public void PlayFourRound(){

        int numberOfGuesses = 4;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "yes");

        assertTrue(game.play(4, 10));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 1);

        //Continue game
        assertFalse(game.isAnotherGame());

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "50", "yes");

        assertTrue(game.play(4, 51));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 2);

        //Continue game
        assertFalse(game.isAnotherGame());

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "30", "yes");

        assertTrue(game.play(4, 90));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 3);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "94", "yes");

        assertTrue(game.play(4, 95));
        assertEquals(game.getPlayer(), 0);
        assertEquals(game.getComputer(), 4);
    }
}

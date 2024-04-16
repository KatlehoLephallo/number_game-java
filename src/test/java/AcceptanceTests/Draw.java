package AcceptanceTests;

import number.game.NumberGame;
import number.game.ScannerWrapper;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Draw {

    @Test
    public void PlayThreeRounds(){

        int numberOfGuesses = 4;

        //mocking the scanner
        ScannerWrapper mockScanner = mock(ScannerWrapper.class);
        NumberGame game = new NumberGame();
        game.setScanner(mockScanner);

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "70", "yes");
        assertTrue(game.play(numberOfGuesses, 70));
        //Continue game
        assertFalse(game.isAnotherGame());

        //Player guesses
        when(mockScanner.nextLine()).thenReturn("50", "60", "40", "7", "no");
        assertTrue(game.play(numberOfGuesses, 30));

        assertEquals(game.getPlayer(), 1);
        assertEquals(game.getComputer(), 1);

        //end game
        assertFalse(game.isAnotherGame());

    }
}

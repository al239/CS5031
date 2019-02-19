package uk.ac.standrews.cs5031;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.MalformedParametersException;

public class GameStateTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void settingTest() {
        GameState gameState = new GameState(Words.words1[0], 10, 5);
        assertTrue(gameState.g == 0);
        assertTrue(gameState.wrong == 10);
        assertTrue(gameState.h == 5);
    }

    @Test(expected = MalformedParametersException.class)
    public void settingWrongVariableTest() {
        GameState gameState = new GameState(Words.words1[0], -1, -2);
    }

    @Test
    public void guessLetterTest() {

    }

    @Test
    public void showWordTest() {
        GameState gameState = new GameState(Words.words1[0], 10, 5);
        gameState.showWord("a");
        assertEquals("-", outContent.toString());
    }
}
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
        GameState gameState = new GameState(Words.words1[0], 10, 5);
        assertTrue(gameState.guessLetter("a"));
        assertTrue(gameState.g == 1);
        assertTrue(gameState.got.size() == 1);
        assertFalse(gameState.guessLetter("c"));
        assertTrue(gameState.g == 2);
        assertTrue(gameState.got.size() == 1);
    }

    @Test
    public void hintTest() {
        GameState gameState = new GameState(Words.words1[0], 10, 1);
        assertFalse(gameState.guessLetter("?"));
        assertTrue(gameState.h == 0);
    }

    @Test
    public void hintNotAllowedTest() {
        GameState gameState = new GameState(Words.words1[0], 10, 0);
        assertFalse(gameState.guessLetter("?"));
        assertEquals("No more hints allowed" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void showWordTest() {
        GameState gameState = new GameState(Words.words1[0], 10, 5);
        gameState.showWord("a");
        assertEquals("-" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void winGameTest() {
        GameState gameState = new GameState(Words.words1[1], 10, 5);
        gameState.guessLetter("c");
        gameState.guessLetter("a");
        gameState.guessLetter("i");
        gameState.guessLetter("t");
        gameState.guessLetter("h");
        gameState.guessLetter("n");
        gameState.guessLetter("e");
        gameState.guessLetter("s");
        assertTrue(gameState.won());
    }

    @Test
    public void lossGameTest() {
        GameState gameState = new GameState(Words.words1[0], 1, 5);
        gameState.guessLetter("c");
        gameState.guessLetter("a");
        assertTrue(gameState.lost());
    }
}
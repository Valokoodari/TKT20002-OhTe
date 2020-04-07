package logic;

import org.junit.Test;

import gui.GameGUI;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void correctStartValuesEasy() {
    	Game game = new Game(0, "Easy", new GameGUI(null, null));
    	
    	assertEquals(20, game.getLength());
    	assertEquals(100, game.getHealth());
    	assertEquals(0, game.getDifficulty());
    }
    
    @Test
    public void correctStartValuesMedium() {
    	Game game = new Game(0, "Medium", new GameGUI(null, null));
    	
    	assertEquals(40, game.getLength());
    	assertEquals(50, game.getHealth());
    	assertEquals(1, game.getDifficulty());
    }
    
    @Test
    public void correctStartValuesHard() {
    	Game game = new Game(0, "Hard", new GameGUI(null, null));
    	
    	assertEquals(60, game.getLength());
    	assertEquals(1, game.getHealth());
    	assertEquals(2, game.getDifficulty());
    }
    
    @Test
    public void invalidDifficultyDefaultsToMedium() {
    	Game game = new Game(0, "ASDF", new GameGUI(null, null));
    	
    	assertEquals(1, game.getDifficulty());
    }
}
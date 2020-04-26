package logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void correctStartValuesEasy() {
    	Game game = new Game(0, "Easy");
    	
    	assertEquals(20, game.getLength());
    	assertEquals(100, game.getHealth());
    	assertEquals(0, game.getDifficulty());
    }
    
    @Test
    public void correctStartValuesMedium() {
    	Game game = new Game(0, "Medium");
    	
    	assertEquals(40, game.getLength());
    	assertEquals(50, game.getHealth());
    	assertEquals(1, game.getDifficulty());
    }
    
    @Test
    public void correctStartValuesHard() {
    	Game game = new Game(0, "Hard");
    	
    	assertEquals(60, game.getLength());
    	assertEquals(1, game.getHealth());
    	assertEquals(2, game.getDifficulty());
    }
    
    @Test
    public void invalidDifficultyDefaultsToMedium() {
    	Game game = new Game(0, "ASDF");
    	
    	assertEquals(1, game.getDifficulty());
    }
    
    @Test
    public void startingRoundIs1() {
    	Game game = new Game(0, "Easy");
    	
    	assertEquals(1, game.getRound());
    }
}
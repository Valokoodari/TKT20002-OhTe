package logic;

import org.junit.Test;

import logic.towers.Tower;

import static org.junit.Assert.*;

import org.junit.Before;

public class GameTest {
	Game game;

	@Before
	public void Before() {
		game = new Game(0, 0);
	}

    @Test
    public void correctStartValuesEasy() {
    	assertEquals(10, game.getLength());
    	assertEquals(100, game.getHealth());
    }
    
    @Test
    public void correctStartValuesMedium() {
    	Game mediumGame = new Game(0, 1);
    	
    	assertEquals(20, mediumGame.getLength());
    	assertEquals(50, mediumGame.getHealth());
    }
    
    @Test
    public void correctStartValuesHard() {
    	Game hardGame = new Game(0, 2);
    	
    	assertEquals(40, hardGame.getLength());
    	assertEquals(1, hardGame.getHealth());
    }
    
    @Test
    public void startingRoundIs1() {
    	assertEquals(1, game.getRound());
	}

	@Test
	public void gameStartWithoutTowers() {
		assertEquals(0, countTowers(game.getTowers()));
	}
	
	@Test
	public void towerCanBePlacedOnEmptyTile() {
		assertEquals(0, countTowers(game.getTowers()));
		game.click(0, 0);
		assertEquals(1, countTowers(game.getTowers()));
	}

	@Test
	public void towerCanBePlacedAndUpgraded() {
		game.click(0, 0);
		assertEquals(0, game.getTowers()[0][0].getUpgrades()[1]);
		
		game.click(0, 0);
		assertEquals(1, game.getTowers()[0][0].getUpgrades()[1]);
	}

	@Test
	public void towersCannotBePlacedOnPathTile() {
		assertEquals(0, countTowers(game.getTowers()));
		game.click(0, 2);
		assertEquals(0, countTowers(game.getTowers()));
	}

	@Test
	public void gameCanBeLost() {
		boolean lost = false;
		for (int i = 0; i < 10000 && !lost; i++)
			if (game.update(2.0) == 2) lost = true;

		assertTrue(lost);
	}

	private int countTowers(Tower[][] towers) {
		int count = 0;

		for (int i = 0; i < towers.length; i++)
			for (int j = 0; j < towers[i].length; j++)
				if (towers[i][j] != null) count++;

		return count;
	}
}
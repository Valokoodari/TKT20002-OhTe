package logic;

import gui.GameGUI;

public class Game {
	private int health;
	private int round;
	private int finalRound;
	private int difficulty;
	private int mapNumber;
	private GameGUI gui;
	
	public Game(int map, String difficulty, GameGUI gameGui) {
		this.mapNumber = map;
		this.round = 1;
		
		// Convert chosen difficulty from String to Integer
		this.difficulty = this.convertDifficulty(difficulty);
		
		// Set health to 100, 50, or 1
		this.health = 100 - this.difficulty * 50;
		if (this.health <= 0) {
			this.health = 1;
		}
		
		// Set final round to 20, 40, or 60
		this.finalRound = 20 + this.difficulty * 20;
		
		this.gui = gameGui;
		this.gui.setHealth(this.health);
		this.gui.setRound(this.round, this.finalRound);
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getLength() {
		return this.finalRound;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	private int convertDifficulty(String difficulty) {
		switch (difficulty) {
			case "Easy":
				return 0;
			case "Medium":
				return 1;
			case "Hard":
				return 2;
			default:
				return 1;
		}
	}
}
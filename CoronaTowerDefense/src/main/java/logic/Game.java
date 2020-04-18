package logic;

import towers.Tower;
import viruses.Virus;

public class Game {
	private int money;
	private int health;
	private int round;
	private int finalRound;
	private int difficulty;
	private Map map;
	private Tower[][] towers;
	private Virus[] viruses;
	
	public Game(int mapNumber, String difficulty) {
		this.map = new Map(mapNumber);
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
		
		this.towers = new Tower[this.getMap().length][this.getMap()[0].length];
		
		this.viruses = new Virus[2];
		this.viruses[0] = new Virus(0, 2, this.map.getPath());
		this.viruses[1] = new Virus(-1, 2, this.map.getPath());
	}
	
	public int update(double elapsedTime) {
		if (this.health <= 0) {
			return 2;
		}

		for (int i = 0; i < this.viruses.length; i++) {
			this.health -= this.viruses[i].update(elapsedTime);
		}
		
		return 0;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getRound() {
		return this.round;
	}
	
	public int getLength() {
		return this.finalRound;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public int[][] getMap() {
		return this.map.getMap();
	}
	
	public Tower[][] getTowers() {
		return this.towers;
	}
	
	public void addTower(int x, int y) {
		if (this.map.getMap()[y][x] == 0 && this.towers[y][x] == null) {
			this.towers[y][x] = new Tower(x, y);
		}
	}
	
	public Virus[] getViruses() {
		return this.viruses;
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
package logic;

public class Game {
	private int health;
	private int difficulty;
	private int mapNumber;
	
	public Game(int map, String difficulty) {
		this.mapNumber = map;
		
		// Convert chosen difficulty from String to Integer
		switch (difficulty) {
			case "Easy":
				this.difficulty = 0;
				break;
			case "Medium":
				this.difficulty = 1;
				break;
			case "Hard":
				this.difficulty = 2;
				break;
			default:
				this.difficulty = 1;
				break;
		}
		
		// Set health to 100, 50 or 1
		this.health = 100 - this.difficulty * 50;
		if (this.health <= 0) {
			this.health = 1;
		}
	}
	
	public int getHealth() {
		return this.health;
	}
}
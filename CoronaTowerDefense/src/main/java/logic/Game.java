package logic;

import towers.Tower;
import viruses.Virus;

public class Game {
	private int money;
	private int health;
	private int round;
	private int finalRound;
	private int difficulty;
	private int killed;
	private Map map;
	private Tower[][] towers;
	private Virus[] viruses;
	
	public Game(int mapNumber, int difficulty) {
		this.map = new Map(mapNumber);
		this.difficulty = difficulty;
		this.killed = 0;
		this.round = 1;

		this.money = 500 - this.difficulty * 100;
		
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

		this.updateTowers(elapsedTime);
		boolean win = this.updateViruses(elapsedTime);

		return (win) ? 1 : 0;
	}

	private void updateTowers(double elapsedTime) {
		for (int i = 0; i < this.towers.length; i++) {
			for (int j = 0; j < this.towers[i].length; j++) {
				if (this.towers[i][j] != null) {
					this.towers[i][j].attack(this.viruses, elapsedTime);
				}
			}
		}
	}

	private boolean updateViruses(double elapsedTime) {
		boolean win = true;
		int dead = 0;
		for (int i = 0; i < this.viruses.length; i++) {
			if (this.viruses[i].alive()) {
				dead++;
				win = false;
			}
			this.health -= this.viruses[i].update(elapsedTime);
		}

		this.money += (dead - this.killed) * 10;
		this.killed = dead;

		return win;
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
	
	public int[][] getMap() {
		return this.map.getMap();
	}
	
	public Tower[][] getTowers() {
		return this.towers;
	}

	public Virus[] getViruses() {
		return this.viruses;
	}
	
	public void click(int x, int y) {
		int towerPrice = 100 + this.difficulty * 50;

		if (this.map.getMap()[y][x] == 0 && this.towers[y][x] == null) {
			if (this.money >= towerPrice) {
				this.towers[y][x] = new Tower(x, y);
				this.money -= towerPrice;
			}
		}
	}
}
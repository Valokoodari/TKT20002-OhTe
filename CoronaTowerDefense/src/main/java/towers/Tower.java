package towers;

import viruses.Virus;

public class Tower {
	private double cooldown;
	private double[] position;
	private int[] upgrades;

	public Tower(double x, double y) {
		this.position = new double[2];
		this.position[0] = x;
		this.position[1] = y;
		
		this.upgrades = new int[2];

		this.cooldown = 0;
	}

	public void upgrade(int path) {
		if (path < 0 || path >= this.upgrades.length) {
			return;
		}
		if (this.upgrades[path] < 3) {
			this.upgrades[path]++;
		}
	}

	public void attack(Virus[] viruses, double elapsedTime) {
		this.cooldown -= elapsedTime;
		if (this.cooldown <= 0) {
			for (int i = 0; i < viruses.length; i++) {
				if (viruses[i].alive() && inRange(viruses[i])) {
					viruses[i].takeDamage(1);
				}
			}
			this.cooldown = 1;
		}
	}

	public boolean inRange(Virus virus) {
		double range = this.getRange();
		double dx2 = Math.pow(Math.abs(this.position[0] - virus.getPos()[0]), 2);
		double dy2 = Math.pow(Math.abs(this.position[1] - virus.getPos()[1]), 2);
		double distance = Math.sqrt(dx2 + dy2);
		
		return distance <= range;
	}

	public double getRange() {
		return 2.6 + this.upgrades[1] * 0.25;
	}
	
	public int[] getUpgrades() {
		return this.upgrades;
	}
}

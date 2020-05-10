package logic.towers;

import logic.viruses.Virus;

public class Tower {
	private double cooldown;
	private double[] position;
	private int[] upgrades;

	/**
	 * Luo uuden tornin annettuun sijaintiin
	 * 
	 * @param x  tornin sijainnin x-koordinaatti
	 * @param y  tornin sijainnin y-koordinaatti
	 */
	public Tower(double x, double y) {
		this.position = new double[2];
		this.position[0] = x;
		this.position[1] = y;
		
		this.upgrades = new int[2];

		this.cooldown = 0;
	}

	/**
	 * Päivittää tornia yhden pykälän annetulla päivityspolulla.
	 * 
	 * @param path  päivitettävän polun indeksi
	 * @return  tosi, jos päivitys tapahtui
	 */
	public boolean upgrade(int path) {
		if (path < 0 || path >= this.upgrades.length) {
			return false;
		}
		
		if (this.upgrades[path] < 3) {
			this.upgrades[path]++;
			return true;
		}

		return false;
	}

	/**
	 * Torni hyökkää annetulla listalla olevia viruksia kohtaan,
	 * jos virukset ovat riittävän lähellä tornia ja tornin
	 * edellisestä hyökkäyksestä on kulunut riittävästi aikaa.
	 * Torni vahingoittaa pisimmälle päässyttä virusta.
	 * 
	 * @param viruses  lista viruksista
	 * @param elapsedTime  edellisestä kutsusta kulunut aika
	 */
	public void attack(Virus[] viruses, double elapsedTime) {
		this.cooldown -= elapsedTime;
		if (this.cooldown <= 0) {
			for (int i = 0; i < viruses.length; i++) {
				if (viruses[i].alive() && inRange(viruses[i])) {
					viruses[i].takeDamage(1);
					this.cooldown = 0.4;
					return;
				}
			}
		}
	}

	/**
	 * Tarkistaa onko pyydetty virus tornin näköetäisyydellä
	 * 
	 * @param virus  Tarkistettava virus
	 * @return tosi, jos torni näkee viruksen
	 */
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

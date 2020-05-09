package logic.viruses;

public class Virus {
	private String name;
	private int damage;
	private int health;
	private double speed;
	private double[] position;
	private int[][] path;
	private int pathPos;
	
	/**
	 * Luo uuden viruksen annettuun sijaintiin seuraamaan annettua reittiä
	 * 
	 * @param startX  Aloitussijainnin x-koordinaatti
	 * @param startY  Aloitussijainnin y-koordinaatti
	 * @param path    Viruksen kulkeman reitin pisteet
	 */
	public Virus(double startX, double startY, int health, int[][] path) {
		this.name = "SARS-CoV-2";
		
		this.damage = (int) Math.ceil(health / 10.0);
		this.health = health;
		this.speed = 2.0;
		
		this.position = new double[]{startX, startY};
		this.pathPos = -1;
		this.path = path;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double[] getPos() {
		return this.position;
	}
	
	public int getHealth() {
		return this.health;
	}

	/**
	 * Kertoo onko virus vielä olemassa
	 * 
	 * @return viruksen tila
	 */
	public boolean alive() {
		return this.health > 0;
	}

	/**
	 * Saa viruksen ottamaan jonkin positiivisen kokonaisluvun verran vahinkoa
	 * 
	 * @param  points  viruksen ottaman vahingon suuruus
	 */
	public void takeDamage(int points) {
		if (points > 0) {
			this.health -= points;
		}
	}
	
	/**
	 * Saa viruksen tarkistamaan oman tilansa sekä liikkumaan tarvittavan määrän.
	 * 
	 * @param elapsedTime  edellisestä päivityksestä kulunut aika sekunteina
	 * @return viruksen pelaajalle aiheuttama vahinko, jos polun loppu on saavutettu
	 */
	public int update(double elapsedTime) {
		if (this.health <= 0) {
			return 0;
		}

		if (this.pathPos + 1 == this.path.length) {
			this.health = 0;
			return this.damage;
		}
		
		double moveX = this.path[this.pathPos + 1][0] - this.position[0];
		double moveY = this.path[this.pathPos + 1][1] - this.position[1];
		
		this.move(moveX, moveY, elapsedTime);
		
		return 0;
	}

	/**
	 * Liikuttaa virusta kuluneen ajan ja polun seuraavan pisteen mukaisesti.
	 * 
	 * @param moveX  Etäisyys seuraavaan pisteeseen x-akselilla
	 * @param moveY  Etäisyys seuraavaan pisteeseen y-akselilla
	 * @param elapsedTime  Edellisestä kerrasta kulunut aika sekunteina
	 */
	private void move(double moveX, double moveY, double elapsedTime) {
		if (Math.abs(moveX + moveY) < elapsedTime * this.speed) {
			this.pathPos++;
			this.position = new double[] {this.path[this.pathPos][0], this.path[this.pathPos][1]};
		}

		if (Math.abs(moveX) > 1e-12) {
			this.position[0] += (Math.abs(moveX) / moveX) * this.speed * elapsedTime;
		}
		if (Math.abs(moveY) > 1e-12) {
			this.position[1] += (Math.abs(moveY) / moveY) * this.speed * elapsedTime;
		}
	}
}
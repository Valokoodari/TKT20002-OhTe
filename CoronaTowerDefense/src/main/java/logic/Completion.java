package logic;

import dao.SaveDao;
import dao.SqlSaveDao;

public class Completion {
	private SaveDao saveDao;
	private boolean[][] completed;

	/**
	 * Luo uuden olion pitämään kirjaa pelin etenemisestä.
	 * 
	 * @param config pelin konfiguraatio
	 * @param maps karttojen lukumäärä
	 * @param difficulties vaikeustasojen lukumäärä
	 */
	public Completion(Config config, int maps, int difficulties) {
		this.saveDao = new SqlSaveDao(config.dbFileName);
		this.completed = this.saveDao.loadCompletion(maps, difficulties);
	}

	/**
	 * Tallentaa edistymisen suoritetussa kartassa ja vaikeustasossa.
	 * 
	 * @param mapNumber suoritetun kartan numero
	 * @param difficulty suoritettu vaikeustaso numerona
	 */
	public void addCompletion(int mapNumber, int difficulty) {
		this.saveDao.saveCompletion(mapNumber, difficulty);
		this.completed[mapNumber][difficulty] = true;
	}

	/**
	 * Palauttaa vastauksena onko kartta läpäisty kyseisellä vaikeustasolla
	 * 
	 * @param mapNumber kartan numero
	 * @param difficulty vaikeustaso numerona
	 * @return tosi, jos kartta on suoritettu kyseisellä vaikeustasolla
	 */
	public boolean isCompleted(int mapNumber, int difficulty) {
		if (mapNumber >= this.completed.length || mapNumber < 0) {
			return false;
		}
		if (difficulty >= this.completed[mapNumber].length || difficulty < 0) {
			return false;
		}

		return this.completed[mapNumber][difficulty];
	}
}
package logic;

import dao.ConfigDao;

public class Config {
	public int displayWidth;
	public int displayHeight;
	public String dbFileName;
	
	/**
	 * Luo olion säilyttämään tietoja nykyisestä näytöstä
	 * 
	 * @param width  Näytön leveys pikseleissä
	 * @param height  Näytön korkeus pikseleissä
	 */
	public Config(int displayWidth, int displayHeight) {
		ConfigDao configDao = new ConfigDao(displayWidth, displayHeight);

		this.displayWidth = configDao.readDisplayWidth();
		this.displayHeight = configDao.readDisplayHeight();

		this.dbFileName = configDao.readSaveFileName();
	}
}

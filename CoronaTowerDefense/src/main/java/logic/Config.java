package logic;

import dao.ConfigDao;
import dao.FileConfigDao;

public class Config {
	public int displayWidth;
	public int displayHeight;
	public String dbFileName;
	
	/**
	 * Luo olion säilyttämään pelin konfiguraatioita kuten näytön kokoa
	 * 
	 * @param width  Näytön leveys pikseleissä
	 * @param height  Näytön korkeus pikseleissä
	 */
	public Config(int displayWidth, int displayHeight) {
		ConfigDao configDao = new FileConfigDao(displayWidth, displayHeight);

		this.displayWidth = configDao.readDisplayWidth();
		this.displayHeight = configDao.readDisplayHeight();

		this.dbFileName = configDao.readSaveFileName();
	}
}

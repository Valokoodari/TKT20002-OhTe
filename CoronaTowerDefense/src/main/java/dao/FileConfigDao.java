package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileConfigDao implements ConfigDao {
	private String fileName = "config.properties";
	private File file;

	/**
	 * Tarkistaa onko konfiguraatiotiedosto olemassa ja luo tarvittaessa uuden tiedoston.
	 * 
	 * @param displayWidth  Näytön leveys pikseleissä
	 * @param displayHeight  Näytön korkeus pikseleissä
	 */
	public FileConfigDao(int displayWidth, int displayHeight) {
		this.file = new File(this.fileName);

		if (!file.exists()) {
			createConfigFile(displayWidth, displayHeight);
		}
	}

	private void createConfigFile(int dw, int dh) {
		try {
			this.file.createNewFile();

			FileWriter writer = new FileWriter(file);

			writer.write("## Corona Tower Defense config file\n" +
							"saveDB=save.sql\n" +
							"width=" + dw + "\n" +
							"height=" + dh);

			writer.close();
		} catch (Exception e) {
			System.out.println("Could not create a config file.");
		}
	}

	private String readProperty(String name) {
		try {
			Scanner scanner = new Scanner(this.file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				} else if (line.contains(name)) {
					scanner.close();
					return line.split("=")[1];
				}
			}

			scanner.close();
		} catch (Exception e) {
			
		}

		return "";
	}

	/**
	 * Lukee ja palauttaa konfiguraatiotiedoston mukaisen tietokantatiedoston nimen
	 */
	public String readSaveFileName() {
		String value = readProperty("saveDB");

		if (value.length() > 0) {
			return value;
		}

		System.out.println("Invalid config file!");
		return "save.sql"; 
	}

	/**
	 * Lukee ja palauttaa konfiguraatiotiedoston mukaisen näytön leveyden pikseleissä
	 */
	public int readDisplayWidth() {
		String value = readProperty("width");

		if (value.matches("[0-9]+")) {
			return Integer.parseInt(value);
		}

		System.out.println("Invalid config file!");
		return 1920;
	}

	/**
	 * Lukee ja palauttaa konfiguraatiotiedoston mukaisen näytön korkeuden pikseleissä
	 */
	public int readDisplayHeight() {
		String value = readProperty("height");

		if (value.matches("[0-9]+")) {
			return Integer.parseInt(value);
		}

		System.out.println("Invalid config file!");
		return 1080;
	}
}
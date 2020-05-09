package dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import logic.viruses.Virus;

public class FileVirusDao implements VirusDao {
	/**
	 * Lataa tiedostosta halutulle kartalle suunnitellut virusaallot
	 * 
	 * @param mapNumber  kartan numero
	 * @param path  Virusten kulkema polku
	 */
	public Virus[][] loadViruses(int mapNumber, int[][] path) {
		Virus[][] viruses = new Virus[40][1000];

		try {
			InputStream inputStream = getClass().getResourceAsStream("/viruses/" + mapNumber + ".vir");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			for (int n = 0; (line = reader.readLine()) != null; n++) {
				viruses[n] = this.parseLine(line, path);
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println("Resource " + mapNumber + ".map not found");
		}
		
		return viruses;
	}

	private Virus[] parseLine(String line, int[][] path) {
		Virus[] round = new Virus[1000];
		String[] splitLine = line.split(";");
				
		for (int i = 0; i < splitLine.length; i++) {
			String[] parts = splitLine[i].split(",");
			double posX = Double.parseDouble(parts[0]);
			double posY = Double.parseDouble(parts[1]);
			int health = Integer.parseInt(parts[2]);

			round[i] = new Virus(posX, posY, health, path);
		}

		return round;
	}
}
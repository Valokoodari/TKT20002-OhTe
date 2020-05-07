package dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapDao {
	/**
	 * Lataa resursseissa olevista tiedostoista oikean kartan ohjelman muistiin.
	 * 
	 * @param mapNumber  ladattavan kartan numero
	 */
	public int[][] loadMap(int mapNumber) {
		int[][] map = new int[24][32];

		try {
			InputStream inputStream = getClass().getResourceAsStream("/maps/" + mapNumber + ".map");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			int n = 0;
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i += 2) {
					map[n][i / 2] = line.charAt(i) - '0';
				}
				n++;
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println("Resource " + mapNumber + ".map not found");
		}
		
		return map;
	}
}
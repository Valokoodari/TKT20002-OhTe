package logic;

import java.util.Arrays;
import dao.FileMapDao;
import dao.MapDao;

public class Map {
	private int[][] map;
	private int[] startPos;
	private int[] endPos;
	private int[][] path;
	
	/**
	 * Luo uuden kartta-olion, lataa valitun kartan tiedostosta
	 * ja etsii ladatusta kartasta polun viruksia varten.
	 * 
	 * @param mapNumber  Ladattavan kartan nimi
	 */
	public Map(int mapNumber) {
		MapDao mapDao = new FileMapDao();
		this.map = mapDao.loadMap(mapNumber);

		this.findStartAndEndPositions();
		this.findPath();
	}
	
	public int[][] getMap() {
		return this.map;
	}
	
	public int[][] getPath() {
		return this.path;
	}

	/**
	 * Etsii nykyisestä kartasta polun alku- ja loppupisteet
	 */
	private void findStartAndEndPositions() {
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (this.map[i][j] == 2) {
					this.startPos = new int[] {j, i};
				} else if (this.map[i][j] == 3) {
					this.endPos = new int[] {j, i};
				}
			}
		}
	}
	
	/**
	 * Etsii kartasta alku- ja loppupisteiden välisen polun
	 */
	private void findPath() {
		int[][] path = new int[1000][2];
		int length = 1;
		
		path[0] = this.startPos;
		
		int[] current = this.startPos;
		int dir = -1;
		while (!Arrays.equals(current, this.endPos)) {
			dir = this.findNextStep(dir, current, path, length);

			current = path[length];
			length++;
		}
		
		this.path = new int[length][2];
		for (int i = 0; i < length; i++) {
			this.path[i] = path[i];
		}
	}

	/**
	 * Etsii polulle seuraavan pisteen kartalta
	 * 
	 * @param dir  Edellinen kuljettu suunta
	 * @param current  Nykyinen sijainti kartalla
	 * @param path  Tähän mennessä löydetty polku
	 * @param length  Polun pituus tähän mennessä
	 * @return  Suunta johon päädyttiin kulkemaan
	 */
	private int findNextStep(int dir, int[] current, int[][] path, int length) {
		if (current[0] != 0 && this.map[current[1]][current[0] - 1] != 0 && dir != 2) {
			path[length] = new int[] {current[0] - 1, current[1]};
			dir = 0;
		} else if (current[1] != 0 && this.map[current[1] - 1][current[0]] != 0 && dir != 3) {
			path[length] = new int[] {current[0], current[1] - 1};
			dir = 1;
		} else if (current[0] != this.map[0].length - 1 && this.map[current[1]][current[0] + 1] != 0 && dir != 0) {
			path[length] = new int[] {current[0] + 1, current[1]};
			dir = 2;
		} else if (current[1] != this.map.length && this.map[current[1] + 1][current[0]] != 0 && dir != 1) {
			path[length] = new int[] {current[0], current[1] + 1};
			dir = 3;
		}

		return dir;
	}
}
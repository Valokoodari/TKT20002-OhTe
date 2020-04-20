package logic;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Map {
	private int mapNumber;
	private int[][] map;
	private int[] startPos;
	private int[] endPos;
	private int[][] path;
	
	public Map(int mapNumber) {
		this.mapNumber = mapNumber;
		
		this.loadMap(this.mapNumber);
		this.findStartAndEndPositions();
		this.findPath();
	}
	
	public int[][] getMap() {
		return this.map;
	}
	
	public int[][] getPath() {
		return this.path;
	}
	
	private void loadMap(int mapNumber) {
		this.map = new int[24][32];

		try {
			InputStream inputStream = getClass().getResourceAsStream("/maps/" + mapNumber + ".map");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			int n = 0;
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i += 2) {
					this.map[n][i / 2] = line.charAt(i) - '0';
				}
				n++;
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println("Resource " + mapNumber + ".map not found");
		}
	}

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
package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Map {
	private int mapNumber;
	private int[][] map;
	private int[] startPos;
	private int[] finalPos;
	private int[][] path;
	
	public Map(int mapNumber) {
		this.mapNumber = mapNumber;
		
		this.loadMap(this.mapNumber);
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
			File mapFile = new File(getClass().getClassLoader().getResource("maps/" + mapNumber + ".map").getFile());
			Scanner mapScanner = new Scanner(mapFile);
			
			int n = 0;
			while(mapScanner.hasNextLine()) {
				String line = mapScanner.nextLine();
				
				for (int i = 0; i < line.length(); i += 2) {
					this.map[n][i/2] = line.charAt(i) - '0';
					if (this.map[n][i/2] == 2)
						this.startPos = new int[] {i/2, n};
					if (this.map[n][i/2] == 3)
						this.finalPos = new int[] {i/2, n};
				}
				n++;
			}
			
			mapScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Resource " + mapNumber + ".map not found");
		}
	}
	
	private void findPath() {
		int[][] path = new int[1000][2];
		int length = 1;
		
		path[0] = this.startPos;
		
		int[] current = this.startPos;
		int dir = -1;
		while (!Arrays.equals(current, this.finalPos)) {
			if (current[0] != 0 && this.map[current[1]][current[0]-1] != 0 && dir != 2) {
				path[length] = new int[] {current[0]-1, current[1]};
				dir = 0;
			} else if (current[1] != 0 && this.map[current[1]-1][current[0]] != 0 && dir != 3) {
				path[length] = new int[] {current[0], current[1]-1};
				dir = 1;
			} else if (current[0] != this.map[0].length-1 && this.map[current[1]][current[0]+1] != 0 && dir != 0) {
				path[length] = new int[] {current[0]+1, current[1]};
				dir = 2;
			} else if (current[1] != this.map.length && this.map[current[1]+1][current[0]] != 0 && dir != 1) {
				path[length] = new int[] {current[0], current[1]+1};
				dir = 3;
			}
				
			current = path[length];
			length++;
		}
		
		this.path = new int[length][2];
		for (int i = 0; i < length; i++)
			this.path[i] = path[i];
	}
}
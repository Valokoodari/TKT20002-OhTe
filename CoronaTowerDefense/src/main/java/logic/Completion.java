package logic;

import dao.SaveDao;
import dao.SqlSaveDao;

public class Completion {
	private SaveDao saveDao;
	private boolean[][] completed;

	public Completion(Config config, int maps, int difficulties) {
		this.saveDao = new SqlSaveDao(config.dbFileName);
		this.completed = this.saveDao.loadCompletion(maps, difficulties);
	}

	public void addCompletion(int mapNumber, int difficulty) {
		this.saveDao.saveCompletion(mapNumber, difficulty);
		this.completed[mapNumber][difficulty] = true;
	}

	public boolean isCompleted(int mapNumber, int difficulty) {
		if (mapNumber >= this.completed.length || mapNumber < 0) return false;
		if (difficulty >= this.completed[mapNumber].length || difficulty < 0) return false;

		return this.completed[mapNumber][difficulty];
	}
}
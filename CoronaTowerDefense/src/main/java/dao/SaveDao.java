package dao;

public interface SaveDao {
    public void saveCompletion(int mapNumber, int difficulty);
    public boolean[][] loadCompletion(int maps, int difficulties);
}
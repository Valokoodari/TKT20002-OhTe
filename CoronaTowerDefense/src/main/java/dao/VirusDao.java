package dao;

import logic.viruses.Virus;

public interface VirusDao {
    public Virus[][] loadViruses(int mapNumber, int[][] path);
}
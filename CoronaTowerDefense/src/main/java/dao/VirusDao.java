package dao;

import viruses.Virus;

public interface VirusDao {
    public Virus[][] loadViruses(int mapNumber, int[][] path);
}
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlSaveDao implements SaveDao {
	private String connectionDriver;

	/**
	 * Tarkistaa onko tietokanta olemassa ja luo tarvittaessa uuden.
	 * 
	 * @param dbFileName  tietokantatiedoston nimi
	 */
	public SqlSaveDao(String dbFileName) {
		this.connectionDriver = "jdbc:sqlite:" + dbFileName;

		try {
			Connection c = DriverManager.getConnection(this.connectionDriver);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='completed'");
			if (!rs.next()) {
				rs.close();
				s.close();
				c.close();
				this.initializeDatabase();
			}
		} catch (Exception e) {
			System.out.println("Could not read the database");
		}
	}

	private void initializeDatabase() {
		try {
			Connection c = DriverManager.getConnection(this.connectionDriver);
			Statement s = c.createStatement();
			String create = "CREATE TABLE completed " +
							"(id INT PRIMARY KEY, " +
							"map_id INT, " +
							"difficulty INT)";
			
			s.executeUpdate(create);
			s.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			
		}
	}

	/**
	 * Tallentaa suorituksen tietokantaan myöhempää varten.
	 * 
	 * @param mapNumber  suoritetun kartan numero
	 * @param difficulty  suoritettu vaikeustaso numerona
	 */
	public void saveCompletion(int mapNumber, int difficulty) {
		try {
			Connection c = DriverManager.getConnection(this.connectionDriver);
			Statement s = c.createStatement();

			String command = "INSERT INTO completed (map_id, difficulty) " +
								"VALUES (" + mapNumber + ", " + difficulty + ")";
			
			s.executeUpdate(command);

			s.close();
			c.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Lukee tietokannasta kaikki onnistuneet suoritukset
	 * 
	 * @param maps  karttojen lukumäärä
	 * @param difficulties  vaikeustasojen lukumäärä
	 */
	public boolean[][] loadCompletion(int maps, int difficulties) {
		boolean[][] completion = new boolean[maps][difficulties];

		try {
			Connection c = DriverManager.getConnection(this.connectionDriver);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT map_id, difficulty FROM completed");

			while (rs.next()) {
				int map = rs.getInt("map_id");
				int diff = rs.getInt("difficulty");

				completion[map][diff] = true;
			}
		} catch (Exception e) {

		}

		return completion;
	}
}
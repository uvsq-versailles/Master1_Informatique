package fr.uvsq.pglp.dao.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.uvsq.pglp.dao.crud.Personnel.PersonnelBuilder;

public class PersonnelJdbcDAO implements DAO<Personnel> {
	
	private static String dburl = JdbcInitializer.dburl;

	@Override
	public Personnel create(Personnel obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			PreparedStatement prepare = connect.prepareStatement(
					"INSERT INTO personnels (nom, prenom, fonction)" +
					"VALUES (?, ?, ?)");
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());
			prepare.setString(3, obj.getFonction());
			System.out.println("Création " + obj);
			int result = prepare.executeUpdate();
			assert result == 1; // Nombre de tuples affectés = 1
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Personnel read(String id) {
		Personnel p = null;
		try (Connection connect = DriverManager.getConnection(dburl)) {
			System.out.println("Recherche " + id);
			PreparedStatement prepare = connect.prepareStatement(
					"SELECT * FROM personnels WHERE nom = ?");
			prepare.setString(1, id);
			ResultSet result = prepare.executeQuery();
			if(result.next()) {
				p = new PersonnelBuilder(
							result.getString("nom"),
							result.getString("prenom"),
							result.getString("fonction")).build();
				result.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Personnel update(Personnel obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			PreparedStatement prepare = connect.prepareStatement(
					"UPDATE personnels "
					+ "SET prenom = ?"
					+ "SET fonction = ?"
					+ "WHERE nom = ?");
			prepare.setString(1, obj.getPrenom());
			prepare.setString(2, obj.getFonction());
			prepare.setString(3, obj.getNom());
			int result = prepare.executeUpdate();
			assert result == 1;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Maj " + obj);
		return obj;
	}

	@Override
	public void delete(Personnel obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			PreparedStatement prepare = connect.prepareStatement(
					"DELETE FROM personnels "
					+ "WHERE nom = ?");
			prepare.setString(1, obj.getNom());
			int result = prepare.executeUpdate();
			assert result == 1;
			System.out.println("Suppression " + obj);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

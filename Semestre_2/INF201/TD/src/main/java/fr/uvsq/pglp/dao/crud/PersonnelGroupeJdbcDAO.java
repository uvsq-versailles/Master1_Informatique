package fr.uvsq.pglp.dao.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonnelGroupeJdbcDAO implements DAO<PersonnelGroupe> {

	private static String dburl = JdbcInitializer.dburl;

	@Override
	public PersonnelGroupe create(PersonnelGroupe obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			PreparedStatement prepare = connect.prepareStatement(
					"INSERT INTO personnelGroupes (id)" +
					"VALUES (?)");
			prepare.setString(1, obj.getId());
			int result = prepare.executeUpdate();
			assert result == 1; // Nombre de tuples affectés = 1
			List<Personnel> lp = obj.getAllPersonnel().stream()
					.filter(e -> !e.isGroupe())
					.map(e -> (Personnel) e)
					.collect(Collectors.toList());
			for (Personnel p : lp) {
				prepare = connect.prepareStatement(
						"INSERT INTO appartient "
						+ "VALUES (?, ?)");
				prepare.setString(1, obj.getId());
				prepare.setString(2, p.getNom());
				prepare.addBatch();
			}
			prepare.executeBatch();

			System.out.println("Création " + obj);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public PersonnelGroupe read(String id) {
		PersonnelGroupe pg = null;
		try (Connection connect = DriverManager.getConnection(dburl)) {
			System.out.println("Recherche " + id);
			PreparedStatement prepare = connect.prepareStatement(
					"SELECT * FROM appartient WHERE id = ?");
			prepare.setString(1, id);
			pg = new PersonnelGroupe(id);
			ResultSet result = prepare.executeQuery();
			PersonnelJdbcDAO pjd = new PersonnelJdbcDAO();
			while (result.next()) {
		        pg.addPersonnel(pjd.read(result.getString("nom")));
		    }
								
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return pg;
	}

	@Override
	public PersonnelGroupe update(PersonnelGroupe obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			List<Personnel> lp = obj.getAllPersonnel().stream()
				.filter(e -> !e.isGroupe())
				.map(e -> (Personnel) e)
				.collect(Collectors.toList());
			PreparedStatement prepare = connect.prepareStatement(
					"DELETE FROM appartient"
					+ "WHERE id = ?"
			);
			prepare.setString(1, obj.getId());
			for (Personnel p : lp) {
				prepare = connect.prepareStatement(
						"INSERT INTO appartient "
						+ "VALUES (?, ?)");
				prepare.setString(1, obj.getId());
				prepare.setString(2, p.getNom());
				prepare.addBatch();
			}
			prepare.executeBatch();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Maj " + obj);
		return obj;
	}

	@Override
	public void delete(PersonnelGroupe obj) {
		try (Connection connect = DriverManager.getConnection(dburl)) {
			PreparedStatement prepare = connect.prepareStatement(
					"DELETE FROM personnelGroupes "
					+ "WHERE id = ?");
			prepare.setString(1, obj.getId());
			int result = prepare.executeUpdate();
			assert result == 1;
			System.out.println("Suppression " + obj);
			// On suppose délétion de appartient
			// Avec ON DELETE CASCADE lors définition des clés étrangères
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

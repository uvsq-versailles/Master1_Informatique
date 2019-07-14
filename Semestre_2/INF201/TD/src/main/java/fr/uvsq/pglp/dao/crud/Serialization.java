package fr.uvsq.pglp.dao.crud;

import fr.uvsq.pglp.dao.crud.AbstractDAOFactory.DAOType;
import fr.uvsq.pglp.dao.crud.Personnel.PersonnelBuilder;

public enum Serialization {
	ENVIRONNEMENT;
	
	private void run(String[] args, DAOType dt) {
		DAO<Personnel> personnelDAO = AbstractDAOFactory
				.getFactory(dt) // Factory
				.getPersonnelDAO();
		DAO<PersonnelGroupe> pgDAO = AbstractDAOFactory
				.getFactory(dt)
				.getPersonnelGroupeDAO();
		// Creation personnel
		Personnel dupond = new PersonnelBuilder("Dupond", "Dupont", "Employé")
				   			.build();
		Personnel dupont = new PersonnelBuilder("Dupont", "Dupond", "Employé")
							.build();
		PersonnelGroupe pg = new PersonnelGroupe();
		PersonnelGroupe spg = new PersonnelGroupe();
		pg.addPersonnel(spg);
		pg.addPersonnel(dupont);
		spg.addPersonnel(dupond);
		// Ajout DAO
		pgDAO.create(pg);
		pgDAO.create(spg);
		personnelDAO.create(dupond); // update
		personnelDAO.create(dupont); // update
		System.out.println("\t" + pgDAO.read(spg.getId()));
		System.out.println("\t" + personnelDAO.read("Dupont"));
	}
	
	public static void main(String[] args) {
		JdbcInitializer jinit = new JdbcInitializer();
		jinit.dropCreateTables(); // the 1st time, use jinit.createTables()
		// ENVIRONNEMENT.run(args, DAOType.Serial);
		ENVIRONNEMENT.run(args, DAOType.JDBC);
	}
	
}

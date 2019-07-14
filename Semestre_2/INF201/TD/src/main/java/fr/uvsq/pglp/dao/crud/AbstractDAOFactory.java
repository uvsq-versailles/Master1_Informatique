package fr.uvsq.pglp.dao.crud;

public abstract class AbstractDAOFactory {
	public enum DAOType {JDBC, Serial};
	
	public abstract DAO<Personnel> getPersonnelDAO();
	
	public abstract DAO<PersonnelGroupe> getPersonnelGroupeDAO();
	
	public static AbstractDAOFactory getFactory(DAOType type) {
		if (type == DAOType.JDBC) return new JdbcDAOFactory();
		if (type == DAOType.Serial) return new DAOFactory();
		return null;
	}
	
}

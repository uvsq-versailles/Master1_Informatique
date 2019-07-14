package fr.uvsq.pglp.dao.crud;

public class PersonnelSerialDAO extends SerialDAO<Personnel> {

	public String getFilename(String id) {
		return id + ".personnel";
	}
	
	public String getFilename(Personnel obj) {
		return getFilename(obj.getNom());
	}

}

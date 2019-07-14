package fr.uvsq.pglp.dao.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonnelGroupe extends PersonnelType {
	
	private static final long serialVersionUID = 1L;
	
	private final List<PersonnelType> personnelList;
	private UUID id;
	
	public PersonnelGroupe() {
		personnelList = new ArrayList<PersonnelType>();
		id = UUID.randomUUID();
	}

	public PersonnelGroupe(String id) {
		personnelList = new ArrayList<PersonnelType>();
		this.id = UUID.fromString(id);
	}
	
	public void addPersonnel(PersonnelType p) {
		personnelList.add(p);
	}
	
	public List<PersonnelType> getAllPersonnel() {
		return personnelList;
	}

	public String getId() {
		return id.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((personnelList == null) ? 0 : personnelList.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonnelGroupe other = (PersonnelGroupe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (personnelList == null) {
			if (other.personnelList != null)
				return false;
		} else if (!personnelList.equals(other.personnelList))
			return false;
		return true;
	}

	@Override
	public boolean isGroupe() {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonnelGroupe [personnelList=" + personnelList + ", id=" + id + "]";
	}
	
	
	
}

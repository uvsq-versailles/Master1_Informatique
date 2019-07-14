package fr.uvsq.pglp.builder.composite.iterator;

public abstract class PersonnelType {

	abstract public boolean isGroupe();
	
	public String typeString() {
		return (isGroupe() ? "Groupe" : "Personnel");
	}
}

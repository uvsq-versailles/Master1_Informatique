package fr.uvsq.pglp.builder.composite.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonnelGroupe extends PersonnelType implements Iterable<PersonnelType> {
	
	private final List<PersonnelType> personnelList;
	
	public PersonnelGroupe() {
		personnelList = new ArrayList<PersonnelType>();
	}
	
	public void addPersonnel(PersonnelType p) {
		personnelList.add(p);
	}
	
	public void removePersonnel(PersonnelType p) {
		personnelList.remove(p);
	}
	
	public List<PersonnelType> getAllPersonnel() {
		return personnelList;
	}

	@Override
	public Iterator<PersonnelType> iterator() {
		return personnelList.listIterator();
	}
	
	public ParGroupeIterator parGroupeIterator() {
		return new ParGroupeIterator(this);
	}
	
	public ParHierarchieIterator parHierarchieIterator() {
		return new ParHierarchieIterator(this);
	}
	
	@Override
	public boolean isGroupe() {
		return true;
	}
}

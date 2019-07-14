package fr.uvsq.pglp.builder.composite.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class PersonnelTypeIterator implements Iterator<PersonnelType> {
	private PersonnelGroupe racine;
	protected Collection<PersonnelType> collection;

	public PersonnelTypeIterator(PersonnelGroupe personnelGroupe,
			 Collection<PersonnelType> collection) {
		this.racine = personnelGroupe;
		this.collection = collection;
		this.collection.addAll(racine.getAllPersonnel());
	}

	@Override
	public boolean hasNext() {
		return !collection.isEmpty();
	}

	@Override
	public PersonnelType next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        PersonnelType pt = getFromCollection();
        if(pt.isGroupe()) {
        	collection.addAll(((PersonnelGroupe) pt).getAllPersonnel());
        }
		return pt;
	}

	abstract protected PersonnelType getFromCollection();

	public PersonnelType getRacine() {
		return racine;
	}

}

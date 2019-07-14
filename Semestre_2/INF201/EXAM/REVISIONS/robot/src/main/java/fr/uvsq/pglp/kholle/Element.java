package fr.uvsq.pglp.kholle;

import java.util.List;

public abstract class Element{
	protected final String nom;
	
	protected final List<Option> options;
	
	public Element(String nom, List<Option> options) {
		this.nom=nom;
		this.options=options;
	}
	
	public abstract void declineIdentite();
}
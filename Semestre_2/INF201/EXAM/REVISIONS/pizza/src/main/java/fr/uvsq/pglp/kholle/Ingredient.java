package fr.uvsq.pglp.kholle;

public abstract class Ingredient implements Element {
	protected String nom;
	
	public Ingredient() {
		this.nom="Ingredient";
	}
	
	@Override
	public String toString() {
		return nom;
	}

}

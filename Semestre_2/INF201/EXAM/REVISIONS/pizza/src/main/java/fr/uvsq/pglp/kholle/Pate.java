package fr.uvsq.pglp.kholle;

public class Pate extends Ingredient {
	public Pate(String nom) {
		this.nom=nom;
	}
	
	@Override
	public String toString() {
		return "Pate"+nom;
	}
}

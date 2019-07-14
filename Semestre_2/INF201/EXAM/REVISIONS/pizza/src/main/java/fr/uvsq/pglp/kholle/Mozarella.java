package fr.uvsq.pglp.kholle;

public class Mozarella extends Ingredient {
	public Mozarella(String nom) {
		this.nom=nom;
	}
	
	@Override
	public String toString() {
		return "Mozarella"+nom;
	}
}

package fr.uvsq.pglp.kholle;

public class Sauce extends Ingredient {
	public Sauce(String nom) {
		this.nom=nom;
	}
	
	@Override
	public String toString() {
		return "Sauce"+ nom;
	}
}

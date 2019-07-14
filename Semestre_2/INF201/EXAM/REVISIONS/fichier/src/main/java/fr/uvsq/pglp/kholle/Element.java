package fr.uvsq.pglp.kholle;

public abstract class Element{
	protected final String nom;
	protected int taille;
	
	public Element(String nom, int taille) {
		this.nom=nom;
		this.taille=taille;
	}
	
	public abstract void print();
	
	public int getTaille() {
		return this.taille;
	}
}

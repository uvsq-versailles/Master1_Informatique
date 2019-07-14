package fr.uvsq.pglp.kholle.caumes;

public abstract class Element{
	protected String nom;
	protected int poids;
	
	public Element(String nom, int poids) {
		this.nom=nom;
		this.poids=poids;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getPoids() {
		return poids;
	}
	
	public abstract boolean contient(Element e);
	
	public abstract void print();
}
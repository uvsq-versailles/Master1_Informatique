package fr.uvsq.pglp.kholle;

public abstract class Element{
	protected String nom;
	
	protected int cout;
	
	public Element(String nom, int cout) {
		this.nom=nom;
		this.cout=cout;
	}
	
	public int getCout() {
		return cout;
	}
	
	public abstract boolean contient(Element e);
	
	public abstract void print();
}

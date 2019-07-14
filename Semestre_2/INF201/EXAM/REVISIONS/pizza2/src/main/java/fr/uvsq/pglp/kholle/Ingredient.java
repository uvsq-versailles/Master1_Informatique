package fr.uvsq.pglp.kholle;

public class Ingredient extends Element{

	
	public Ingredient(String nom, int cout) {
		super(nom,cout);
	}
	
	@Override
	public void print() {
		System.out.println(this.nom);
		
	}

	@Override
	public boolean contient(Element e) {
		// TODO Auto-generated method stub
		return false;
	}

	

}

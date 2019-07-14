package fr.uvsq.pglp.solid.ocp;

public class Manager extends Employe {
	private int nbSousFifres;
	
	@Override
	public double calculSalaire() {
		return super.calculSalaire() + nbSousFifres * 100;
	}
	
}
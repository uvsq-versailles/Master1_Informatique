package fr.uvsq.pglp.solid.ocp;

public class Vendeur extends Employe {
	private double commission;
	
	@Override
	public double calculSalaire() {
		return super.calculSalaire() + commission;
	}
	
}
package fr.uvsq.pglp.solid.ocp;

public class Employe implements Salarie {
	private int nbAnnees;
	private Coordonnees coordonnees;

	public String getCoordonnees() {
		return coordonnees.toString();
	}
	
	public double calculSalaire() {
		return 1500 + nbAnnees * 20;
	}
	
}
/*
 * Decorateur permet d'implémenter une classe puis de lui rajouter des 
 * fonctionnalités. 
 */


// Déclarations
public abstract class Voiture {
	
	private String nom;
	private String marque;

	abstract int getPrix();
	abstract int getPoids();
}

class DS extends Voiture{

	public DS() {
		this.nom = "DS"; this.marque = "Citroën";
	}	
	int getPrix() {return 30000;}	
	int getPoids() {return 1500;}	
}

// Décorateurs
abstract class VoitureAvecOption extends Voiture{
	Voiture voiture;
}

class VoitureAvecToitOuvrant extends VoitureAvecOption{
	
	int getPrix() {return voiture.getPrix() + 10000;}
	int getPoids() {return voiture.getPoids() + 15;}	
}

//On garde le nom du pattern Decorator pour savoir qu'on wrap un objet 
class DSAvecToitOuvrantDecorator extends VoitureAvecToitOuvrant{
	public DSAvecToitOuvrantDecorator(DS ds) {
		this.voiture = ds;
	}
}

public class Main {
	// ______________________________________________________________________
	// Implémentation
	public static void main(String[] args) {
		Voiture ds = new DS();
		Voiture dsOption = new DSAvecToitOuvrantDecorator((DS) ds);
	}
}

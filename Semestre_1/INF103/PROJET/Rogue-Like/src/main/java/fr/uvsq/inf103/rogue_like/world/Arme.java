package fr.uvsq.inf103.rogue_like.world;

/**
 * Enumeration Arme qui est une caracteristique a choisir
 * pour le joueur.
 */
public enum Arme{
	/**
	 * Differents types d'armes.
	 */
	AUCUNE_ARME("Aucune arme",1),
	COUTEAU("Couteau",3),
	EPEE("Epee",4),
	BATTE_BASEBALL("Batte",5),

	/**
	 * Enumeration supplementaire pour connaitre le nombre d'armes au total.
	 */
	NB_ARMES("null",0);

	/**
	 * Attribut Nom permettant de l'écrire dans le jeu.
	 */
	private String nom;

	/**
	 * Attribut representant le nombre de degats que provoque l'arme.
	 */
	private int degats;

	/**
	 * Accesseur de nom.
	 * @return nom de l'arme.
	 */
	public String getNom(){
		return nom;
	}

	/**
	 * Accesseur du nombre de degats de l'arme.
	 * @return nombre de degats provoques par l'arme.
	 */
	public int getDegats(){ return degats;}

	/**
	 * Constructeur de Arme.
	 * @param nom de l'arme.
	 */
	private Arme(String nom, int degats){
		this.nom=nom;
		this.degats=degats;
	}
}

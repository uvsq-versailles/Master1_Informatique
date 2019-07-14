package systeme_fichiers.main;

/**
 * Abstract class Element - Classe abstraite représentant le repertoire et le fichier
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 */
public abstract class Element {
	/**
	 * Nom de l'élément.
	 */
	protected String nom;
	
	/**
	 * Constructeur de element
	 * @param nom de l'élement
	 */
	public Element(String nom) {
		this.nom=nom;
	}
	
	/**
	 * Methode abstraite pour obtenir la taille
	 * @return taille de l'élément
	 */
	public abstract int getTaille();
	
	/**
	 * Methode qui renvoie vrai si l'élément est contenu
	 * @param e potentiellement contenu
	 * @return vrai ou faux
	 */
	public abstract boolean contient(Element e);
	
	/**
	 * Methode pour obtenir le nom 
	 * @return nom de l'élément
	 */
	public String getNom() {
		return this.nom;
	}
}

package systeme_fichiers.main;

import systeme_fichiers.exceptions.TailleNegativeException;
import systeme_fichiers.exceptions.TailleNulleException;

/**
 * Abstract class Fichier - Classe représentant le fichier
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 */
public class Fichier extends Element{
	
	/**
	 * Taille du fichier.
	 */
	private int taille;
	
	/**
	 * Constructeur de Fichier.
	 * @param nom du fichier.
	 * @param taille du fichier.
	 */
	public Fichier(String nom, int taille) {
		super(nom);
		if(taille==0) throw new TailleNulleException();
		else if(taille<0) throw new TailleNegativeException();
		this.taille=taille;
	}
	
	/**
	 * Accesseur Taille fichier.
	 * @return taille du fichier.
	 */
	@Override
	public int getTaille() {
		return this.taille;
	}
	
	/**
	 * Accesseur Nom fichier.
	 * @return nom du fichier.
	 */
	@Override
	public String getNom() {
		return super.getNom();
	}
	
	/**
	 * Methode qui renvoie vrai si l'élément est contenu
	 * @param e potentiellement jamais contenu 
	 * @return faux toujours car c'est un fichier
	 */
	@Override
	public boolean contient(Element e) {
		return false;
	
	}
}

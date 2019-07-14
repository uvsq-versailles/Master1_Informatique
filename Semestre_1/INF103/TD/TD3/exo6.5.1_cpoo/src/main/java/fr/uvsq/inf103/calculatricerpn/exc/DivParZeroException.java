package fr.uvsq.inf103.calculatricerpn.exc;

/**
 * Classe d'exception qui se déclenche si il y a une divion par zéro 
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class DivParZeroException extends Exception{
	
	/**
	 * Constructeur de l'exception DivParZeroException
	 */
	public DivParZeroException() {
		super("ERREUR CALCULATRICE : Division Par Zéro impossible. ");
	}
}

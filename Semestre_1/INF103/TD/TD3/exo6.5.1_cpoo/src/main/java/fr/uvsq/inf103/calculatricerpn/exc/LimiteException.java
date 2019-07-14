package fr.uvsq.inf103.calculatricerpn.exc;

/**
 * Classe d'exception qui se déclenche si l'opérande dépasse la borne [MIN_VALUES,MAX_VALUES]
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class LimiteException extends Exception{
	
	/**
	 * Constructeur de l'exception LimiteException
	 */
	public LimiteException() {
		super("ERREUR CALCULATRICE : Opérande non située entre les bornes de la calculatrice : ");
	}
}

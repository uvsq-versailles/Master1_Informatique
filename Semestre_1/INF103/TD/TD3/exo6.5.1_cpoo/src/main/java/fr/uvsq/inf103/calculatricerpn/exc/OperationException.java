package fr.uvsq.inf103.calculatricerpn.exc;

/**
 * Classe d'exception qui se déclenche si la pile vide 
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class OperationException extends Exception{
	
	/**
	 * Constructeur de l'exception OperationException
	 */
	public OperationException() {
		super("ERREUR CALCULATRICE : Opération impossible (problème de pile). ");
	}
}

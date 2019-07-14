package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe SauvegardeException qui est une exception particuliere.
 */
public class SauvegardeException extends RuntimeException{
	
	/**
     * Constructeur de SauvegardeException.
     */
    public SauvegardeException(){
        super("Impossible de sauvegarder la partie. ");
    }
}

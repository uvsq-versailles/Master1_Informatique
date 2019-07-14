package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe ChargementException qui est une exception particuliere.
 */
public class ChargementException extends RuntimeException{
	
	/**
     * Constructeur de ChargementException.
     */
    public ChargementException(){
        super("Impossible de charger la sauvegarde. ");
    }
}

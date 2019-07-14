package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe PorteException qui est une exception particuliere.
 */
public class PorteException extends RuntimeException{
	
	/**
     * Constructeur de PorteException.
     */
    public PorteException(){
        super("Manque une porte pour acceder au niveau superieur. ");
    }
}

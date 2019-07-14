package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe ArmeException qui est une exception particuliere.
 */
public class ArmeException extends RuntimeException{
	
	/**
     * Constructeur de ArmeException.
     */
    public ArmeException(){
        super("Manque une arme sur la carte du jeu. ");
    }
}

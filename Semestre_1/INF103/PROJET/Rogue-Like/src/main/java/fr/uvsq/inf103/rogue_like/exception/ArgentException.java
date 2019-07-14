package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe ArgentException qui est une exception particuliere.
 */
public class ArgentException extends RuntimeException{

    /**
     * Constructeur de ArgentException.
     */
    public ArgentException(){
        super("Manque de l'argent sur la carte du jeu. ");
    }
}
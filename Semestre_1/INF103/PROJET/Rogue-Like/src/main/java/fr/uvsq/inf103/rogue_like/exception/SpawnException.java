package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe SpawnException qui est une exception particuliere.
 */
public class SpawnException extends RuntimeException{
	
	/**
     * Constructeur de SpawnException.
     */
    public SpawnException(){
        super("Spawn de Joueur ou de PNJ invalide. ");
    }
}

package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe VillageoisException qui est une exception particuliere.
 */
public class VillageoisException extends RuntimeException{
	
	/**
     * Constructeur de VillageoisException.
     */
    public VillageoisException(){
        super("Manque un villageois pour avoir la clef et acceder au niveau superieur. ");
    }
}

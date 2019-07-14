package fr.uvsq.inf103.rogue_like.exception;

/**
 * Classe VillageoisAgressifException qui est une exception particuliere.
 */
public class VillageoisAgressifException extends RuntimeException{
	
	/**
     * Constructeur de VillageoisAgressifException.
     */
    public VillageoisAgressifException(){
        super("Le villageois ne doit pas attaquer le joueur. ");
    }
}

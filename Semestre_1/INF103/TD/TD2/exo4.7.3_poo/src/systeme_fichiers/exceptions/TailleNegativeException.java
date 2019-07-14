package systeme_fichiers.exceptions;

/**
 * Classe d'exception qui se déclenche si la taille du fichier est negative
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class TailleNegativeException extends RuntimeException{
	
	/**
	 * Constructeur de l'exception Taille Negative Exception
	 */
	public TailleNegativeException() {
		super("La taille est negative");
	}
}

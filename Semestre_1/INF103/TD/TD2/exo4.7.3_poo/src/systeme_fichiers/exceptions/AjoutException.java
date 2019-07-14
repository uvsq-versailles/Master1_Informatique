package systeme_fichiers.exceptions;

/**
 * Classe d'exception qui se déclenche si l'ajout est impossible
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class AjoutException extends RuntimeException{
	
	/**
	 * Constructeur de l'exception Ajout Exception
	 */
	public AjoutException() {
		super("Problème lors de l'ajout.");
	}
}

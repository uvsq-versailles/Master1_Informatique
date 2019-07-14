package systeme_fichiers.exceptions;

/**
 * Classe d'exception qui se déclenche si la taille du fichier est nulle
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 *
 */
public class TailleNulleException extends RuntimeException{
	
	/**
	 * Constructeur de l'exception Taille Nulle Exception
	 */
	public TailleNulleException() {
		super("La taille est nulle");
	}
}

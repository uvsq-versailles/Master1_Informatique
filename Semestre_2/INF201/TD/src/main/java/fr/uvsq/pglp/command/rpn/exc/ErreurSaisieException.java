package fr.uvsq.pglp.command.rpn.exc;


/**
 * Exception dans le cas d'une erreur de saisie
 * @author Jean-Charles
 * @date 30/10/2018
 */

public class ErreurSaisieException extends IllegalArgumentException {

	/**
	 * Constructeur de l'exception
	 */
	public ErreurSaisieException() {
		super("Erreur de saisie.");
	}

}

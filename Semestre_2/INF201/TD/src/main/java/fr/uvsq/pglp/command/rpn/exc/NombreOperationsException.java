package fr.uvsq.pglp.command.rpn.exc;

/**
 * Exception pour le cas où la pile n'a pas été utilisée entièrement par manque d'opérations
 * @author Jean-Charles
 * @date 26/10/2018
 */
public class NombreOperationsException extends IllegalArgumentException {

	/**
	 * Constructeur de l'exception
	 */
	public NombreOperationsException() {
		super("Il n'y a pas assez d'opérations, il reste au moins deux nombres dans la pile");
	}

}

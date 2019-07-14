package fr.uvsq.pglp.command.rpn.exc;

/**
 * Exception pour la division par zéro
 * @author Maxime
 * @date 18/10/2018
 */
public class DivisionParZeroException extends ArithmeticException {

	/**
	 * Constructeur de l'exception
	 */
	public DivisionParZeroException() {
		super("Division par zéro");
	}

}

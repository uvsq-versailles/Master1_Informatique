package fr.uvsq.pglp.command.rpn.exc;

/**
 * @author melanie
 * Exception renvoyée si la valeur est trop grande
 */
public class MinMaxException extends ArithmeticException {

	/**
	 * Constructeur de l'exception
	 */
	public MinMaxException() {
		super("Valeur absolue trop grande pour être gérée");
	}
}

package fr.uvsq.pglp.command.rpn.exc;

/**
 * Exception pour la pile
 * @author Maxime
 * @date 18/10/2018
 */
public class ArgumentPileException extends IllegalArgumentException {

	/**
	 * Constructeur de l'exception
	 */
	public ArgumentPileException() {
		super("Pas assez d'éléments dans la pile pour cette opération");
	}

}

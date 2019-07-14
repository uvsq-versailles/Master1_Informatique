package fr.uvsq.pglp.command.rpn.cmd;


/**
 * @author Maxime
 * Interface fonctionnelle Command
 * # COMMANDE
 */
@FunctionalInterface
public interface Command {
	
	public void apply();
	
}

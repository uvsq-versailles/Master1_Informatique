package fr.uvsq.pglp.command.rpn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.uvsq.pglp.command.rpn.cmd.Command;
import fr.uvsq.pglp.command.rpn.cmd.QuitCommand;
import fr.uvsq.pglp.command.rpn.cmd.UndoCommand;
import fr.uvsq.pglp.command.rpn.cmd.UndoableCommand;

/**
 * @author Maxime
 * Partie PGLP
 * Pattern Command
 * # INVOKER
 */
public class Interpreteur {
	
	private Stack<UndoableCommand> historique;
	private Switch interrupteur;
	private List<String> log;
	
	public Interpreteur() {
		this.historique = new Stack<UndoableCommand>();
		this.interrupteur = new Switch();
		this.log = new ArrayList<String>();
	}
	
	/**
	 * Exécute une commande
	 * @param command
	 */
	private void applyCommand(Command command) {
		command.apply();
	}	
	
	/**
	 * Exécute une commande annulable puis la store dans l'historique
	 * La commande n'est pas ajoutée à l'historique si elle lance une exception
	 * @param command
	 */
	public void applyStoreCommand(UndoableCommand command) {
		try {
			command.apply();
			this.historique.add(command);	
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void undoCommand() {
		this.applyCommand(new UndoCommand(historique));
	}
	
	public void log(String str) {
		this.applyCommand(() -> log.add(str));
	}
	
	public String getLastLog() {
		return log.get(log.size() - 1);
	}
	
	public void shutdown() {
		this.applyCommand(new QuitCommand(interrupteur));
	}
	
	public boolean isOn() {
		return interrupteur.isOn();
	}
	
}

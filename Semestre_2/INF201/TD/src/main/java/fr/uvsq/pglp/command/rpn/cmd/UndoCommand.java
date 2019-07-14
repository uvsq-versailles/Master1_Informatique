package fr.uvsq.pglp.command.rpn.cmd;

import java.util.Stack;

import fr.uvsq.pglp.command.rpn.ExceptionHandler;

public class UndoCommand implements Command {
	
	/**
	 * # RECEIVER: historique et commande
	 */
	
	private UndoableCommand lastCommand;
	
	public UndoCommand(Stack<UndoableCommand> historique) {
		ExceptionHandler.handleStackSize(historique.size(), 1);
		this.lastCommand = historique.pop();
	}

	@Override
	public void apply() {
		lastCommand.undo();		
	}
}

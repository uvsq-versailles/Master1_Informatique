package fr.uvsq.pglp.command.rpn.cmd;

import fr.uvsq.pglp.command.rpn.Switch;

public class QuitCommand implements Command {
	
	/** # RECEIVER: Switch **/
	
	private Switch interrupteur;
	
	public QuitCommand(Switch interrupteur) {
		this.interrupteur = interrupteur;
	}

	@Override
	public void apply() {
		interrupteur.turnOff();
	}
}

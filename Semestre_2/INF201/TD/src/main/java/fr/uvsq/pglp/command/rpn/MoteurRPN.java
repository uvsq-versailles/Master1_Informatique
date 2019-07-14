package fr.uvsq.pglp.command.rpn;
import java.util.Stack;

import fr.uvsq.pglp.command.rpn.cmd.OperationCommand;
import fr.uvsq.pglp.command.rpn.cmd.StoreNbCommand;

/**
 * Gestion de la pile d'opérandes
 * @author melanie
 * # INVOKER
 */
public class MoteurRPN extends Interpreteur {

	private Stack<Double> pile;

	/**
	 * Créé la pile dans laquelle seront stockés les opérandes.
	 */
	public MoteurRPN() {
		this.pile = new Stack<Double>();
	}

	/**
	 * ajoute une opérande nb dans la pile, à condition qu'elle soit dans le bon intervalle.
	 * @param nb 
	 */
	public void enregistrerNb(double nb) {
		this.applyStoreCommand(new StoreNbCommand(pile, nb));
	}

	/**
	 * applique l'opération op aux deux dernières opérandes de la pile 
	 * (lance une exception lorsque la pile ne contient pas assez d'opérandes).
	 * @param op
	 */
	public void appliquerOperation(Operation op) {
		this.applyStoreCommand(new OperationCommand(pile, op));
	}

	/**
	 * méthode d'affichage des opérandes de la pile
	 * @return chaîne de caractères à afficher
	 */
	public String afficherPile() {
		String s = "[";
		for (Double d : this.pile) {
			s += (d + " ");
		}	
		return s.trim() + "]";
	}

	/** 
	 * méthode donnant la taille de la pile 
	 * @return nombre d'opérandes dans la pile
	 */
	public int getSizePile(){
		return this.pile.size();
	}
}

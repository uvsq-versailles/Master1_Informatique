package fr.uvsq.pglp.command.rpn;

import java.util.Scanner;

import fr.uvsq.pglp.command.rpn.exc.ArgumentPileException;
import fr.uvsq.pglp.command.rpn.exc.DivisionParZeroException;
import fr.uvsq.pglp.command.rpn.exc.ErreurSaisieException;
import fr.uvsq.pglp.command.rpn.exc.MinMaxException;
import fr.uvsq.pglp.command.rpn.exc.NombreOperationsException;

/**
 * Gère la saisie RPN.
 * Les nombres sont empilés dans la pile du moteur, les opérations sont appliquées au sommet de la pile.  
 * 
 * @date 26/10/18
 * @author Jean-Charles
 * 
 */
public class SaisieRPN {

	private MoteurRPN moteur;
	private Scanner sc;

	/**
	 * Constructeur de la classe  
	 */	
	public SaisieRPN(){
		moteur = new MoteurRPN();
	}

	/**
	 * Initialise le Scanner
	 * @param nouvelle instance de Scanner
	 */
	public void setScanner(Scanner sc) {
		this.sc = sc;		
	}

	/**
	 * Ferme le Scanner
	 */
	public void closeScanner() {
		sc.close();
	}

	/**
	 * Fonction pour la saisie et l'affichage
	 * Appelée par CalculatriceRPN
	 */
	public void saisie() {
		this.setScanner(new Scanner(System.in));

		String input = ""; 
		String str = "";

		while(moteur.isOn()) {
			System.out.println("Saisir un entier, une opération ou exit pour sortir, puis taper sur entrée");
			try {
				input = scanneLigne();
				str += input + " ";
				System.out.println(str);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
			System.out.println(moteur.afficherPile());
		}

		// checkNombreOperations();

		System.out.println("Fin du programme");
		this.closeScanner();    
	}

	/**
	 * Scanne une ligne
	 * @return operande, double ou exit
	 * @throws DivisionParZeroException
	 * @throws MinMaxException
	 * @throws ArgumentPileException
	 * @throws ErreurSaisieException
	 */
	public String scanneLigne() throws DivisionParZeroException, MinMaxException, 
	ArgumentPileException, ErreurSaisieException {
		String input;
		if (sc.hasNextDouble()) {
			Double d = sc.nextDouble();
			moteur.enregistrerNb(d);
			sc.nextLine();
			input = d.toString();
		} 
		else {
			input = sc.nextLine();
			if (input.equals("exit")) {
				moteur.shutdown();
			}
			else if (input.equals("undo")) {
				moteur.undoCommand();
			}
			else if (isOperation(input)) {
				moteur.appliquerOperation(renvoieOperation(input));	
			}
			else {
				throw new ErreurSaisieException();
			}
		}
		return input;
	}

	/**
	 * Retourne vrai si le string correspond à une opération
	 * @param str
	 * @return booléen
	 */
	private boolean isOperation(String str) {
		return this.renvoieOperation(str) != null;
	}

	/**
	 * Retourne une opération correspondant au string
	 * @param str
	 * @return Opération
	 */
	private Operation renvoieOperation(String str) {
		for (Operation op : Operation.values())
			if (str.equals(op.getSymbole()))
				return op;
		return null;
	}

	/**
	 * Vérifie que l'état de la pile est conforme à la fin de la saisie
	 * @throws NombreOperationsException
	 */
	public void isStackValid() throws NombreOperationsException {
		if (!(this.moteur.getSizePile() == 1)) {throw new NombreOperationsException();}
	}
	
	
	/**
	 * NombreOperationsException
	 */
	private void checkNombreOperations() {
		try {
			this.isStackValid();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}


}
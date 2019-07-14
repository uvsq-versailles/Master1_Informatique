package fr.uvsq.inf103.calculatricerpn.src;

import fr.uvsq.inf103.calculatricerpn.exc.DivParZeroException;
import java.math.*;
/**
 * Enumeration Operation qui permet de définir des constantes pour évaluer le 
 * résultat de l'opération sur deux opérandes.
 * 
 * @author Clément Caumes et Mehdi Merimi
 */
public enum Operation {
	
	/**
	 * Constante qui définit l'opération + (ADDITION)
	 */
	PLUS('+') { 
		
		/**
		 * Méthode de l'évaluation pour l'addition
		 */
		public double eval(double op1, double op2) {
			return op1 + op2;
		}
	},
	
	/**
	 * Constante qui définit l'opération - (SOUSTRACTION)
	 */
	MOINS('-') {
		
		/**
		 * Méthode de l'évaluation pour la soustraction
		 */
		public double eval(double op1, double op2) {
			return op2 - op1;
		}
	},
	
	/**
	 * Constante qui définit l'opération * (MULTIPLICATION)
	 */
	MULT('*') {
		
		/**
		 * Méthode de l'évaluation pour la multiplication
		 */
		public double eval(double op1, double op2) {
			return op1 * op2;
		}
	},
	
	/**
	 * Constante qui définit l'opération / (DIVISION)
	 */
	DIV('/') {
		
		/**
		 * Méthode de l'évaluation pour la division
		 */
		public double eval(double op1, double op2) throws DivParZeroException{
			if(op1==0) throw new DivParZeroException();
			return op2 / op1;
		}
	};
	
	/**
	 * Attribut Symbole représentant l'opérateur (question 3a)
	 */
	private char symbole;
	
	/**
	 * Constructeur privée de l'énumération de l'opération
	 * @param symbole de l'opération
	 */
	private Operation(char symbole) {
		this.symbole=symbole;
	}
	
	/**
	 * Méthode qui retourne le résultat de l'évaluation de l'opération
	 * entre deux opérandes (question 3c)
	 * @param op1 opérande 1
	 * @param op2 opérande 2
	 * @return évalution de l'opération entre les deux opérandes
	 */
	public abstract double eval(double op1, double op2)throws DivParZeroException;
	
	/**
	 * Accesseur du symbole de l'opération
	 * @return char correspond au symbol de l'opération
	 */
	public char getSymbole() {
		return symbole;
	}
}

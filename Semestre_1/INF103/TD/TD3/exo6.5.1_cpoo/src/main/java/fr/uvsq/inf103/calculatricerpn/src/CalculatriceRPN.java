package fr.uvsq.inf103.calculatricerpn.src;

import java.io.ByteArrayInputStream;
import fr.uvsq.inf103.calculatricerpn.exc.*;
import java.util.Scanner;

import java.io.*;

/**
 * Main qui permet d'éxecuter la calculatrice
 * @author Clément Caumes et Mehdi Merimi
 *
 */
public enum CalculatriceRPN {

	CALCULATRICE;

	/**
	 * Demande et Gestion de Saisie puis éxecution de l'opération en cas de saisie correcte
	 * sinon affiche "Saisie incorrecte" 
	 * @param args paramètre à mettre en arguments
	 */
	public void run(String[] args) {

		try {
			SaisieRPN saisie = new SaisieRPN();
			saisie.afficheValues();
			saisie.saisie();
		}
		
		// Exception correspondant au problème de pile
		catch(OperationException e) { 
			System.out.println(e.getMessage());		
		}
		
		// Exeption correspondant aux bornes des valeurs
		catch(LimiteException e) {
			SaisieRPN s_erreur=new SaisieRPN();
			System.out.println(e.getMessage() + s_erreur.getMoteurRPN().specifieMinMaxValue());
		}
		
		// Exception correspondant à la division par zéro
		catch(DivParZeroException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Execute la fonction run
	 * @param args
	 */
	public static void main(String[] args) {
		CALCULATRICE.run(args);
	}
}

package fr.uvsq.inf103.calculatricerpn.src;

import fr.uvsq.inf103.calculatricerpn.exc.DivParZeroException;
import fr.uvsq.inf103.calculatricerpn.exc.LimiteException;
import fr.uvsq.inf103.calculatricerpn.exc.OperationException;
import java.util.Stack;

/**
 * MoteurRPN permet d'enregistrer une opérande, d'appliquer une opération sur les opérandes 
 * et de retourner l'ensemble des opérandes stockées
 * 
 * @author Clément Caumes et Mehdi Merimi
 */
public class MoteurRPN {

	/**
	 * Pile d'opérandes
	 */
	private Stack<Double> pile;
	
	/**
	 * La plus petite valeur représentable par la calcuatrice
	 */
	private static final double MIN_VALUE = -10000.0;
	
	/**
	 * La plus grande valeur représentable par la calcuatrice
	 */
	private static final double MAX_VALUE = 10000.0;
	
	/**
	 * Constructeur de MoteurRPN qui initialise la pile
	 */
	public MoteurRPN() {
		pile = new Stack<Double>();
	}
	
	/**
	 * Méthode qui permet d'enregistrer une opérande (4a)
	 * @param val opérande à enregistrer
	 * @return val opérande enregistrée
	 */
	public Double enregistreOperande(double val)throws LimiteException{
		if(Math.abs(val)>MAX_VALUE) throw new LimiteException();
		if(Math.abs(val)<MIN_VALUE) throw new LimiteException();
		return pile.push(val);
	}
		
	/**
	 * Methode qui fait le calcul de l'opération entre  les deux opérandes 
	 * et stocke le résultat dans la pile
	 * @param op opération qui va faire le calcul
	 * @return resultat du calcul
	 */
	public Double calculeOperation(Operation op)throws DivParZeroException,LimiteException{
		return enregistreOperande(op.eval(pile.pop(), pile.pop()));
	}

	/**
	 * Methode qui permet de savoir si une operation est possible 
	 * @return true ou false
	 */
	public boolean operationPossible(){
		if(pile.size()>=2) return true;
		else return false;
	}

	/**
	 * 	Methode qui affiche l'ntervalle de nombres supporté 
	 * @return
	 */
	public String specifieMinMaxValue(){
		return "MIN_VALUE = " + MIN_VALUE + " et MAX_VALUE = " + MAX_VALUE + ".";
	}

	/**
	 * Methode qui retourne l'ensemble des operandes stockées (4c)
	 * @return la chaine de caracteres representant les operandes stockées
	 */
	public String listeOperandes(){
		String string = "";
		for(Double val: pile){
			string += val + " ";
		}
		return string;
	}
	
	/**
	 * Accesseur de la pile du moteurRPN
	 * @return pile du moteur
	 */
	public Stack<Double> getPile(){
		return this.pile;
	}
}

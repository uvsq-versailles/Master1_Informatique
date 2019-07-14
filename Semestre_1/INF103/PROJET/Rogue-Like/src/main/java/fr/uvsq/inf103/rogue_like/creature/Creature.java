package fr.uvsq.inf103.rogue_like.creature;

import fr.uvsq.inf103.rogue_like.world.*;

import java.awt.Color;

/**
 * Classe Creature representant le joueur et le PNJ.
 */
public abstract class Creature {

	/**
	 * Monde dans lequel est le joueur ou le PNJ.
	 */
	protected Monde monde;

	/**
	 * Coordonnees en abscisse de la creature. 
	 */
	public int x;

	/**
	 * Coordonnees en ordonnee de la creature.
	 */
	public int y;

	/**
	 * Caractere representant le joueur ou le PNJ.
	 */
	private char caractere;

	/**
	 * Couleur de la creature.
	 */
	private Color couleur;

	/**
	 * Nombre de vies du PNJ.
	 */
	protected int vie;

	/**
	 * Coordonnees en abscisse de la creature.
	 * @return Monde de la creature.
	 */
	public Monde getWorld() {return monde;}

	/**
	 * Accesseur du caractere de la creature.
	 * @return caractere representant la creature.
	 */
	public char getCaractere() { return caractere; }

	/**
	 * Accesseur de la couleur de la creature.
	 * @return couleur de la creature.
	 */
	public Color getCouleur() { return couleur; }

	/**
	 * Accesseur du nombre de vies de la creature.
	 * @return entier representant le nombre de vies de la creature.
	 */
	public int getVie(){ return this.vie; }

	/**
	 * Constructeur de Creature.
	 * @param monde monde dans lequel la creature se trouve.
	 * @param caractere caractere de la creature.
	 * @param couleur couleur de la creature.
	 */
	public Creature(Monde monde, char caractere, Color couleur){
		this.monde = monde;
		this.caractere = caractere;
		this.couleur = couleur;
		monde.addAtEmptyLocation(this);
	}

	/**
	 * Methode de test pour le deplacement de la creature.
	 * @param x coordonnees des abscisses de la position de la case.
	 * @param y coordonnees des ordonnees de la position de la case.
	 * @param element nature de la case.
	 * @return boolean true si il peut se deplacer et false sinon.
	 */
	public boolean testerDeplacement(int x, int y, Element element){
		if (element.testerSol()){
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}
}

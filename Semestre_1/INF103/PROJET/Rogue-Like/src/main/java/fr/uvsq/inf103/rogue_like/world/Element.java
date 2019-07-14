package fr.uvsq.inf103.rogue_like.world;

import java.awt.Color;
import asciiPanel.AsciiPanel;

/**
 * Element possible dans un monde cree dans Rogue Like.
 */
public enum Element {
	
    /**
     * Elements representant le sol, les murs, les limites de jeu,
     * les cles, les portes, les armes (couteau, epee, batte), les fioles
     * de vie, l'argent.
     */
    FLOOR((char)250, AsciiPanel.white),
    WALL((char)177, AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightGreen),
    KEY((char)213, AsciiPanel.brightYellow),
    DOOR((char)219, AsciiPanel.brightCyan),
    COUTEAU((char)196, AsciiPanel.brightWhite),
    EPEE((char)244, AsciiPanel.brightWhite),
    BATTE_BASEBALL((char)124, AsciiPanel.brightWhite),
    LIFE((char)3, AsciiPanel.brightRed),
    MONEY ('$', AsciiPanel.brightGreen),
    NB_ELEMENTS('w',AsciiPanel.black);

    /**
     * Representation ASCII de cet element.
     */
    private char caractere;

    /**
     * Couleur de l'element.
     */
    private Color couleur;

    /**
     * Accesseur de la representation ASCII de l'element
     * @return char representant le caractere ASCII de l'element a afficher.
     */
    public char getCaractere() { return caractere; }

    /**
     * Accesseur de la couleur de l'element.
     * @return couleur de l'element.
     */
    public Color getCouleur() { return couleur; }

    /**
     * Constructeur de l'element
     * @param caractere ASCII de l'element.
     * @param color couleur de l'element.
     */
    Element(char caractere, Color color){
        this.caractere = caractere;
        this.couleur = color;
    }

    /**
     * Methode qui teste si on peut marcher sur ce bloc.
     * @return TRUE si on peut marcher sur ce bloc et FALSE sinon.
     */
    public boolean testerSol() {
        return this!=DOOR && this != WALL && this != BOUNDS;
    }
}

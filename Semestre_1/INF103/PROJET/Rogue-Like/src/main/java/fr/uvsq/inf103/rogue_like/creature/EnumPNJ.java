package fr.uvsq.inf103.rogue_like.creature;

import asciiPanel.AsciiPanel;
import java.awt.Color;

/**
 * Enumerations des differentes classes de PNJ possible.
 */
public enum EnumPNJ{

    /**
     * Un PNJ peut etre agressif (zombie ou sorcier) ou pacifique (villageois)
     */
    VILLAGEOIS('V',"Villageois", AsciiPanel.green, 4, 0),
    ZOMBIE('Z', "Zombie", AsciiPanel.brightRed, 5, 1),
    SORCIER('S', "Sorcier", AsciiPanel.brightRed, 7, 3),
    NB_ENUM_PNJ('X', "null", AsciiPanel.white, 0, 0);

    /**
     * Caractere representant le PNJ.
     */
    private char caractere;

    /**
     * Nom utilise pour decrire le PNJ dans le jeu.
     */
    private String nom;

    /**
     * Couleur representant le PNJ.
     */
    private Color couleur;

    /**
     * Nombre de vies pour le PNJ.
     */
    private int vie;

    /**
     * Nombre de degats du PNJ.
     */
    private int degats;

    /**
     * Constructeur de l'enumeration.
     * @param caractere du PNJ.
     * @param color du PNJ.
     * @param vie du PNJ.
     */
    EnumPNJ(char caractere, String nom, Color couleur, int vie, int degats){
        this.caractere=caractere;
        this.nom=nom;
        this.couleur=couleur;
        this.vie=vie;
        this.degats=degats;
    }

    /**
     * Accesseur du caractere ASCII du PNJ.
     * @return caractere representant le PNJ.
     */
    public char getCaractere(){
        return this.caractere;
    }

    /**
     * Accesseur de la couleur de PNJ.
     * @return couleur du PNJ.
     */
    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * Accesseur du nombre de vies du PNJ.
     * @return nombre de vies du PNJ.
     */
    public int getVie(){
        return this.vie;
    }

    /**
     * Accesseur des degats du PNJ en question.
     * @return degats du PNJ.
     */
    public int getDegats() {
        return this.degats;
    }

    /**
     * Accesseur du nom du PNJ.
     * @return nom du PNJ.
     */
    public String getNom(){
        return this.nom;
    }
}

package fr.uvsq.inf103.rogue_like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

import fr.uvsq.inf103.rogue_like.world.*;

/**
 * Classe ChoiceScreen qui s'affichera quand l'utilisateur aura choisi
 * une nouvelle partie. 
 */
public class ChoiceScreen implements Screen {
	
	/**
	 * Difficulte que l'utilisateur choisira.
	 */
	private Difficulte difficulte;
	
	/**
	 * Arme que l'utilisateur choisira.
	 */
	private Arme arme;

	/**
	 * Constructeur de ChoiceScreen qui permet a chaque interaction de l'utilisateur 
	 * de modifier les parametres globaux du jeu. 
	 * @param difficulte du jeu.
	 * @param arme du joueur.
	 */
	public ChoiceScreen (Difficulte difficulte, Arme arme){
		this.difficulte=difficulte;
		this.arme=arme;
	}

    /**
     * Methode qui affiche les interactions possibles avec l'utilisateur.
     * @param terminal represente l'ecran du jeu.
     */
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("Choix de la partie Rogue Like", 1);
        terminal.writeCenter("Choix selectionnes : ", 2);
        terminal.writeCenter(difficulte.getNom() + " - " +arme.getNom(), 3, AsciiPanel.brightRed);
        terminal.writeCenter("-- Difficulte de la partie --", 10);
        terminal.writeCenter("-- [A] FACILE - [B] INTERMEDIAIRE - [C] DIFFICILE - [D] HARDCORE --", 11);
        terminal.writeCenter("-- Choix de l'arme --", 13);
        terminal.writeCenter("-- [E] AUCUNE - [F] COUTEAU - [G] EPEE - [H] BATTE DE BASEBALL --", 14);
        terminal.writeCenter("-- Appuie sur [ENTREE] pour demarrer la partie --", 19);
    }

    /**
     * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
     * @param key touche que l'utilisateur tape sur le clavier.
     * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
     */
    public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
            case KeyEvent.VK_A: this.difficulte=Difficulte.FACILE; break;
            case KeyEvent.VK_B: this.difficulte=Difficulte.INTERMEDIAIRE; break;
            case KeyEvent.VK_C: this.difficulte=Difficulte.DIFFICILE; break;
            case KeyEvent.VK_D: this.difficulte=Difficulte.HARDCORE; break;
            case KeyEvent.VK_E: this.arme=Arme.AUCUNE_ARME; break;
            case KeyEvent.VK_F: this.arme=Arme.COUTEAU; break;
            case KeyEvent.VK_G: this.arme=Arme.EPEE; break;
            case KeyEvent.VK_H: this.arme=Arme.BATTE_BASEBALL; break;
            case KeyEvent.VK_ENTER: return new PlayScreen(1, arme, difficulte,10,0);
        }
        return this;
    }
}

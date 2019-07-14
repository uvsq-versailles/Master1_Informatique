package fr.uvsq.inf103.rogue_like.screen;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import asciiPanel.AsciiPanel;

import fr.uvsq.inf103.rogue_like.creature.*;
import fr.uvsq.inf103.rogue_like.world.*;

/**
 * Classe LoseScreen qui s'affichera quand l'utilisateur aura perdu sa partie.
 */
public class LoseScreen implements Screen {

    /**
     * Methode qui affiche les interactions possibles avec l'utilisateur.
     * @param terminal represente l'ecran du jeu.
     */
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("GAME OVER", 1);
        terminal.writeCenter("-- appuie sur [ENTREE] pour jouer une nouvelle partie --", 10);
    }

    /**
     * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
     * @param key touche que l'utilisateur tape sur le clavier.
     * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
     */
    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new ChoiceScreen(Difficulte.FACILE, Arme.AUCUNE_ARME) : this;
    }
}

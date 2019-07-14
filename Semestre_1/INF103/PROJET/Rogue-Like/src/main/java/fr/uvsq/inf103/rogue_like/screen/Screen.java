package fr.uvsq.inf103.rogue_like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

/**
 * Screen est une interface representant ce qui s'affiche à l'écran.
 * Les classes qui representeront les différents affichages possibles
 * implementeront Screen.
 */
public interface Screen {

    /**
     * Methode permettant d'afficher sur le terminal.
     * @param terminal represente l'ecran du jeu.
     */
    public void displayOutput(AsciiPanel terminal);

    /**
     * Methode permettant a l'utilisateur d'interagir avec l'ecran.
     * @param key touche que l'utilisateur tape sur le clavier.
     * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
     */
    public Screen respondToUserInput(KeyEvent key);
}

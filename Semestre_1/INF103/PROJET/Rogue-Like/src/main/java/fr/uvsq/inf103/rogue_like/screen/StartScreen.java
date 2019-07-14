package fr.uvsq.inf103.rogue_like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import java.io.File;

import fr.uvsq.inf103.rogue_like.sauvegarde.*;
import fr.uvsq.inf103.rogue_like.world.*;

/**
 * Classe StartScreen qui s'affichera quand l'utilisateur aura demarrer le jeu.
 */
public class StartScreen implements Screen {

    String message;

    public StartScreen(){
        this.message=null;
    }

    /**
     * Methode qui affiche les interactions possibles avec l'utilisateur.
     * @param terminal represente l'ecran du jeu.
     */
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("Menu Rogue Like", 1);
        terminal.writeCenter("-- appuie sur [N] pour jouer une nouvelle partie --", 10);
        terminal.writeCenter("-- appuie sur [G] pour jouer la partie sauvegardee --", 11);

        if(this.message!=null) terminal.writeCenter(this.message, 13, AsciiPanel.red);
    }

    /**
     * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
     * @param key touche que l'utilisateur tape sur le clavier.
     * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
     */
    public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
            case KeyEvent.VK_N: return new ChoiceScreen(Difficulte.FACILE, Arme.AUCUNE_ARME);
            //case KeyEvent.VK_G: return new PlayScreen(new Chargement());
            case KeyEvent.VK_G:
                File f=new File("save/save.txt");
                if(!f.isFile()) message="Aucune Sauvegarde stockee";
                else return new PlayScreen(new Chargement());
        }
        return this;
    }
}

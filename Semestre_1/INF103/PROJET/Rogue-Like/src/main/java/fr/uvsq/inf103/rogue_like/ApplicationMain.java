package fr.uvsq.inf103.rogue_like;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import fr.uvsq.inf103.rogue_like.screen.Screen;
import fr.uvsq.inf103.rogue_like.screen.StartScreen;

/**
 * Main de l'application qui va permettre de jouer a Rogue Like
 */
public class ApplicationMain extends JFrame implements KeyListener {
    /**
     * Attribut permettant de remedier a certains problemes de compilation.
     * cf Tuto Trystan's blog.
     */
    private static final long serialVersionUID = 1060623638149583738L;

    /**
     * terminal ou sera affiche le jeu.
     */
    private AsciiPanel terminal;

    /**
     * screen representant l'ecran du jeu.
     */
    private Screen screen;

    /**
     * Constructeur de ApplicationMain qui instance un nouveau
     * terminal et un nouvel ecran.
     */
    public ApplicationMain(){
        super();
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    /**
     * Methode permettant de faire le reaffichage apres chaque interaction
     * avec l'utilisateur.
     */
    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     * Methode permettant de gerer les interactions avec l'utilisateur.
     * L'utilisateur va utiliser le clavier et il va y avoir une
     * mise a jour sur l'ecran affiche.
     * @param e touche pressee par l'utilisateur.
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    /**
     * Methode interne a KeyListener.
     * @param e KeyEvent.
     */
    public void keyReleased(KeyEvent e) { }

    /**
     * Methode interne a KeyListener.
     * @param e KeyEvent.
     */
    public void keyTyped(KeyEvent e) { }

    /**
     * Methode main permettant de jouer a Rogue Like
     * @param args null
     */
    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}

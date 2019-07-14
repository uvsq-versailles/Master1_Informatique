package fr.uvsq.inf103.rogue_like.sauvegarde;

import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.exception.*;
import fr.uvsq.inf103.rogue_like.creature.*;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;

/**
 * Tests unitaires sur la classe Chargement.
 */
public class ChargementTest {

    /**
     * Monde pour la classe Test.
     */
    private	Monde monde;

    /**
     * Tableau d'elements contenant les cases du monde.
     */
    private Element[][] e;

    /**
     * Joueur a initialiser et a tester.
     */
    private Joueur joueur;

    /**
     * Liste de PNJ sur la map.
     */
    private ArrayList <PNJ> listePNJ;

    /**
     * Initialisation de l'arme.
     * @param arme a initialiser.
     */
    private void initialiserArme(Arme arme){
        joueur.setArme(arme);
    }

    /**
     * Initialisation de l'objet en (x,y).
     * @param element a initialiser.
     * @param x abscisse de la map.
     * @param y ordonnee de la map.
     */
    private void initialiserObjet(Element element, int x, int y){
        monde.setElement(x,y,element);
    }

    /**
     * Permet d'initialiser les variables que l'on va tester.
     */
    @Before
    public void initialiser() {
        e = new Element[80][21];
        monde= new Monde(e);

        for(int i=0;i<80;i++) {
            for(int j=0;j<21;j++) {
                monde.setElement(i,j,Element.FLOOR);
            }
        }
        joueur=new Joueur(monde,Arme.AUCUNE_ARME, 10, 10);
        joueur.x=2;
        joueur.y=2;
        this.listePNJ=new ArrayList<PNJ>();
        this.listePNJ.add(new PNJ(monde,EnumPNJ.VILLAGEOIS));
        this.listePNJ.get(0).x = 2;
        this.listePNJ.get(0).y = 1;
        this.listePNJ.add(new PNJ(monde,EnumPNJ.ZOMBIE));
        this.listePNJ.get(1).x = 1;
        this.listePNJ.get(1).y = 2;

    }

    /**
     * Methode pour supprimer le fichier lors de ces tests unitaires.
     */
    @After
    public void finaliser(){
        File f=new File("save/testSauvegardeTest.txt");
        f.delete();
    }

    /**
     * Test si une exception SpawnException est bien jetee si un joueur est au meme endroit qu'un mur.
     */
    @Test (expected=SpawnException.class)
    public void testSpawnExceptionJoueurMur(){
        initialiserObjet(Element.DOOR, 11,11);
        initialiserObjet(Element.WALL, joueur.x, joueur.y);
        Sauvegarde s = new Sauvegarde();
        s.sauvegarderPartie("save/testSauvegardeTest.txt", Difficulte.FACILE, monde, 2, joueur, listePNJ);
        Chargement c = new Chargement("save/testSauvegardeTest.txt");
        c.lireSauvegarde("save/testSauvegardeTest.txt");
    }

    /**
     * Test si une exception SpawnException est bien jetee si un joueur est au meme endroit qu'une porte.
     */
    @Test (expected=SpawnException.class)
    public void testSpawnExceptionJoueurPorte(){
        initialiserObjet(Element.DOOR, joueur.x, joueur.y);
        Sauvegarde s = new Sauvegarde();
        s.sauvegarderPartie("save/testSauvegardeTest.txt", Difficulte.FACILE, monde, 2, joueur, listePNJ);
        Chargement c = new Chargement("save/testSauvegardeTest.txt");
        c.lireSauvegarde("save/testSauvegardeTest.txt");
    }

    /**
     * Test si une exception SpawnException est bien jetee si un joueur est au meme endroit qu'un PNJ.
     */
    @Test (expected=SpawnException.class)
    public void testSpawnExceptionJoueurPNJ(){
        initialiserObjet(Element.DOOR, 11, 11);
        listePNJ.get(0).x=joueur.x; listePNJ.get(0).y=joueur.y;
        Sauvegarde s = new Sauvegarde();
        s.sauvegarderPartie("save/testSauvegardeTest.txt", Difficulte.FACILE, monde, 2, joueur, listePNJ);
        Chargement c = new Chargement("save/testSauvegardeTest.txt");
        c.lireSauvegarde("save/testSauvegardeTest.txt");
    }

    /**
     * Test si une exception PorteException est bien jetee si il manque une porte.
     */
    @Test (expected=PorteException.class)
    public void testManquePorte(){
        Sauvegarde s = new Sauvegarde();
        s.sauvegarderPartie("save/testSauvegardeTest.txt", Difficulte.FACILE, monde, 2, joueur, listePNJ);
        Chargement c = new Chargement("save/testSauvegardeTest.txt");
        c.lireSauvegarde("save/testSauvegardeTest.txt");
    }
}

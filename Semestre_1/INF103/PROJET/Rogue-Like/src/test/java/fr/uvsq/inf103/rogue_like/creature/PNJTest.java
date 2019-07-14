package fr.uvsq.inf103.rogue_like.creature;

import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.exception.*;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import asciiPanel.AsciiPanel;

/**
 * Tests unitaires sur la classe PNJ.
 */
public class PNJTest {

    /**
     * Monde pour la classe Test.
     */
    private	Monde monde;

    /**
     * Tableau d'elements contenant les cases du monde.
     */
    private Element[][] e;

    /**
     * Joueur a initialiser.
     */
    private Joueur joueur;

    /**
     * Liste de PNJ sur la map.
     */
    private ArrayList <PNJ> listePNJ;

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
        e = new Element[70][70];
        monde= new Monde(e);

        for(int i=0;i<70;i++) {
            for(int j=0;j<70;j++) {
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
        this.listePNJ.add(new PNJ(monde,EnumPNJ.ZOMBIE));
        this.listePNJ.get(2).x = 1;
        this.listePNJ.get(2).y = 4;
    }

    /**
     * Test de la methode etreAttaque() par un joueur.
     */
    @Test
    public void testEtreAttaque(){
        int vieAvantAttaque=listePNJ.get(0).getVie();
        listePNJ.get(0).etreAttaque(joueur);
        assertEquals(Arme.AUCUNE_ARME.getDegats(), vieAvantAttaque-listePNJ.get(0).getVie());
    }

    /**
     * Test de la methode attaquerJoueur() par un villageois.
     * Renvoie une exception puisque un villageois est pacifique.
     */
    @Test (expected=VillageoisAgressifException.class)
    public void testAttaquerJoueurVillageois(){
        listePNJ.get(0).attaquerJoueur(joueur);
    }

    /**
     * Test de la methode attaquerJoueur() par un PNJ agressif.
     */
    @Test
    public void testAttaquerJoueurPNJAgressif(){
       int vieAvantAttaque=joueur.getVie();
       listePNJ.get(1).attaquerJoueur(joueur);
       assertEquals(listePNJ.get(1).getClasse().getDegats(), vieAvantAttaque-joueur.getVie());
    }

    /**
     * Test de la methode seDeplacer() sans obstacle.
     */
    @Test
    public void testSeDeplacerPossible(){
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,4);
        listePNJ.get(2).seDeplacer(joueur,listePNJ);
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,3);
    }

    /**
     * Test de la methode seDeplacer() avec obstacle (objet).
     */
    @Test
    public void testSeDeplacerImpossibleObjet(){
        initialiserObjet(Element.WALL,1,3);
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,4);
        listePNJ.get(2).seDeplacer(joueur,listePNJ);
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,4);
    }

    /**
     * Test de la methode seDeplacer() avec obstacle (creature).
     */
    @Test
    public void testSeDeplacerImpossibleCreature(){
        listePNJ.add(new PNJ(monde,EnumPNJ.ZOMBIE));
        listePNJ.get(3).x = 1;
        listePNJ.get(3).y = 3;
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,4);
        listePNJ.get(2).seDeplacer(joueur,listePNJ);
        assertEquals(listePNJ.get(2).x,1);
        assertEquals(listePNJ.get(2).y,4);
    }
}

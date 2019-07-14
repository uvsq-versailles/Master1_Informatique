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
 * Tests unitaires sur la classe Joueur.
 */
public class JoueurTest {

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

    }

    /**
     * Test du bon fonctionnement de l'echange avec le villageois.
     */
    @Test
    public void testFaireEchangeVillageois(){
        String s=joueur.faireEchangeVillageois(this.listePNJ);
        assertEquals(s,"Echange effectue : "+this.listePNJ.get(0).getVolonteArgent()+" $ contre la clef.");

    }

    /**
     * Test du bon echange de l'argent contre la clef avec le villageois.
     */
    @Test
    public void testGetClefTrue(){
        joueur.faireEchangeVillageois(this.listePNJ);
        assertEquals(joueur.getClef(), true);
    }

    /**
     * Test de l'attaque avec un PNJ sans arme.
     */
    @Test
    public void testAttaquePNJAucuneArme(){
        initialiserArme(Arme.AUCUNE_ARME);
        int vieAvantAttaque=this.listePNJ.get(0).getVie();
        joueur.attaquerPNJ(this.listePNJ);
        assertEquals(Arme.AUCUNE_ARME.getDegats(), vieAvantAttaque-this.listePNJ.get(0).getVie());
    }

    /**
     * Test de l'attaque avec un PNJ avec une arme lambda.
     */
    @Test
    public void testAttaquePNJArme(){
        initialiserArme(Arme.COUTEAU);
        int vieAvantAttaque=this.listePNJ.get(0).getVie();
        joueur.attaquerPNJ(this.listePNJ);
        assertEquals(Arme.COUTEAU.getDegats(), vieAvantAttaque-this.listePNJ.get(0).getVie());
    }

    /**
     * Test si l'attaque d'un villageois sur un joueur lance bien une exception.
     */
    @Test(expected=VillageoisAgressifException.class)
    public void testEtreAttaqueVillageois(){
        int vieAvantAttaque=joueur.getVie();
        joueur.etreAttaque(this.listePNJ.get(0));
    }

    /**
     * Test si l'attaque contre un PNJ lambda fonctionne correctement.
     */
    @Test
    public void testEtreAttaquePNJAgressif(){
        int vieAvantAttaque=joueur.getVie();
        joueur.etreAttaque(this.listePNJ.get(1));
        assertEquals(joueur.getVie(), vieAvantAttaque-this.listePNJ.get(1).getClasse().getDegats());
    }

    /**
     * Test si le ramassage d'argent sur le sol marche correctement.
     */
    @Test
    public void testRamasserObjetMoney(){
        initialiserObjet(Element.MONEY,2,2);
        int moneyAvantRamassage=joueur.getArgent();
        joueur.ramasserObjet(monde);
        assertEquals(joueur.getArgent(), moneyAvantRamassage+1);
    }

    /**
     * Test du ramassage de la vie lorsque le joueur n'a pas toute sa vie.
     */
    @Test
    public void testRamasserObjetVieNonPleine(){
        initialiserObjet(Element.LIFE,2,2);
        int vieAvantAttaque=joueur.getVie();
        joueur.etreAttaque(this.listePNJ.get(1));
        joueur.ramasserObjet(monde);
        assertEquals(joueur.getVie(), vieAvantAttaque-this.listePNJ.get(1).getClasse().getDegats()+1);
    }

    /**
     * Test du ramassage de la vie lorsque le joueur a deja toute sa vie.
     */
    @Test
    public void testRamasserObjetViePleine(){
        initialiserObjet(Element.LIFE,2,2);
        int vieAvantAttaque=joueur.getVie();
        assertEquals(null,joueur.ramasserObjet(monde));
        assertEquals(joueur.getVie(), 10);
    }

    /**
     * Test du ramassage de la clef sur le sol.
     */
    @Test
    public void testRamasserObjetClef(){
        initialiserObjet(Element.KEY,2,2);
        assertEquals(joueur.getClef(),false);
        String s=joueur.ramasserObjet(monde);
        assertNotNull(s);
        assertEquals(joueur.getClef(),true);
    }

    /**
     * Test du ramassage du couteau sur le sol.
     */
    @Test
    public void testRamasserObjetCouteau(){
        assertEquals(joueur.getArme(), Arme.AUCUNE_ARME);
        initialiserObjet(Element.COUTEAU,2,2);
        String s=joueur.ramasserObjet(monde);
        assertNotNull(s);
        assertEquals(joueur.getArme(), Arme.COUTEAU);
    }

    /**
     * Test du ramassage de la batte sur le sol.
     */
    @Test
    public void testRamasserObjetBatte(){
        assertEquals(joueur.getArme(), Arme.AUCUNE_ARME);
        initialiserObjet(Element.BATTE_BASEBALL,2,2);
        String s=joueur.ramasserObjet(monde);
        assertNotNull(s);
        assertEquals(joueur.getArme(), Arme.BATTE_BASEBALL);
    }

    /**
     * Test du ramassage de l'epee sur le sol.
     */
    @Test
    public void testRamasserObjetEpee(){
        assertEquals(joueur.getArme(), Arme.AUCUNE_ARME);
        initialiserObjet(Element.EPEE,2,2);
        String s=joueur.ramasserObjet(monde);
        assertNotNull(s);
        assertEquals(joueur.getArme(), Arme.EPEE);
    }

    /**
     * Test si le deplacement est possible sans obstacle.
     */
    @Test
    public void testDeplacementPossible(){
        assertEquals(joueur.x,2);
        assertEquals(joueur.y,2);
        joueur.seDeplacer(1,0,this.listePNJ);
        assertEquals(joueur.x,3);
        assertEquals(joueur.y,2);
    }

    /**
     * Test si le deplacement est bien impossible avec un mur sur la case.
     */
    @Test
    public void testDeplacementImpossibleMur(){
        assertEquals(joueur.x,2);
        assertEquals(joueur.y,2);
        initialiserObjet(Element.WALL,3,2);
        joueur.seDeplacer(1,0,this.listePNJ);
        assertEquals(joueur.x,2);
        assertEquals(joueur.y,2);
    }

    /**
     * Test si le deplacement est bien impossible avec une creature sur la case.
     */
    @Test
    public void testDeplacementImpossibleCreature(){
        assertEquals(joueur.x,2);
        assertEquals(joueur.y,2);
        joueur.seDeplacer(-1,0,this.listePNJ);
        assertEquals(joueur.x,2);
        assertEquals(joueur.y,2);
    }
}



package fr.uvsq.inf103.rogue_like.world;

import fr.uvsq.inf103.rogue_like.exception.*;

/**
 * Classe permettant de construire le monde.
 */
public class MondeBuilder {

    /**
     * Largeur du monde.
     */
    private int largeur;

    /**
     * Longueur du monde.
     */
    private int longueur;

    /**
     * Tableau d'elements du monde.
     */
    private Element[][] elements;

    /**
     * Accesseur du tableau d'elements du monde.
     * @return tableau d'elements du monde. 
     */
    public Element[][] getElements() {
    	return elements;
    }

    /**
     * Accesseur de la nature de l'element en (x,y)
     * @param x abscisse de la case.
     * @param y ordonnee de la case.
     * @return Element de la case.
     */
    public Element getElement(int x,int y) {
        return elements[x][y];
    }

    /**
     * Methode permettant de modifier la nature d'une case de la map.
     * @param x abscisse de la case a modifier.
     * @param y ordonnee de la case a modifier.
     * @param e nature de la future case a modifier.
     */
    public void setElement(int x,int y,Element e)
    {
        elements[x][y]=e;
    }

    /**
     * Constructeur de MondeBuilder.
     * @param largeur du monde.
     * @param longueur du monde.
     */
    public MondeBuilder(int largeur, int longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.elements = new Element[largeur][longueur];
    }

    /**
     * Methode de construction aleatoire de murs sur le monde.
     * @param x abscisse du mur potentiel a ajouter.
     * @param y ordonnee du mur potentiel a ajouter.
     * @return MondeBuilder avec les murs construits.
     */
    private MondeBuilder construitMur(int x, int y) {
        int i=0;
        // creation d'un mur de 3 en largeur
        if(Math.random()<0.5){
            while((i<3)&&(x<largeur)){
                elements[x][y]=Element.WALL;
                i++;
                x++;
            }
        }
        // creation d'un mur de 3 en longueur
        else {
            while((i<3)&&(y<longueur)){
                elements[x][y]=Element.WALL;
                i++;
                y++;
            }
        }
        return this;
    }

    /**
     * Methode de creation aleatoire d'elements sur le monde.
     * @return MondeBuilder avec le monde construit.
     */
    private MondeBuilder creerElementsAleatoires() {
        //on met du sol partout
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < longueur; y++) {
                elements[x][y]=Element.FLOOR;
            }
        }

        // on met des murs a des endroits aleatoires
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < longueur; y++) {
                if(Math.random()>=0.99){
                    this.construitMur(x,y);
                }
            }
        }

        // on met 10 dollars sur la map
        int x_random, y_random;
        for(int money=0 ; money<10 ; money++) {
            x_random=(int)(Math.random() * largeur);
            y_random=(int)(Math.random() * longueur);
            while(elements[x_random][y_random]!=Element.FLOOR){
                x_random=(int)(Math.random() * largeur);
                y_random=(int)(Math.random() * longueur);
            }
            elements[x_random][y_random]=Element.MONEY;

        }

        // on met une porte
        x_random=(int)(Math.random() * largeur);
        y_random=(int)(Math.random() * longueur);
        while(elements[x_random][y_random]!=Element.FLOOR){
            x_random=(int)(Math.random() * largeur);
            y_random=(int)(Math.random() * longueur);
        }
        elements[x_random][y_random]=Element.DOOR;

        // on met une arme
        x_random=(int)(Math.random() * largeur);
        y_random=(int)(Math.random() * longueur);
        int type_arme;
        while(elements[x_random][y_random]!=Element.FLOOR){
            x_random=(int)(Math.random() * largeur);
            y_random=(int)(Math.random() * longueur);
        }
        Arme nb_armes=Arme.NB_ARMES;
        Arme arme_choisi;
        do{
            type_arme=(int)(Math.random() * nb_armes.ordinal()+1); // ordinal recupere le nombre d'enum
            arme_choisi=Arme.values()[type_arme];
        }while((arme_choisi==Arme.AUCUNE_ARME)||(arme_choisi==Arme.NB_ARMES));
        if(arme_choisi==Arme.BATTE_BASEBALL) elements[x_random][y_random]=Element.BATTE_BASEBALL;
        else if(arme_choisi==Arme.COUTEAU) elements[x_random][y_random]=Element.COUTEAU;
        else if(arme_choisi==Arme.EPEE) elements[x_random][y_random]=Element.EPEE;

        // on met 2 vies sur la map
        for(int vie=0 ; vie<2 ; vie++) {
            x_random=(int)(Math.random() * largeur);
            y_random=(int)(Math.random() * longueur);
            while(elements[x_random][y_random]!=Element.FLOOR){
                x_random=(int)(Math.random() * largeur);
                y_random=(int)(Math.random() * longueur);
            }
            elements[x_random][y_random]=Element.LIFE;
        }
        return this;
    }

    /**
     * Methode permettant de savoir si la configuration de la map est correcte.
     * Elle jete differentes exceptions en fonction des differents problemes rencontres.
     * @throws PorteException s'il n'y a pas de porte sur la map.
     * @throws ArgentException s'il n'y a pas assez d'argent sur la map.
     * @throws ArmeException s'il n'y a pas d'arme sur la map.
     */
    public void testerConfigurationValide() throws PorteException, ArgentException, ArmeException{
        int compteurPorte, compteurArgent, compteurArme;
        compteurPorte=compteurArgent=compteurArme=0;
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < longueur; y++) {
                if(elements[x][y]==Element.DOOR) compteurPorte++;
                else if(elements[x][y]==Element.MONEY) compteurArgent++;
                else if((elements[x][y]==Element.COUTEAU)||(elements[x][y]==Element.EPEE)||(elements[x][y]==Element.BATTE_BASEBALL)) compteurArme++;
            }
        }
        if(compteurPorte!=1) throw new PorteException();
        if(compteurArgent!=10) throw new ArgentException();
        if(compteurArme!=1) throw new ArmeException();
    }

    /**
     * Methode de fabrication des elements sur la map.
     * @return MondeBuilder avec les elements sur la map.
     */
    public MondeBuilder fabriquerElements() {
        MondeBuilder m=creerElementsAleatoires();
        testerConfigurationValide();
        return m;
    }

    /**
     * Methode de construction du monde.
     * @return Monde nouvellement cree.
     */
    public Monde construire() {
        return new Monde(elements);
    }

}

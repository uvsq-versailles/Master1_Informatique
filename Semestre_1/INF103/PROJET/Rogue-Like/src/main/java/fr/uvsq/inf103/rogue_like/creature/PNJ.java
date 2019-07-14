package fr.uvsq.inf103.rogue_like.creature;

import asciiPanel.AsciiPanel;
import java.util.ArrayList;

import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.exception.*;


/**
 * Classe PNJ representant les creatures pacifiques et agressives.
 */
public class PNJ extends Creature{

    /**
     * Classe de la creature.
     */
    private EnumPNJ classe;

    /**
     * Volonte du PNJ a vouloir de l'argent contre la clef
     * (specifique pour le Villageois).
     */
    private int volonteArgent;

    /**
     * Variable representant si le Villageois possede la clef.
     * (specifique pour le villageois).
     */
    private boolean possedeClef;

    /**
     * Constructeur de PNJ.
     * @param monde dans lequel se trouve le PNJ.
     * @param classe_pnj classe du PNJ.
     */
    public PNJ(Monde monde, EnumPNJ classe_pnj){
        super(monde, classe_pnj.getCaractere(), classe_pnj.getCouleur());
        this.classe=classe_pnj;
        this.vie=classe.getVie();
        if(classe_pnj==EnumPNJ.VILLAGEOIS){
            int argent_voulu=(int)(Math.random() * 7)+1;
            this.volonteArgent=argent_voulu;
            this.possedeClef=true;
        }
        else{
            this.volonteArgent=0;
            this.possedeClef=false;
        }
    }

/**
     * Constructeur de PNJ.
     * @param monde dans lequel se trouve le PNJ.
     * @param classe_pnj classe du PNJ.
     * @param x abscisse du PNJ. 
     * @param y ordonnee du PNJ.
     * @param vie du PNJ. 
     * @param volonteArgent du PNJ.
     * @param clef du PNJ (true ou false). 
     */
    public PNJ(Monde monde, EnumPNJ classe_pnj, int x, int y, int vie, int volonteArgent, boolean clef){
        super(monde, classe_pnj.getCaractere(), classe_pnj.getCouleur());
        this.classe=classe_pnj;
        this.vie=vie;
        this.volonteArgent=volonteArgent;
        this.possedeClef=clef;
        this.x=x;
        this.y=y;
    }

    /**
     * Accesseur pour savoir si le pnj possede la clef
     * @return true si il possede la clef et false sinon.
     */
    public boolean testPossedeClef(){
        return this.possedeClef;
    }

    /**
     * Methode pour changer le fait que le pnj possede la clef.
     * @param cond true ou false
     */
    public void setPossedeClef(boolean cond){
        this.possedeClef=cond;
    }

    /**
     * Accesseur de la classe de PNJ.
     * @return differents types de PNJ.
     */
    public EnumPNJ getClasse(){
        return this.classe;
    }

    /**
     * Accesseur pour savoir combien veut le villageois en echange de la clef.
     * @return entier representant la somme d'argent qu'il veut.
     */
    public int getVolonteArgent(){
        return this.volonteArgent;
    }

    /**
     * Methode qui permet au joueur d'attaquer le PNJ.
     * @param joueur qui attaque
     * @return false si le PNJ est mort et true sinon.
     */
    public boolean etreAttaque(Joueur joueur){
        if(this.vie-joueur.getArme().getDegats()<=0) return false;
        else{
            this.vie-=joueur.getArme().getDegats();
            return true;
        }
    }

    /**
     * Methode qui permt au PNJ de frapper le joueur.
     * @param joueur a frapper.
     * @return false si le joueur est mort et true sinon.
     */
    private boolean frapperJoueur(Joueur joueur) throws VillageoisAgressifException {
        if(this.classe==EnumPNJ.VILLAGEOIS) throw new VillageoisAgressifException();
        joueur.etreAttaque(this);
        if(joueur.getVie()-this.getClasse().getDegats()==0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Methode qui permet au PNJ d'attaquer le joueur.
     * @param joueur a attaquer
     */
    public void attaquerJoueur(Joueur joueur){
        boolean attaque=false;
        if((this.x==joueur.x+1)&&(this.y==joueur.y)){
            attaque=true;
        }
        else if((this.x==joueur.x)&&(this.y==joueur.y+1)){
            attaque=true;
        }
        else if((this.x==joueur.x-1)&&(this.y==joueur.y)){
            attaque=true;
        }
        else if((this.x==joueur.x)&&(this.y==joueur.y-1)){
            attaque=true;
        }
        if(attaque==true){
            this.frapperJoueur(joueur);
        }
    }


    //zone autour du PNJ ou il est attire par le joueur

    /**
     * Methode qui permet de savoir si le joueur est dans une zone visible par le PNJ.
     * @param joueur dont le PNJ est attire.
     * @return true si le PNJ est attire par le joueur et false sinon.
     */
    private boolean etreAttire(Joueur joueur){
        if((Math.abs(joueur.x-this.x)<=6)&&(Math.abs(joueur.y-this.y)<=6)){
            return true;
        }
        return false;
    }

    /**
     * Methode permettant au PNJ de se deplacer sur la map sur laquelle se trouve les joueurs et les autres PNJ.
     * @param joueur sur la map.
     * @param listePNJ liste des PNJ sur la map.
     */
    public void seDeplacer(Joueur joueur, ArrayList<PNJ> listePNJ){
        int mx, my; mx=my=0;
        if((this.etreAttire(joueur))&&(this.getClasse()!=EnumPNJ.VILLAGEOIS)){ //le pnj le suit

            if((Math.abs(joueur.y-this.y)<=6)&&(joueur.y<this.y)&&(Math.abs(joueur.x-this.x)<=1)){ //zone 4
                mx=0; my=-1;
            }
            else if((Math.abs(joueur.y-this.y)<=6)&&(joueur.y>this.y)&&(Math.abs(joueur.x-this.x)<=1)){ //zone 2
                mx=0; my=1;
            }
            else if((Math.abs(joueur.x-this.x)<=6)&&(joueur.x>this.x)){ //zone 3
                mx=1; my=0;
            }
            else if((Math.abs(joueur.x-this.x)<=6)&&(joueur.x<this.x)){ //zone 1
                mx=-1; my=0;
            }
        }
        else if(this.getClasse()!=EnumPNJ.VILLAGEOIS){ //deplacement aleatoire
            int deplacement = (int)(Math.random() * 4);
            if(deplacement==0) { mx=0; my=1;}
            else if(deplacement==1) {mx=0; my=-1;}
            else if(deplacement==2) {mx=1; my=0;}
            else if(deplacement==3) {mx=-1; my=0;}
        }

        boolean test=true;
        for(int i=0; i<listePNJ.size(); i++){ // permet au PNJ de ne pas etre sur une case ou se trouve deja un PNJ.
            if(listePNJ.get(i)!=this){
                if((listePNJ.get(i).x==x+mx)&&(listePNJ.get(i).y==y+my)){
                    test=false;
                }
            }
        }
        // si le joueur est a portee du pnj
        if((joueur.x==x+mx)&&(joueur.y==y+my)){

            test=false;
        }
        if(test==true) testerDeplacement(x+mx, y+my, this.monde.getElement(x+mx, y+my));
        if(this.getClasse()!=EnumPNJ.VILLAGEOIS) this.attaquerJoueur(joueur);
    }
}

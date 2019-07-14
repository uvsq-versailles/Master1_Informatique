package fr.uvsq.inf103.rogue_like.creature;

import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.exception.*;

import asciiPanel.AsciiPanel;
import java.util.ArrayList;

/**
 * Classe Joueur de Rogue Like.
 */
public class Joueur extends Creature{

	/**
	 * Arme du joueur.
	 */
	private Arme arme;

	/**
	 * Argent possede par le joueur.
	 */
	private int argent;

	/**
	 * Variable permettant de savoir si le joueur possede la clef.
	 */
	private boolean clef;

	/**
	 * Constructeur de Joueur.
	 * @param monde monde dans lequel joue l'utilisateur.
	 * @param arme du joueur.
	 * @param vie du joueur.
	 * @param argent du joueur.
	 */
	public Joueur(Monde monde, Arme arme, int vie, int argent){
		super(monde, '@', AsciiPanel.brightWhite);
		this.arme=arme;
		this.vie=vie;
		this.argent=argent;
		this.clef=false;
	}

	/**
	 * Constructeur de Joueur.
	 * @param monde monde dans lequel joue l'utilisateur.
	 * @param arme du joueur.
	 * @param vie du joueur.
	 * @param argent du joueur.
	 * @param clef du joueur (true ou false). 
	 * @param x abscisse du joueur. 
	 * @param y ordonnee du joueur. 
	 */
	public Joueur(Monde monde, Arme arme, int vie, int argent, boolean clef, int x, int y){
		super(monde, '@', AsciiPanel.brightWhite);
		this.arme=arme;
		this.vie=vie;
		this.argent=argent;
		this.clef=clef;
		this.x=x;
		this.y=y;
	}

	/**
	 * Accesseur de l'arme du joueur.
	 * @return arme du joueur.
	 */
	public Arme getArme(){
		return this.arme;
	}

	/**
	 * Modifieur de l'arme du joueur.
	 * @param arme du joueur
	 */
	public void setArme(Arme arme){
		this.arme=arme;
	}

	/**
	 * Accesseur du nombre de pieces que le joueur detient.
	 * @return nombre de pieces du joueur.
	 */
	public int getArgent(){
		return this.argent;
	}

	/**
	 * Accesseur pour savoir si le joueur possede la clef.
	 * @return boolean true si il possede la clef et false sinon.
	 */
	public boolean getClef(){
		return this.clef;
	}

	/**
	 * Methode permettant de laisser la clef a chaque fois qu'il rentre dans une salle.
	 */
	public void laisserClef(){ this.clef=false;}

	/**
	 * Methode qui permet au joueur d'echanger avec le PNJ villageois.
	 * @param listePNJ PNJ de la map.
	 * @return pnj a qui le joueur va echanger ou null si il n'y a pas de villageois autour de lui.
	 */
	private PNJ demanderEchangeAlentour(ArrayList <PNJ> listePNJ) {
		PNJ pnj;
		for (int i = 0; i < listePNJ.size(); i++) {
			pnj = listePNJ.get(i);
			if ((pnj.getClasse() == EnumPNJ.VILLAGEOIS) && (pnj.x == x + 1) && (pnj.y == y)) return pnj;
			else if ((pnj.getClasse() == EnumPNJ.VILLAGEOIS) && (pnj.x == x - 1) && (pnj.y == y))
				return pnj;
			else if ((pnj.getClasse() == EnumPNJ.VILLAGEOIS) && (pnj.x == x) && (pnj.y == y + 1))
				return pnj;
			else if ((pnj.getClasse() == EnumPNJ.VILLAGEOIS) && (pnj.x == x) && (pnj.y == y - 1))
				return pnj;
		}
		return null;
	}

	/**
	 * Methode pour faire un echange avec le PNJ villageois.
	 * @param listePNJ liste des PNJ sur la map.
	 * @return String representant ce que dit le villageois ou null.
	 */
	public String faireEchangeVillageois(ArrayList <PNJ> listePNJ){
		PNJ pnj=demanderEchangeAlentour(listePNJ);
		if(pnj==null) return null;

		if(pnj.getClasse()!=EnumPNJ.VILLAGEOIS){ //si c'est un PNJ agressif
			return null;
		}
		else { //si c'est un villageois
			int volonteArgent=pnj.getVolonteArgent();
			if(pnj.testPossedeClef()){ //si le villageois possede la clef
				if(volonteArgent<=this.getArgent()){ // si le joueur a assez d'argent
					//affichage echange effectue
					this.argent-=volonteArgent;
					this.clef=true;
					pnj.setPossedeClef(false);
					return ("Echange effectue : "+volonteArgent+" $ contre la clef.");
				}
				else { //si le joueur n'a pas assez d'argent
					return ("Il faut "+volonteArgent+" $ pour avoir la clef.");
				}
			}
			else{ //si le villageois ne possede plus la clef
				return ("Le villageois n'a plus de clef.");
			}
		}
	}

	/**
	 * Methode appelee lorsque le joueur est attaque par un PNJ.
	 * @param pnj qui attaque le joueur
	 * @return false si le joueur est mort et true s'il n'est pas mort.
	 */
	public boolean etreAttaque(PNJ pnj){
		if(pnj.getClasse()==EnumPNJ.VILLAGEOIS) throw new VillageoisAgressifException();
		if(this.vie-pnj.getClasse().getDegats()<=0){
			this.vie=0;
			return false;
		}
		else{
			this.vie-=pnj.getClasse().getDegats();
			return true;
		}
	}

	/**
	 * Methode qui permet au joueur d'attaquer les PNJ autour de lui.
	 * @param listePNJ liste des PNJ sur la map.
	 * @return String representant le message affiche sur le jeu ou null.
	 */
	public String attaquerPNJ(ArrayList <PNJ> listePNJ){
		PNJ pnj;
		boolean estAttaque=false;
		for (int i = 0; i < listePNJ.size(); i++) {
			pnj = listePNJ.get(i);
			if((pnj.x==this.x+1)&&(pnj.y==this.y)){
				estAttaque=true;
			}
			else if((pnj.x==this.x)&&(pnj.y==this.y+1)){
				estAttaque=true;
			}
			else if((pnj.x==this.x-1)&&(pnj.y==this.y)){
				estAttaque=true;
			}
			else if((pnj.x==this.x)&&(pnj.y==this.y-1)){
				estAttaque=true;
			}
			if(estAttaque==true){
				if(pnj.etreAttaque(this)==false){
					listePNJ.remove(pnj);
					return pnj.getClasse().getNom()+" mort.";
				}
				else return "Degats de "+this.getArme().getDegats()+".";
			}
			estAttaque=false;
		}
		return null;
	}

	/**
	 * Methode permettant au joueur de ramasser ce qu'il y a sur le sol.
	 * @param world sur lequel le joueur se trouve.
	 * @return String representant l'affichage de ce qu'il ramasse.
	 */
	public String ramasserObjet(Monde world){
		Element element=world.getElement(this.x, this.y);
		// si il y a de l'argent on augmente son porte monnaie et on enleve l'argent sur le sol.
		if(element==Element.MONEY){
			world.setElement(this.x, this.y, Element.FLOOR);
			this.argent++;
			return "1$ ramasse.";
		}
		// si il a de la vie, on augmente sa vie si elle est inferieure a 10 et on enleve la vie sur le sol.
		else if((element==Element.LIFE)&&(this.vie<10)){
			world.setElement(this.x, this.y, Element.FLOOR);
			this.vie++;
			return "1 fiole de vie ramassee.";
		}
		// si il y a la clef, on recupere la clef.
		else if((element==Element.KEY&&(this.clef==false))){
			world.setElement(this.x, this.y, Element.FLOOR);
			this.clef=true;
			return "1 clef ramassee";
		}
		// si il s'agit d'une arme, on l'echange avec son arme courante (si il n'en a pas on prend juste l'arme).
		else if((element==Element.BATTE_BASEBALL)||(element==Element.COUTEAU)||(element==Element.EPEE)){
			Arme arme_deja_possede=this.arme;
			// si il y a une batte par terre on la ramasse
			if(world.getElement(this.x, this.y)==Element.BATTE_BASEBALL){
				this.arme=Arme.BATTE_BASEBALL;
				world.setElement(this.x, this.y, Element.FLOOR);
			}
			// si il y a un couteau par terre on la ramasse
			else if (world.getElement(this.x, this.y)==Element.COUTEAU){
				this.arme=Arme.COUTEAU;
				world.setElement(this.x, this.y, Element.FLOOR);
			}
			// si il y a un epee par terre on la ramasse
			else if (world.getElement(this.x, this.y)==Element.EPEE){
				this.arme=Arme.EPEE;
				world.setElement(this.x, this.y, Element.FLOOR);
			}
			// si l'arme anciennement possedee etait une batte on la pose par terre
			if(arme_deja_possede==Arme.BATTE_BASEBALL){
				world.setElement(this.x, this.y, Element.BATTE_BASEBALL);
			}
			// si l'arme anciennement possedee etait un couteau on la pose par terre
			else if(arme_deja_possede==Arme.COUTEAU){
				world.setElement(this.x, this.y, Element.COUTEAU);
			}
			// si l'arme anciennement possedee etait une epee on la pose par terre
			else if(arme_deja_possede==Arme.EPEE){
				world.setElement(this.x, this.y, Element.EPEE);
			}
			return "1 arme ramassee";
		}
		return null;
	}

	/**
	 * Methode de deplacement pour le joueur.
	 * @param mx coordonnees en deplacement en abscisse.
	 * @param my coordonnees en deplacement en ordonnee.
	 * @param listePNJ liste des PNJ sur la map.
	 * @return boolean true si il peut se deplacer et false sinon.
	 */
	public boolean seDeplacer(int mx, int my, ArrayList<PNJ> listePNJ){
		boolean test=true;
		// si il a un PNJ sur le chemin le joueur ne bouge pas
		for(int i=0; i<listePNJ.size(); i++){
			if((listePNJ.get(i).x==x+mx)&&(listePNJ.get(i).y==y+my)){
				test=false;
				return false;
			}
		}
		if(test==true) return testerDeplacement(x+mx, y+my, this.monde.getElement(x+mx, y+my));
		else return false;
	}
}

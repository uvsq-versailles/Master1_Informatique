package fr.uvsq.inf103.rogue_like.sauvegarde;

import java.io.FileInputStream; 
import java.io.DataInputStream; 
import java.io.BufferedInputStream; 
import java.util.ArrayList;

import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.creature.*;
import fr.uvsq.inf103.rogue_like.exception.*;

/**
 * Classe Chargement permettant de charger une partie sauvegardee.
 */
public class Chargement {

	/**
	 * Map de la partie sauvegardee.
	 */
	private Monde monde;

	/**
	 * Niveau de la partie sauvegardee.
	 */
	private int niveau;

	/**
	 * Joueur de la partie sauvegardee.
	 */
	private Joueur joueur;

	/**
	 * Difficulte de la partie sauvegardee.
	 */
	private Difficulte difficulte;

	/**
	 * Liste des PNJ de la partie sauvegardee.
	 */
	private ArrayList <PNJ> listePNJ;

	/**
	 * Constructeur de Chargement qui va lire fileName
	 * @param fileName nom du fichier a lire qui contient les donnees
	 * de la partie sauvegardee.
	 */
	public Chargement(String fileName){
		try{
			this.lireSauvegarde(fileName);
		}
		catch(SpawnException e){
			throw new SpawnException();
		}
		catch(PorteException e){
			throw new PorteException();
		}
		catch(Exception e){
			throw new ChargementException();
		}
	}

	/**
	 * Constructeur de Chargement.
	 */
	public Chargement() {
		this.lireSauvegarde();
	}

	/**
	 * Accesseur de la difficulte de la partie sauvegardee.
	 * @return Difficulte de la partie.
	 */
	public Difficulte getDifficulte(){
		return this.difficulte;
	}

	/**
	 * Accesseur du niveau de la partie sauvegardee.
	 * @return Niveau de la partie.
	 */
	public int getNiveau(){
		return this.niveau;
	}

	/**
	 * Accesseur de la map de la partie sauvegardee.
	 * @return Monde de la partie.
	 */
	public Monde getMonde(){
		return this.monde;
	}

	/**
	 * Accesseur du joueur de la partie sauvegardee.
	 * @return Joueur de la partie.
	 */
	public Joueur getJoueur(){
		return this.joueur;
	}

	/**
	 * Accesseur de la liste de PNJ de la partie sauvegardee.
	 * @return Liste de PNJ de la partie.
	 */
	public ArrayList <PNJ> getListePNJ(){
		return this.listePNJ;
	}

	/**
	 * Methode qui permet de lire la derniere partie sauvegardee qui se
	 * trouve dans "save/save.txt".
	 */
	public void lireSauvegarde(){
		lireSauvegarde("save/save.txt");

	}

	/**
	 * Methode qui permet de lire la partie sauvegardee dans fileName.
	 * @param fileName nom du fichier contenant la partie sauvegardee.
	 */
	public void lireSauvegarde(String fileName){
		try{
			Element[][] element;
			int nbPNJ, vieJoueur, argentJoueur, joueurX, joueurY, i;
			int compteurPorte, compteurArgent, compteurArme;
			compteurPorte=compteurArgent=compteurArme=0;
			char c;
			boolean clef; Arme armeJoueur;
			element=new Element[90][32];
			FileInputStream file=new FileInputStream(fileName);
			DataInputStream out=new DataInputStream(new BufferedInputStream(file));

			for(i=0;i<90;i++) {
				for(int j=0;j<32;j++) {
					c=out.readChar();
					for(Element e : Element.values()) {
						if(e.getCaractere()==c){
							element[i][j]=e.valueOf(e.name());
							if(element[i][j]==Element.DOOR) compteurPorte++;
						}

					}
				}
			}
			if(compteurPorte!=1) throw new PorteException();
			this.monde=new Monde(element);

			i=out.readInt(); this.niveau=i; //level initialise
			vieJoueur=out.readInt();
			argentJoueur=out.readInt();
			i=out.readInt(); armeJoueur=Arme.values()[i];
			i=out.readInt();
			if(i==0) clef=false; else clef=true;
			joueurX=out.readInt();
			joueurY=out.readInt();
			if(element[joueurX][joueurY]==Element.WALL) throw new SpawnException(); // si le joueur spawn sur un mur
			if(element[joueurX][joueurY]==Element.DOOR) throw new SpawnException(); // si le joueur spawn sur une porte
			this.joueur=new Joueur(this.monde, armeJoueur, vieJoueur, argentJoueur, clef, joueurX, joueurY);
			i=out.readInt(); this.difficulte=Difficulte.values()[i]; //difficulte initialise
			nbPNJ=out.readInt();
			this.listePNJ=new ArrayList<PNJ>();
			int classe, pnjX, pnjY, pnjVie, pnjVolonteArgent, pnjClef;
			for(int k=0;k<nbPNJ;k++){
				classe=out.readInt();
				pnjX=out.readInt();
				pnjY=out.readInt();

				if(element[pnjX][pnjY]==Element.WALL) throw new SpawnException(); // si le pnj spawn sur un mur
				if(element[pnjX][pnjY]==Element.DOOR) throw new SpawnException(); // si le pnj spawn sur une porte
				if((joueurX==pnjX)&&(joueurY==pnjY)) throw new SpawnException(); // si le pnj spawn sur le joueur
				for(int l=0; l<k; l++){ // si le pnj spawn sur un autre pnj
					if((this.listePNJ.get(l).x==pnjX)&&(this.listePNJ.get(l).y==pnjY)) throw new SpawnException();
				}

				pnjVie=out.readInt();
				pnjVolonteArgent=out.readInt();
				pnjClef=out.readInt();
				if(pnjClef==0) clef=false; else clef=true;

				this.listePNJ.add(new PNJ(this.monde, EnumPNJ.values()[classe], pnjX, pnjY, pnjVie, pnjVolonteArgent, clef));

			}
			file.close();
		}
		catch(SpawnException e){
			throw new SpawnException();
		}
		catch(PorteException e){
			throw new PorteException();
		}
		catch(Exception e){
			throw new ChargementException();
		}
	}
}


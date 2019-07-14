package fr.uvsq.inf103.rogue_like.sauvegarde;

import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream; 
import java.util.ArrayList;

import fr.uvsq.inf103.rogue_like.screen.*;
import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.creature.*;
import fr.uvsq.inf103.rogue_like.exception.*;

/**
 * Classe Sauvegarde permettant de sauvegarder la partie en cours de Rogue Like.
 */
public class Sauvegarde {

	/**
	 * Playscreen du jeu en cours.
	 */
	private PlayScreen playscreen;

	/**
	 * Constructeur de Sauvegarde mettant playscreen a null.
	 */
	public Sauvegarde(){
		this.playscreen=null;
	}

	/**
	 * Constructeur de Sauvegarde initialisant les donnees pour
	 * la bonne sauvegarde de la partie.
	 * @param playscreen de la partie en cours.
	 */
	public Sauvegarde(PlayScreen playscreen) {
		this.playscreen=playscreen;
		sauvegarderPartie("save/save.txt");
	}

	/**
	 * Methode pour sauvegarder la partie en cours du jeu.
	 * @param fileName nom du fichier qui va contenir la partie sauvegardee.
	 */
	public void sauvegarderPartie(String fileName){
		sauvegarderPartie(fileName, playscreen.getDifficulte(), playscreen.getMonde(), playscreen.getNiveau(),
				playscreen.getJoueur(), playscreen.getListePNJ());
	}

	/**
	 * Methode pour sauvegarder la partie en cours avec tous les parametres du jeu.
	 * @param fileName nom du fichier ou sera la sauvegarde.
	 * @param difficulte de la partie.
	 * @param monde map de la partie.
	 * @param niveau de la partie.
	 * @param joueur de la partie.
	 * @param listePNJ liste des PNJ presents sur la map.
	 */
	public void sauvegarderPartie(String fileName, Difficulte difficulte, Monde monde, int niveau, Joueur joueur,
								 ArrayList <PNJ> listePNJ){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			DataOutputStream dataOut = new DataOutputStream(fileOutputStream);
			//ecriture du monde
			int i;
			for(i=0;i<90;i++) {
				for(int j=0;j<32;j++) {
					dataOut.writeChar(monde.getElement(i,j).getCaractere());
				}
			}

		/*
			[n°niveau du jeu] [vie joueur] [argent joueur] [n°arme joueur] [0 ou 1 si joueur a la clef]
			[joueur X] [joueur Y] [n°difficulte]
			[nb PNJ]
			[n°classe PNJ] [PNJ X] [PNJ Y] [PNJ vie] [PNJ volonteArgent] [0 ou 1 si PNJ a la clef]
		 */
			boolean testClef; PNJ pnj;
			dataOut.writeInt(niveau);
			dataOut.writeInt(joueur.getVie());
			dataOut.writeInt(joueur.getArgent());
			dataOut.writeInt(joueur.getArme().ordinal());
			testClef=joueur.getClef();
			if(testClef==true)dataOut.writeInt(1);
			else dataOut.writeInt(0);
			dataOut.writeInt(joueur.x);
			dataOut.writeInt(joueur.y);
			dataOut.writeInt(difficulte.ordinal());
			dataOut.writeInt(listePNJ.size());

			for(i=0;i<listePNJ.size();i++){
				pnj=listePNJ.get(i);
				dataOut.writeInt(pnj.getClasse().ordinal());
				dataOut.writeInt(pnj.x);
				dataOut.writeInt(pnj.y);
				dataOut.writeInt(pnj.getVie());
				dataOut.writeInt(pnj.getVolonteArgent());
				testClef=pnj.testPossedeClef();
				if(testClef==true)dataOut.writeInt(1);
				else dataOut.writeInt(0);
			}

			fileOutputStream.close();
		}
		catch(Exception e){
			throw new SauvegardeException();
		}
	}
}

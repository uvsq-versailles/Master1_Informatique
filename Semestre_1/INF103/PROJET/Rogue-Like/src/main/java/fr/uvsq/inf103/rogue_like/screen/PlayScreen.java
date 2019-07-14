package fr.uvsq.inf103.rogue_like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import java.util.ArrayList;

import fr.uvsq.inf103.rogue_like.sauvegarde.*;
import fr.uvsq.inf103.rogue_like.world.*;
import fr.uvsq.inf103.rogue_like.creature.*;
import fr.uvsq.inf103.rogue_like.exception.*;

/**
 * Classe PlayScreen qui s'affichera quand l'utilisateur sera en train de jouer.
 */
public class PlayScreen implements Screen {

	/**
	 * Map du monde Rogue Like.
	 */
	private Monde monde;
	
	/**
	 * Difficulte du jeu choisie par le joueur.
	 */
	private Difficulte difficulte;
	
	/**
	 * PJ representant le joueur en train de jouer.
	 */
	private Joueur joueur;
	
	/**
	 * Liste de PNJ (pacifiques ou non).
	 */
	private ArrayList <PNJ> listePNJ;
	
	/**
	 * Level de la partie.
	 */
	private int niveau;
	
	/**
	 * Longueur de la fenetre de jeu.
	 */
	private int screenWidth;
	
	/**
	 * Largeur de la fenetre de jeu.
	 */
	private int screenHeight;

	/**
	 * Message a afficher sur l'ecran de jeu de facon temporaire.
	 */
	private String messageTemporaire;

	/**
	 * Accesseur du niveau de la partie.
	 * @return niveau de la partie.
	 */
	public int getNiveau() {
		return this.niveau;
	}

	/**
	 * Accesseur du joueur de la partie.
	 * @return joueur de la partie.
	 */
	public Joueur getJoueur() {
		return this.joueur;
	}

	/**
	 * Accesseur de la liste de PNJ de la partie.
	 * @return liste de PNJ de la partie.
	 */
	public ArrayList <PNJ> getListePNJ(){
		return this.listePNJ;
	}

	/**
	 * Accesseur de la difficulte de la partie.
	 * @return difficulte de la partie.
	 */
	public Difficulte getDifficulte(){
		return this.difficulte;
	}

	/**
	 * Acesseur de la map de la partie.
	 * @return monde de la partie.
	 */
	public Monde getMonde() {
		return monde;
	}
	
	/**
	 * Constructeur de PlayScreen qui permet de generer la map, le joueur et les PNJ.
	 * @param niveau du jeu.
	 * @param arme du joueur.
	 * @param difficulte de la partie.
	 * @param vie du joueur.
	 * @param argent du joueur.
	 */
	public PlayScreen(int niveau, Arme arme, Difficulte difficulte, int vie, int argent){
		screenWidth = 80;
		screenHeight = 21;
		this.niveau=niveau;
		this.difficulte=difficulte;

		creerMonde();
		joueur=new Joueur(monde,arme, vie, argent);

		creerPNJ(monde, difficulte);
	}

	/**
	 * Constructeur de Playscreen a l'aide de la lecture du fichier de sauvegarde.
	 * @param sauvegarde de la partie precedemment enregistree.
	 */
	public PlayScreen(Chargement sauvegarde){
		screenWidth = 80;
		screenHeight = 21;
		this.difficulte=sauvegarde.getDifficulte();
		this.niveau=sauvegarde.getNiveau();
		this.monde=sauvegarde.getMonde();
		this.joueur=sauvegarde.getJoueur();
		this.listePNJ=new ArrayList<PNJ>();

		PNJ pnj; int i;
		for(i=0;i<sauvegarde.getListePNJ().size();i++){
			pnj=sauvegarde.getListePNJ().get(i);
			this.listePNJ.add(new PNJ(this.monde, pnj.getClasse(), pnj.x, pnj.y, pnj.getVie(), pnj.getVolonteArgent(), pnj.testPossedeClef()));
		}
	}

	/**
	 * Methode permettant de savoir si le PNJ a faire spawner est spawnable en (x,y).
	 * @param x coordonnees en abscisse du futur PNJ.
	 * @param y coordonnees en ordonnee du futur PNJ.
	 * @param rang dans la liste du PNJ a faire spawner.
	 * @return true si le PNJ peut etre en (x,y) et false sinon.
	 */
	private boolean testerSpawnPossible(int x, int y, int rang){
		if((this.joueur.x==x)&&(this.joueur.y==y)) return false;
		else{
			for(int i=0; i<rang; i++){
				if((this.listePNJ.get(i).x==x)&&(this.listePNJ.get(i).y==y)) return false;
			}
		}
		return true;
	}

	/**
	 * Methode qui permet de faire spawner un certain nombre de PNJ sur la map.
	 */
	private void spawnPNJ(){
		for(int i=0; i<this.listePNJ.size(); i++){
			int x; int y;
			do {
				x = (int)(Math.random() * monde.getLongueur());
				y = (int)(Math.random() * monde.getLargeur());
			}
			while ((!monde.getElement(x,y).testerSol())||(!testerSpawnPossible(x,y,i)));
			this.listePNJ.get(i).x = x;
			this.listePNJ.get(i).y = y;
		}
	}

	/**
	 * Methode qui permet de creer un certain nombre de PNJ en fonction de la difficulte du jeu.
	 * @param world monde sur lequel il faut faire spawner les PNJ.
	 * @param difficulte du jeu.
	 */
	private void creerPNJ(Monde world, Difficulte difficulte){
		this.listePNJ=new ArrayList<PNJ>();
		int nb_pnj_agressifs;
		if(difficulte==Difficulte.FACILE) nb_pnj_agressifs=5;
		else if(difficulte==Difficulte.INTERMEDIAIRE) nb_pnj_agressifs=7;
		else if(difficulte==Difficulte.DIFFICILE) nb_pnj_agressifs=10;
		else nb_pnj_agressifs=20;

		// ajout des PNJ agressifs
		EnumPNJ pnj_cree; int type_pnj; PNJ pnj;
		for(int i=0;i<nb_pnj_agressifs;i++){
			type_pnj=(int)(Math.random() * EnumPNJ.NB_ENUM_PNJ.ordinal()); // ordinal recupere le nombre d'enum
			if(type_pnj==0) type_pnj=1;
			pnj_cree=EnumPNJ.values()[type_pnj];
			this.listePNJ.add(new PNJ(world, pnj_cree));
		}
		// ajout du PNJ villageois necessaire au niveau
		boolean testAjoutVillageois=this.listePNJ.add(new PNJ(world,EnumPNJ.VILLAGEOIS));
		if(testAjoutVillageois==false) throw new VillageoisException();
		spawnPNJ();
	}

	/**
	 * Methode privee permettant de generer le monde.
	 */
	private void creerMonde(){
		monde = new MondeBuilder(90, 32)
					.fabriquerElements()
					.construire();
	}
	
	/**
	 * Methode de point de vue de la camera sur l'axe de la longueur.
	 * @return position de la camera en longueur.
	 */
	public int getScrollX() { return Math.max(0, Math.min(joueur.x - screenWidth / 2, monde.getLongueur() - screenWidth)); }
	
	/**
	 * Methode de point de vue de la camera sur l'axe de la largeur.
	 * @return position de la camera en largeur.
	 */
	public int getScrollY() { return Math.max(0, Math.min(joueur.y - screenHeight / 2, monde.getLargeur() - screenHeight)); }
	
	/**
     * Methode qui affiche les interactions possibles avec l'utilisateur.
     * @param terminal represente l'ecran du jeu.
     */
	//@Override
	public void displayOutput(AsciiPanel terminal) {
		
		int left = getScrollX();
		int top = getScrollY();

		afficherElementsCreatures(terminal, left, top);
		
		terminal.write(joueur.getCaractere(), joueur.x - left, joueur.y - top, joueur.getCouleur());

		terminal.write((char)3, 1, 0, AsciiPanel.brightRed);
		terminal.write(""+joueur.getVie()+"/10", 3, 0);
		terminal.write(joueur.getArme().getNom(), 0, 21);
		terminal.write("$" , 0, 22, AsciiPanel.brightGreen);
		terminal.write(""+this.joueur.getArgent() , 2, 22);
		if(joueur.getClef()==true) terminal.write((char)213, 0, 23, AsciiPanel.brightYellow);
		terminal.writeCenter("Level "+this.niveau, 21, AsciiPanel.blue);
		if(this.messageTemporaire!=null) terminal.writeCenter(this.messageTemporaire, 22, AsciiPanel.white);
		this.messageTemporaire=null;
	}

	/**
	 * Affichage des élements. 
	 * @param terminal ou les elements sont affiches;
	 * @param left longueur de la fenetre.
	 * @param top hauteur de la fenetre.
	 */
	private void afficherElementsCreatures(AsciiPanel terminal, int left, int top) {
		PNJ pnj; int xx; int yy;
		for (int x = 0; x < screenWidth; x++){
			for (int y = 0; y < screenHeight; y++){
				int wx = x + left;
				int wy = y + top;

				terminal.write(monde.getCaractere(wx, wy), x, y, monde.getCouleur(wx, wy));
				for(int ii=0;ii<this.listePNJ.size();ii++){
					pnj=this.listePNJ.get(ii);
					if(((x+left)==pnj.x)&&((y+top)==pnj.y)){
						terminal.write(pnj.getClasse().getCaractere(), x, y, pnj.getClasse().getCouleur());
					}
				}
			}
		}
	}

	/**
	 * Methode permettant de savoir si le joueur peut changer de niveau.
	 * La condition est le fait d'avoir la clef pour ouvrir la porte.
	 * @return true si il peut changer de niveau et false sinon.
	 */
	private boolean testeChangerNiveau(){
		if(joueur.getClef()){
			boolean test=false;
			if(monde.getElement(joueur.x+1,joueur.y)==Element.DOOR){
				test=true;
			}
			else if(monde.getElement(joueur.x,joueur.y+1)==Element.DOOR){
				test=true;
			}
			else if(monde.getElement(joueur.x,joueur.y-1)==Element.DOOR){
				test=true;
			}
			else if(monde.getElement(joueur.x-1,joueur.y)==Element.DOOR){
				test=true;
			}
			if(test==true){
				joueur.laisserClef();
				return true;
			}

		}
		return false;
	}

	/**
	 * Methode permettant
	 * @param listePNJ liste des PNJ a faire se deplacer.
	 * @param joueur joueur du jeu.
	 */
	private void actionPNJ(ArrayList<PNJ> listePNJ, Joueur joueur){
		//deplacement PNJ
		PNJ pnj;
		for(int i=0; i<listePNJ.size(); i++) {
			pnj = listePNJ.get(i);
			pnj.seDeplacer(joueur, listePNJ);
		}
	}

	
	/**
     * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
     * @param key touche que l'utilisateur tape sur le clavier.
     * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
     */
	//@Override
	public Screen respondToUserInput(KeyEvent key) {
		boolean action=false;
		switch (key.getKeyCode()){
			case KeyEvent.VK_LEFT: if(joueur.seDeplacer(-1, 0, listePNJ)) action=true; break;
			case KeyEvent.VK_RIGHT: if(joueur.seDeplacer( 1, 0, listePNJ)) action=true; break;
			case KeyEvent.VK_UP: if(joueur.seDeplacer( 0,-1, listePNJ)) action=true; break;
			case KeyEvent.VK_DOWN: if(joueur.seDeplacer( 0, 1, listePNJ)) action=true; break;
			case KeyEvent.VK_R: messageTemporaire=joueur.ramasserObjet(monde); break;
			case KeyEvent.VK_O:
				if(testeChangerNiveau()) return new PlayScreen(niveau+1, joueur.getArme(), this.difficulte, joueur.getVie(), joueur.getArgent());
			case KeyEvent.VK_P:
				messageTemporaire=joueur.faireEchangeVillageois(this.listePNJ);
				if(messageTemporaire!=null) action=true;
				break;
			case KeyEvent.VK_A:
				messageTemporaire=joueur.attaquerPNJ(this.listePNJ);
				if(messageTemporaire!=null) action=true;
				break;
			case KeyEvent.VK_S:
				new Sauvegarde(this);
				messageTemporaire="Partie Sauvegardee";
				break;
		}
		if(action==true) actionPNJ(this.listePNJ, joueur);
		if(joueur.getVie()==0) return new LoseScreen();

		return this;
	}
}

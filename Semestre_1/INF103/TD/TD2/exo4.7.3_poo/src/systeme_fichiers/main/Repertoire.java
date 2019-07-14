package systeme_fichiers.main;
import java.util.ArrayList;

import systeme_fichiers.exceptions.AjoutException;
import systeme_fichiers.exceptions.TailleNulleException;

/**
 * Classe Repertoire representant un dossier
 * @author Clément Caumes et Mehdi Merimi
 *
 */
public class Repertoire extends Element{
	/**
	 * Liste d'éléments qui sont possédés par le repertoire
	 */
	private ArrayList <Element> element;
	
	/**
	 * Constructeur du repertoire
	 * @param nom du repertoire
	 */
	public Repertoire(String nom) {
		super(nom);
		this.element= new ArrayList<Element>();
	}
	
	/**
	 * Accesseur de la taille du repertoire
	 */
	public int getTaille() {
		int taille=0;
		for(int i=0;i<element.size();i++)
			taille=taille+element.get(i).getTaille();
		return taille ;
	}
	
	/**
	 * Accesseur de la liste representant le contenu du repertoire
	 * @return contenu du repertoire
	 */
	public ArrayList getListe() {
		return element;
	}
	
	/**
	 * Methode qui renvoie vrai ou faux pour savoir si e est 
	 * contenu dans le repertoire.
	 * @param e element à tester pour savoir si il est contenu 
	 * dans le repertoire
	 * @return vrai ou faux
	 */
	@Override
	public boolean contient(Element e) {
		if(this.element.contains(e)) {
			return true;
		}
		else { 
			boolean boolr=false;
			for(int i=0;i<element.size();i++) {
				boolr=this.element.get(i).contient(e) | boolr;
			}
		
			return boolr;
		}
	
	}
	
	/**
	 * Methode qui ajoute e dans le repertoire
	 * @param e élément à rajouter
	 */
	public void ajouteElement(Element e) {
		
		//Un répertoire ne peut pas être ajouté à lui-même (question 4)
		if(this==e) throw new AjoutException();
		
		//Un répertoire ne peut pas être ajouté à sa descendance (question 5)
		else if(e.contient(this)==true) {
			throw new AjoutException();
		}
		
		//Un répertoire qui ne contient pas l'élément peut ajouter cet élément
		else if(contient(e)==false) {
			this.element.add(e);
		}
		else{
			throw new AjoutException(); 
		}
	}
}

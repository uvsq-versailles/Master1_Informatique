/*
   Composite permet de créer des structures hiérarchiques pour des relations
   tout-partie. 
 */

interface Element {
	public void print();
}

// peut etre une classe abstraite
public abstract class Element{
	protected String nom;
	protected int taille;
	
	public Element(String nom, int taille) {
		this.nom=nom;
		this.taille=taille;
	}
	
	public abstract void print();
}


////////////////////////////////////

class Fichier implements Element {
	
	public void print(){
		System.out.Println("je suis un fichier");
	}
}

////////////////////////////////////

class Dossier implements Element {
	
	private List<Element> contenu = new ArrayList <Element>();
	
	public void print(){
		System.out.Println("je suis un dossier");
		for (Element e : contenu){
			e.print();
		}
	}
	
	public void add(Element e){
		contenu.add(e);
	}
}

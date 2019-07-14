/*
	Abstract Factory method permet la création d’objets sans préciser explicitement la classe à utiliser. Les
	objets sont créés en utilisant une méthode de fabrication redéfinie dans des sous-classes.
*/

public class Client {

	public static void main(String[] args) {
		ComplexeIndustriel usineCarottePomme = new UsineCarottePomme();
		ComplexeIndustriel usineHaricotPoire = new UsineHaricotPoire();

		Legume legume = null;
		Fruit fruit = null;
		System.out.println("Utilisation de la premiere fabrique");
		legume = usineCarottePomme.getLegume(); 
		fruit = usineCarottePomme.getFruit(); 
		legume.afficheLegume(); // "Je suis une Carotte"
		fruit.afficheFruit(); // "Je suis une Pomme"
		
		System.out.println("Utilisation de la seconde fabrique");
		legume = usineHaricotPoire.getLegume();
		fruit = usineHaricotPoire.getFruit(); 
		legume.afficheLegume(); // "Je suis un Haricot"
		fruit.afficheFruit(); // "Je suis une Poire"
	}
}

////////////////////////////////////

public interface ComplexeIndustriel {

	public Legume getLegume();
	public Fruit getFruit();
}

////////////////////////////////////////////////////////

public class UsineCarottePomme implements ComplexeIndustriel {

	public Legume getLegume() {
		return new Carotte();
	}

	public Fruit getFruit() {
		return new Pomme();
	}
}

//////////////////////////////////////////////////////////

public class UsineHaricotPoire implements ComplexeIndustriel {

	public Legume getLegume() {
		return new Haricot();
	}

	public Fruit getFruit() {
		return new Poire();
	}
}

///////////////////////////////////

public abstract class Legume {
	public abstract void afficheLegume();
}

//////////////////////////////////////////

public class Carotte extends Legume {

	public void afficheLegume() {
		System.out.println("Je suis une carotte");
	}
}

//////////////////////////////////////////

public class Haricot extends Legume {

	public void afficheLegume() {
		System.out.println("Je suis un Haricot");
	}
}

//////////////////////////////////

public abstract class Fruit {
	public abstract void afficheFruit();
}

////////////////////////////////////////////

public class Pomme extends Fruit {

	public void afficheFruit() {
		System.out.println("Je suis une Pomme");
	}
}

/////////////////////////////////////////////

public class Poire extends Fruit {

	public void afficheFruit() {
		System.out.println("Je suis une Poire");
	}
}

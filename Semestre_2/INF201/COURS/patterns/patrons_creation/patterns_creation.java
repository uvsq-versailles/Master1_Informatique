
/* ///////////////////////////////////////////////////////////////////////
	SINGLETON garantit qu'une seule instance d'une classe est créée.
*/

// SOLUTION AVEC CLASSE

class Singleton {
	private static Singleton INSTANCE; // instance unique

	private Singleton(){ // constructeur privé
		// ...
	}

	public static Singleton getInstance(){
		if(INSTANCE == null) { // crée l'instance au premier appel
			INSTANCE = new Singleton();
		}
		return INSTANCE;
	}

	public void run(String[] args){
		// ...
	}

	public static void main(String[] args){
		getInstance().run(args);
	}
}

// SOLUTION AVEC ENUMERATION

enum Singleton {
	ENVIRONNEMENT;

	public void run(String[] args){
		// ...
	}

	public static void main(String[] args){
		ENVIRONNEMENT.run(args);
	}
}

/* ///////////////////////////////////////////////////////////////////////
	Builder permet de créer une classe avec un grand nombre d'attributs et 
	qui doit gérer un grand nombre de constructeurs. 
*/

public class StreetMap{
	private final Point origin;
	private final Point destination;

	private final Color waterColor;
	private final Color landColor;
	private final Color highTrafficColor;
	private final Color mediumTrafficColor;
	private final Color lowTrafficColor;

	public static class Builder{
		//required parameters
		private final Point origin;
		private final Point destination;
		// optional parameters initialize with default values
		private Color waterColor = Color.BLUE;
		private Color landColor = Color.RED;
		private Color highTrafficColor = Color.YELLOW;
		private Color mediumTrafficColor = Color.PURPLE;
		private Color lowTrafficColor = Color.ORANGE;

		public Builder(Point origin, Point destination){
			this.origin=origin;
			this.detination=destination;
		}

		//faire la meme chose pour chaque parametre
		public Builder waterColor(Color color){
			this.waterColor=color;
			return this;
		}

		public StreetMap build(){
			return new StreetMap(this);
		}
	}

	private StreetMap(Builder builder){
		//required parameters
		origin = builder.origin;
		destination = builder.destination;

		//optional parameters
		waterColor = builder.waterColor;
		landColor = builder.landColor;
		highTrafficColor = builder.highTrafficColor;
		mediumTrafficColor = builder.mediumTrafficColor;
		lowTrafficColor = builder.lowTrafficColor;
	}
}

public static void main(String args[]){
	StreetMap s = new StreetMap	
		.Builder(new Point(1,2), new Point(2,3))
		.landColor(Color.GREY)
		.waterColor(Color.BLACK)
		.build();
}

/* ///////////////////////////////////////////////////////////////////////
	Factory method permet la création d’objets sans préciser explicitement la classe à utiliser. Les
	objets sont créés en utilisant une méthode de fabrication redéfinie dans des sous-classes.
*/

// Exemple 1 :

public class Client {

	public static void main(String[] args) {

		ComplexeIndustriel usinePomme = new UsinePomme();
		ComplexeIndustriel usinePoire = new UsinePoire();	
		
		Fruit fruit1 = null;
		System.out.println("Utilisation de la premiere fabrique");
		fruit1 = usinePomme.getFruit(); 
		fruit1.afficheFruit(); // "Je suis une Pomme"

		Fruit fruit2 = null;
		System.out.println("Utilisation de la seconde fabrique");
		fruit2 = usinePoire.getFruit(); 
		fruit2.afficheFruit(); // "Je suis une Poire"
	}	
}

public abstract class ComplexeIndustriel {

	public Fruit getFruit() {
		return createFruit();
	}

	protected abstract Fruit createFruit();
}

public class UsinePomme extends ComplexeIndustriel {

	@Override
	protected Fruit createFruit() {
		return new Pomme();
	}
}

public class UsinePoire extends ComplexeIndustriel {
	@Override
	protected Fruit createFruit() {
		return new Poire();
	}
}

public abstract class Fruit {
	public abstract void afficheFruit();
}

public class Pomme extends Fruit {
	
	@Override
	public void afficheFruit() {
		System.out.println("Je suis une Pomme");
	}
}

public class Poire extends Fruit {
	
	@Override
	public void afficheFruit() {
		System.out.println("Je suis une Poire");
	}
}

/* ///////////////////////////////////////////////////////////////////////
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

public interface ComplexeIndustriel {

	public Legume getLegume();
	public Fruit getFruit();
}

public class UsineCarottePomme implements ComplexeIndustriel {

	public Legume getLegume() {
		return new Carotte();
	}

	public Fruit getFruit() {
		return new Pomme();
	}
}

public class UsineHaricotPoire implements ComplexeIndustriel {

	public Legume getLegume() {
		return new Haricot();
	}

	public Fruit getFruit() {
		return new Poire();
	}
}

public abstract class Legume {
	public abstract void afficheLegume();
}

public class Carotte extends Legume {

	public void afficheLegume() {
		System.out.println("Je suis une carotte");
	}
}

public class Haricot extends Legume {

	public void afficheLegume() {
		System.out.println("Je suis un Haricot");
	}
}

public abstract class Fruit {
	public abstract void afficheFruit();
}

public class Pomme extends Fruit {

	public void afficheFruit() {
		System.out.println("Je suis une Pomme");
	}
}

public class Poire extends Fruit {

	public void afficheFruit() {
		System.out.println("Je suis une Poire");
	}
}

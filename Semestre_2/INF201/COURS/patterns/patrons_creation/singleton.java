/*
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

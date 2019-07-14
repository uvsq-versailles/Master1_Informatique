package systeme_fichiers.main;

/**
 * Main qui réalise un test ordinaire
 * @author Clément Caumes et Mehdi Merimi
 *
 */
public class Main {
	
	/**
	 * Test ordinaire 
	 * @param args paramètre à mettre en arguments
	 */
	public static void main(String[] args) {
		
		/* CAS ILLUSTRANT LE MAIN
		 	r1
		 	/\
		   /  \
		  /    \
		 r2     f1
		 /\
        /  \
	   /    \
	  r3    f2
		 
	  */
		
		Repertoire r1=new Repertoire("repertoire 1");
		Fichier f1=new Fichier("fichier 1",10);
		r1.ajouteElement(f1);
		Repertoire r2=new Repertoire("repertoire 2");
		Fichier f2=new Fichier("fichier 2",14);
		Repertoire r3=new Repertoire("repertoire 3");
		r2.ajouteElement(f2);
		r1.ajouteElement(r2);
		r2.ajouteElement(r3);
	
	}

}

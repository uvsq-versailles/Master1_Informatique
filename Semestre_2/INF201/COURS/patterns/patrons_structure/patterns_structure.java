/* ////////////////////////////////////////////////////////////////
   Composite permet de créer des structures hiérarchiques pour des relations
   tout-partie. 
 */

// peut etre une classe abstraite
interface Element {
	public void print();
}

public abstract class Element{
	protected String nom;
	protected int taille;
	
	public Element(String nom, int taille) {
		this.nom=nom;
		this.taille=taille;
	}
	
	public abstract void print();
}

class Fichier implements Element {
	
	public void print(){
		System.out.Println("je suis un fichier");
	}
}

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

/* ////////////////////////////////////////////////////////////////
  Adapteur permet à une classe d'être utilisée avec une interface qui n'est 
  pas la sienne. Il permet d'utiliser des interfaces incompatibles. 
 */

/**
 * Définit une interface qui est identifiée 
 * comme standard dans la partie cliente. 
 */
public interface Standard {

    /**
     * L'opération doit multiplier les deux nombres,
     * puis afficher le résultat de l'opération
     */
    public void operation(int pNombre1, int pNombre2);
}

/**
 * Implémente l'interface "Standard".
 */
public class ImplStandard implements Standard {

    public void operation(int pNombre1, int pNombre2) {
        System.out.println("Standard : Le nombre est : " + (pNombre1 * pNombre2));
    }
}

/**
 * Fournit les fonctionnalités définies dans l'interface "Standard",
 * mais ne respecte pas l'interface.
 */
public class ImplAdapte {

    public int operationAdapte1(int pNombre1, int pNombre2) {
        return pNombre1 * pNombre2;
    }
    
    /**
     * Apporte la fonctionalité définie dans l'interface,
     * mais la méthode n'a pas le bon nom 
     * et n'accepte pas le même paramètre.
     */
    public void operationAdapte2(int pNombre) {
        System.out.println("Adapte : Le nombre est : " + pNombre);
    }
}

/**
 * Adapte l'implémentation non standard avec l'héritage.
 */
public class Adaptateur extends ImplAdapte implements Standard {

    /**
     * Appelle les méthodes non standard 
     * depuis une méthode respectant l'interface. 
     * 1°) Appel de la méthode réalisant la multiplication
     * 2°) Appel de la méthode d'affichage du résultat
     * La classe adaptée est héritée, donc on appelle directement les méthodes
     */
    public void operation(int pNombre1, int pNombre2) {
        int lNombre = operationAdapte1(pNombre1, pNombre2);
        operationAdapte2(lNombre);
    }
}

// OU BIEN
/**
 * Adapte l'implémentation non standard avec la composition.
 */
public class Adaptateur implements Standard {

    private ImplAdapte adapte = new ImplAdapte();
    
    /**
     * Appelle les méthodes non standard
     * depuis une méthode respectant l'interface. 
     * 1°) Appel de la méthode réalisant la multiplication
     * 2°) Appel de la méthode d'affichage du résultat
     * La classe adaptée compose l'adaptation, 
     * donc on appelle les méthodes de "ImplAdapte".
     */
    public void operation(int pNombre1, int pNombre2) {
        int lNombre = adapte.operationAdapte1(pNombre1, pNombre2);
        adapte.operationAdapte2(lNombre);
    }
}

public class Main {

    public static void main(String[] args) {
        // Création d'un adaptateur
        final Standard lImplAdapte = new Adaptateur();
        // Création d'une implémentation standard
        final Standard lImplStandard = new ImplStandard();

        // Appel de la même méthode sur chaque instance
        lImplAdapte.operation(2, 4);
        lImplStandard.operation(2, 4);
        
        // Affichage : 
        // Adapte : Le nombre est : 8
        // Standard : Le nombre est : 8
    }
}

/* ////////////////////////////////////////////////////////////////
 * Decorateur permet d'implémenter une classe puis de lui rajouter des 
 * fonctionnalités. 
 */

// Déclarations
public abstract class Voiture {
	
	private String nom;
	private String marque;

	abstract int getPrix();
	abstract int getPoids();
}

class DS extends Voiture{

	public DS() {
		this.nom = "DS"; this.marque = "Citroën";
	}	
	int getPrix() {return 30000;}	
	int getPoids() {return 1500;}	
}

// Décorateurs
abstract class VoitureAvecOption extends Voiture{
	Voiture voiture;
}

class VoitureAvecToitOuvrant extends VoitureAvecOption{
	
	int getPrix() {return voiture.getPrix() + 10000;}
	int getPoids() {return voiture.getPoids() + 15;}	
}

//On garde le nom du pattern Decorator pour savoir qu'on wrap un objet 
class DSAvecToitOuvrantDecorator extends VoitureAvecToitOuvrant{
	public DSAvecToitOuvrantDecorator(DS ds) {
		this.voiture = ds;
	}
}

public class Main {
	// Implémentation
	public static void main(String[] args) {
		Voiture ds = new DS();
		Voiture dsOption = new DSAvecToitOuvrantDecorator((DS) ds);
	}
}

/* ////////////////////////////////////////////////////////////////
 * Facade permet d'utiliser les fonctionnalités de plusieurs classes à 
 * partir d'une seule (Facade) 
 */

/**
 * Classe implémentant diverses fonctionnalités.
 */
public class ClasseA {

    public void operation1() {
        System.out.println("Methode operation1() de la classe ClasseA");
    }
    
    public void operation2() {
        System.out.println("Methode operation2() de la classe ClasseA");
    }
}

/**
 * Classe implémentant d'autres fonctionnalités.
 */
public class ClasseB {

    public void operation3() {
        System.out.println("Methode operation3() de la classe ClasseB");
    }
    
    public void operation4() {
        System.out.println("Methode operation4() de la classe ClasseB");
    }
}

/**
 * Présente certaines fonctionnalités.
 * Dans ce cas, ne présente que la méthode "operation2()" de "ClasseA"
 * et la méthode "operation41()" utilisant "operation4()" de "ClasseB" 
 * et "operation1()" de "ClasseA".
 */
public class Facade {

    private ClasseA classeA = new ClasseA();
    private ClasseB classeB = new ClasseB(); 

    /**
     * La méthode operation2() appelle simplement 
     * la même méthode de ClasseA
     */
    public void operation2() {
        System.out.println("--> Méthode operation2() de la classe Facade : ");
        classeA.operation2();
    }
    
    /**
     * La méthode operation41() appelle  
     * operation4() de ClasseB 
     * et operation1() de ClasseA
     */
    public void operation41() {
        System.out.println("--> Méthode operation41() de la classe Facade : ");
        classeB.operation4();
        classeA.operation1();
    }
}

public class FacadePatternMain {

    public static void main(String[] args) {
        // Création de l'objet "Facade" puis appel des méthodes
        Facade lFacade = new Facade();        
        lFacade.operation2();
        lFacade.operation41();
    }
}

/* ////////////////////////////////////////////////////////////////
 * BRIDGE
 */

/**
 * Définit l'interface de l'implémentation.
 * L'implémentation fournit deux méthodes
 */
public interface Implementation {

    public void operationImpl1(String pMessage);
    public void operationImpl2(Integer pNombre);
}

/**
 * Sous-classe concrète de l'implémentation
 */
public class ImplementationA implements Implementation {

    public void operationImpl1(String pMessage) {
        System.out.println("operationImpl1 de ImplementationA : " + pMessage);
    }
    
    public void operationImpl2(Integer pNombre) {
        System.out.println("operationImpl2 de ImplementationA : " + pNombre);
    }
}

/**
 * Sous-classe concrète de l'implémentation
 */
public class ImplementationB implements Implementation {

    public void operationImpl1(String pMessage) {
        System.out.println("operationImpl1 de ImplementationB : " + pMessage);
    }
    
    public void operationImpl2(Integer pNombre) {
        System.out.println("operationImpl2 de ImplementationB : " + pNombre);
    }
}

/**
 * Définit l'interface de l'abstraction
 */
public abstract class Abstraction {

    // Référence vers l'implémentation
    private Implementation implementation;
    
    protected Abstraction(Implementation pImplementation) {
        implementation = pImplementation;
    }

    public abstract void operation(); 

    /**
     * Lien vers la méthode operationImpl1() de l'implémentation
     * @param pMessage
     */
    protected void operationImpl1(String pMessage) {
        implementation.operationImpl1(pMessage);
    }
    
    /**
     * Lien vers la méthode operationImpl2() de l'implémentation
     * @param pMessage
     */
    protected void operationImpl2(Integer pNombre) {
        implementation.operationImpl2(pNombre);
    }
}

/**
 * Sous-classe concrète de l'abstraction
 */
public class AbstractionA extends Abstraction {

    public AbstractionA(Implementation pImplementation) {
        super(pImplementation);
    }
    
    public void operation() {
        System.out.println("--> Méthode operation() de AbstractionA");
        operationImpl1("A"); operationImpl2(1); operationImpl1("B");
    }
}

/**
 * Sous-classe concrète de l'abstraction
 */
public class AbstractionB extends Abstraction {

    public AbstractionB(Implementation pImplementation) {
        super(pImplementation);
    }
    
    public void operation() {
        System.out.println("--> Méthode operation() de AbstractionB");
        operationImpl2(9); operationImpl2(8); operationImpl1("Z");
    }
}

public class BridgePatternMain {

    public static void main(String[] args) {
        // Création des implémentations
        Implementation lImplementationA = new ImplementationA();
        Implementation lImplementationB = new ImplementationB();
        
        // Création des abstractions
        Abstraction lAbstractionAA = new AbstractionA(lImplementationA);
        Abstraction lAbstractionAB = new AbstractionA(lImplementationB);
        Abstraction lAbstractionBA = new AbstractionB(lImplementationA);
        Abstraction lAbstractionBB = new AbstractionB(lImplementationB);
        
        // Appels des méthodes des abstractions
        lAbstractionAA.operation(); lAbstractionAB.operation();
        lAbstractionBA.operation(); lAbstractionBB.operation();
    }
}

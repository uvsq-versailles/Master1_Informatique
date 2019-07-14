/**
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

////////////////////////////////

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

//////////////////////////////

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

/////////////////////////////////////

public class FacadePatternMain {

    public static void main(String[] args) {
        // Création de l'objet "Facade" puis appel des méthodes
        Facade lFacade = new Facade();        
        lFacade.operation2();
        lFacade.operation41();
        
        // Affichage :
        // --> Méthode operation2() de la classe Facade : 
        // Methode operation2() de la classe ClasseA
        // --> Méthode operation41() de la classe Facade : 
        // Methode operation4() de la classe ClasseB
        // Methode operation1() de la classe ClasseA
    }
}

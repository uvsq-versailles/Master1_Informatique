/**
 * Définit l'interface de l'implémentation.
 * L'implémentation fournit deux méthodes
 */
public interface Implementation {

    public void operationImpl1(String pMessage);
    public void operationImpl2(Integer pNombre);
}

///////////////////////////////////////

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

////////////////////////////////////////

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

/////////////////////////////////////////

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

///////////////////////////////////////:

/**
 * Sous-classe concrète de l'abstraction
 */
public class AbstractionA extends Abstraction {

    public AbstractionA(Implementation pImplementation) {
        super(pImplementation);
    }
    
    public void operation() {
        System.out.println("--> Méthode operation() de AbstractionA");
        operationImpl1("A");
        operationImpl2(1);
        operationImpl1("B");
    }
}

///////////////////////////////////////////:

/**
 * Sous-classe concrète de l'abstraction
 */
public class AbstractionB extends Abstraction {

    public AbstractionB(Implementation pImplementation) {
        super(pImplementation);
    }
    
    public void operation() {
        System.out.println("--> Méthode operation() de AbstractionB");
        operationImpl2(9);
        operationImpl2(8);
        operationImpl1("Z");
    }
}

///////////////////////////////////////////////:

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
        lAbstractionAA.operation();
        lAbstractionAB.operation();
        lAbstractionBA.operation();
        lAbstractionBB.operation();
        
        // Affichage : 
        // --> Méthode operation() de AbstractionA
        // operationImpl1 de ImplementationA : A
        // operationImpl2 de ImplementationA : 1
        // operationImpl1 de ImplementationA : B
        // --> Méthode operation() de AbstractionA
        // operationImpl1 de ImplementationB : A
        // operationImpl2 de ImplementationB : 1
        // operationImpl1 de ImplementationB : B
        // --> Méthode operation() de AbstractionB
        // operationImpl2 de ImplementationA : 9
        // operationImpl2 de ImplementationA : 8
        // operationImpl1 de ImplementationA : Z
        // --> Méthode operation() de AbstractionB
        // operationImpl2 de ImplementationB : 9
        // operationImpl2 de ImplementationB : 8
        // operationImpl1 de ImplementationB : Z
    }
}

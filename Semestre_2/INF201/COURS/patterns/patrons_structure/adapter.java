/* 
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

////////////////////////////////////

/**
 * Implémente l'interface "Standard".
 */
public class ImplStandard implements Standard {

    public void operation(int pNombre1, int pNombre2) {
        System.out.println("Standard : Le nombre est : " + (pNombre1 * pNombre2));
    }
}

///////////////////////////////////

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

//////////////////////////////////////

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

///////////////////////////////////////////

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

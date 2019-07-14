/*
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

////////////////////////////////////////////

public abstract class ComplexeIndustriel {

	public Fruit getFruit() {
		return createFruit();
	}

	protected abstract Fruit createFruit();
}

////////////////////////////////////////////

public class UsinePomme extends ComplexeIndustriel {

	@Override
	protected Fruit createFruit() {
		return new Pomme();
	}
}

///////////////////////////////////////////

public class UsinePoire extends ComplexeIndustriel {
	@Override
	protected Fruit createFruit() {
		return new Poire();
	}
}

///////////////////////////////////////////

public abstract class Fruit {
	public abstract void afficheFruit();
}

//////////////////////////////////////////

public class Pomme extends Fruit {
	
	@Override
	public void afficheFruit() {
		System.out.println("Je suis une Pomme");
	}
}

//////////////////////////////////////////

public class Poire extends Fruit {
	
	@Override
	public void afficheFruit() {
		System.out.println("Je suis une Poire");
	}
}

///////////////////////////////////////////////////////////////////////

// Exemple 2 : 

public abstract class Room {
   abstract void connect(Room room);
}

public class MagicRoom extends Room {
   public void connect(Room room) {}
}

public class OrdinaryRoom extends Room {
   public void connect(Room room) {}
}

///////////////////////////////////////////

public abstract class MazeGame {
    private final List<Room> rooms = new ArrayList<>();

    public MazeGame() {
        Room room1 = makeRoom();
        Room room2 = makeRoom();
        room1.connect(room2);
        rooms.add(room1);
        rooms.add(room2);
    }

    abstract protected Room makeRoom();
}

public class MagicMazeGame extends MazeGame {
    @Override
    protected Room makeRoom() {
        return new MagicRoom(); 
    }
}

public class OrdinaryMazeGame extends MazeGame {
    @Override
    protected Room makeRoom() {
        return new OrdinaryRoom(); 
    }
}

MazeGame ordinaryGame = new OrdinaryMazeGame();
MazeGame magicGame = new MagicMazeGame();

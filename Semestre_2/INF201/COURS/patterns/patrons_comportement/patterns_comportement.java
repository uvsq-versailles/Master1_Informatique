/* ////////////////////////////////////////////////////
 * Command 
 */

public interface Command {
   void execute();
}

public class Interrupteur {

   private List<Command> history = new ArrayList<Command>();

   public Interrupteur() {
   }

   public void storeAndExecute(Command cmd) {
      this.history.add(cmd); // optional 
      cmd.execute();        
   }
}

public class Light {

   public Light() {
   }

   public void turnOn() {
      System.out.println("The light is on");
   }

   public void turnOff() {
      System.out.println("The light is off");
   }
}

public class Allumer implements Command {

   private Light theLight;

   public Allumer(Light light) {
      this.theLight = light;
   }

   public void execute(){
      theLight.turnOn();
   }
}

public class Eteindre implements Command {

   private Light theLight;

   public Eteindre(Light light) {
      this.theLight = light;
   }

   public void execute() {
      theLight.turnOff();
   }
}

public class PressSwitch {

   public static void main(String[] args){
      Light lamp = new Light();
      Command allumer = new Allumer(lamp);
      Command eteindre = new Eteindre(lamp);

      Interrupteur s = new Interrupteur();

      try {
         if (args[0].equalsIgnoreCase("ON")) {
            s.storeAndExecute(allumer);
            System.exit(0);
         }
         if (args[0].equalsIgnoreCase("OFF")) {
            s.storeAndExecute(eteindre);
            System.exit(0);
         }
         System.out.println("Argument \"ON\" or \"OFF\" is required.");
      } catch (Exception e) {
         System.out.println("Argument's required.");
      }
   }
}

/* ////////////////////////////////////////////////////
 * Iterator
 */

public interface Iterator {
   public boolean hasNext();
   public Object next();
}

public interface Container {
   public Iterator getIterator();
}

public class NameRepository implements Container {
   public String names[] = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {
      int index;
      
      @Override
      public boolean hasNext() {
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}

public class Main {
	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 
      
      /* Name : Robert, Name : John, Name : Julie, Name : Lora */
   }
}


/* ////////////////////////////////////////////////////
 * Observateur
 */

/** Classe représentant un GPS (appareil permettant de connaître sa position). */
public class Gps extends Observable{
        private String position;// Position du GPS.
        private int precision;// Précision accordé à cette position
        
        // Constructeur.
        public Gps(){
                position="inconnue";
                precision=0;
        }
        
        // Méthode permettant de notifier tous les observateurs lors d'un changement d'état du GPS.
        public void notifierObservateurs(){
                setChanged();// Méthode de l'API.
                notifyObservers();// Egalement une méthode de l'API.
        }
 
        // Méthode qui permet de mettre à jour de façon artificielle le GPS.
        // Dans un cas réel, on utiliserait les valeurs retournées par les capteurs.
        public void setMesures(String position, int precision){
                this.position=position;
                this.precision=precision;
                notifierObservateurs();
        }

        public String getPosition() return position;
        public int getPrecision() return precision;
}

/** Affiche un résumé en console des informations (position) du GPS. */
public class AfficheResume implements Observer {
      // Méthode appelée automatiquement lors d'un changement d'état du GPS.
      public void update(Observable o, Object obj){
              if(o instanceof Gps){       
                      Gps g = (Gps) o;
                      System.out.println("Position : "+g.getPosition());
              }       
      }
}

public class Main{
      // Méthode principale.
      public static void main(String[] args){
              // Création de l'objet Gps observable.
              Gps g = new Gps();
              // Création de deux observeurs AfficheResume et AfficheComplet
              AfficheResume ar = new AfficheResume();
              // On ajoute AfficheResume comme observeur de Gps.
              g.addObserver(ar);
              // On simule l'arrivée de nouvelles valeurs via des capteurs.
              g.setMesures("N 39°59°993 / W 123°00°000", 4);
              // Nouvelle simulation d'arrivée de nouvelles valeurs via des capteurs.
              g.setMesures("N 37°48°898 / W 124°12°011", 5);
      }
}

/* ////////////////////////////////////////////////////
 * Template method permet de deleguer des methodes aux sous classes 
 */
 
 public abstract class Game {
   abstract void initialize();
   abstract void startPlay();
   abstract void endPlay();

   //template method
   public final void play(){

      //initialize the game
      initialize();

      //start game
      startPlay();

      //end game
      endPlay();
   }
}

public class Cricket extends Game {

   @Override
   void endPlay() {
      System.out.println("Cricket Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Cricket Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Cricket Game Started. Enjoy the game!");
   }
}

public class Football extends Game {

   @Override
   void endPlay() {
      System.out.println("Football Game Finished!");
   }

   @Override
   void initialize() {
      System.out.println("Football Game Initialized! Start playing.");
   }

   @Override
   void startPlay() {
      System.out.println("Football Game Started. Enjoy the game!");
   }
}

public class TemplatePatternMain {
   public static void main(String[] args) {

      Game game = new Cricket();
      game.play();
      System.out.println();
      game = new Football();
      game.play();		
   }
}

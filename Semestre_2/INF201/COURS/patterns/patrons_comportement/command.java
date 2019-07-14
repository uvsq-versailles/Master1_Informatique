/*the Command interface*/
public interface Command {
   void execute();
}


/*the Invoker class*/
import java.util.List;
import java.util.ArrayList;

public class Switch {

   private List<Command> history = new ArrayList<Command>();

   public Switch() {
   }

   public void storeAndExecute(Command cmd) {
      this.history.add(cmd); // optional 
      cmd.execute();        
   }
}

/*the Receiver class*/
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

/*the Command for turning on the light - ConcreteCommand #1*/
public class FlipUpCommand implements Command {

   private Light theLight;

   public FlipUpCommand(Light light) {
      this.theLight = light;
   }

   public void execute(){
      theLight.turnOn();
   }
}

/*the Command for turning off the light - ConcreteCommand #2*/
public class FlipDownCommand implements Command {

   private Light theLight;

   public FlipDownCommand(Light light) {
      this.theLight = light;
   }

   public void execute() {
      theLight.turnOff();
   }
}

/*The test class or client*/
public class PressSwitch {

   public static void main(String[] args){
      Light lamp = new Light();
      Command switchUp = new FlipUpCommand(lamp);
      Command switchDown = new FlipDownCommand(lamp);

      Switch s = new Switch();

      try {
         if (args[0].equalsIgnoreCase("ON")) {
            s.storeAndExecute(switchUp);
            System.exit(0);
         }
         if (args[0].equalsIgnoreCase("OFF")) {
            s.storeAndExecute(switchDown);
            System.exit(0);
         }
         System.out.println("Argument \"ON\" or \"OFF\" is required.");
      } catch (Exception e) {
         System.out.println("Argument's required.");
      }
   }
}

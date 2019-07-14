//Classe représentant un GPS (appareil permettant de connaître sa position).
public class Gps extends Observable
{
        private String position;// Position du GPS.
        private int precision;// Précision accordé à cette position (suivant le nombre de satellite utilisés).
        
        // Constructeur.
        public Gps()
        {
                position="inconnue";
                precision=0;
        }
        
        // Méthode permettant de notifier tous les observateurs lors d'un changement d'état du GPS.
        public void notifierObservateurs()
        {
                setChanged();// Méthode de l'API.
                notifyObservers();// Egalement une méthode de l'API.
        }
 
        // Méthode qui permet de mettre à jour de façon artificielle le GPS.
        // Dans un cas réel, on utiliserait les valeurs retournées par les capteurs.
        public void setMesures(String position, int precision)
        {
                this.position=position;
                this.precision=precision;
                notifierObservateurs();
        }
        
        // Méthode "tiré" donc c'est aux observeurs d'aller chercher les valeurs désiré grâce à un objet Gps.
        // Pour cela on trouve un accesseur en lecture pour position.
        public String getPosition()
        {
                return position;
        }
        // Un accesseur en lecture pour précision.
        public int getPrecision()
        {
                return precision;
        }
}

//Affiche un résumé en console des informations (position) du GPS.
public class AfficheResume implements Observer
{
      // Méthode appelée automatiquement lors d'un changement d'état du GPS.
      public void update(Observable o, Object obj)
      {
              if(o instanceof Gps)
              {       
                      Gps g = (Gps) o;
                      System.out.println("Position : "+g.getPosition());
              }       
      }

}

//Classe principale du projet observeurApi.
public class Main
{
      // Méthode principale.
      public static void main(String[] args)
      {
              // Création de l'objet Gps observable.
              Gps g = new Gps();
              // Création de deux observeurs AfficheResume et AfficheComplet
              AfficheResume ar = new AfficheResume();
              // On ajoute AfficheResume comme observeur de Gps.
              g.addObserver(ar);
              // On simule l'arrivée de nouvelles valeurs via des capteurs.
              g.setMesures("N 39°59°993 / W 123°00°000", 4);
              // On ajoute AfficheComplet comme observeur de Gps.
              g.addObserver(ac);
              // Nouvelle simulation d'arrivée de nouvelles valeurs via des capteurs.
              g.setMesures("N 37°48°898 / W 124°12°011", 5);
      }
}

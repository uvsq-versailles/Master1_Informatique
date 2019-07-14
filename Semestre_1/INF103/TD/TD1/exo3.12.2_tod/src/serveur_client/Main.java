package serveur_client;

public class Main {
	public static void main(String[] args) {
		Client c1=new Client("clement");
		Client c2=new Client("Mehdi");
		Client c3=new Client("Ben");
		Client c4=new Client("Maxime");
		Serveur s=new Serveur();
		c1.seConnecter(s);
		c2.seConnecter(s);
		c3.seConnecter(s);
		c4.seConnecter(s);
		c1.envoyer("Coucou");
	}
}


package serveur_client;

public class Client {
	
	private String nom;
	private Serveur serveur;
	
	public String getNom() {
		return this.nom;
	}
	
	public Client (String nom) {
		this.nom=nom;
	}
	public boolean seConnecter (Serveur s) {
		if(s.connecter(this)==true) {
			this.serveur=s;
			System.out.println(this.nom+" "+"s'est connecté.");
			return true;
		}
		else return false;
	}
	
	public void envoyer(String message) {
		System.out.println(this.nom+" "+"envoit :"+message);
		this.serveur.diffuser(message);
	}
	
	public void recevoir(String message) {
		System.out.println(this.nom+" "+"recoit :"+message);
	}

}

package fr.uvsq.pglp.kholle;

import java.time.LocalDate;

public class Personne {
	private final String nom;
	private final String prenom;
	private final LocalDate dateNaissance;
	private final String email;
	
	public static class Builder{
		private final String nom;
		private final String prenom;
		private LocalDate dateNaissance=LocalDate.now();
		private String email="inconnu@inconnu.fr";

		public Builder(String nom, String prenom){
			this.nom=nom;
			this.prenom=prenom;
		}

		//faire la meme chose pour chaque parametre
		public Builder dateNaissance(LocalDate dateNaissance){
			this.dateNaissance=dateNaissance;
			return this;
		}
		
		public Builder email(String email){
			this.email=email;
			return this;
		}

		public Personne build(){
			return new Personne(this);
		}
	}
	
	private Personne(Builder builder){
		nom=builder.nom;
		prenom=builder.prenom;
		
		dateNaissance=builder.dateNaissance;
		email=builder.email;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getEmail() {
		return email;
	}
	
}

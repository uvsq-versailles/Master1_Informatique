package fr.uvsq.pglp.builder.composite.iterator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Personnel extends PersonnelType {

	private final String nom;
	private final String prenom;
	private final String fonction;
	private final LocalDate dateNaissance;
	private final List<NumeroTelephone> numerosTelephone;
	
	public static class PersonnelBuilder {
		
		// Requis
		private final String nom;
		private final String prenom;
		private final String fonction;
		
		// Optionnel
		private LocalDate dateNaissance = null;
		private List<NumeroTelephone> numerosTelephone = new ArrayList<NumeroTelephone>();
		
		public PersonnelBuilder(String nom, String prenom, String fonction) {
			this.nom = nom;
			this.prenom = prenom;
			this.fonction = fonction;
		}
		
		public PersonnelBuilder dateNaissance(LocalDate dateNaissance) {
			this.dateNaissance = dateNaissance;
			return this;
		}
		
		public PersonnelBuilder addNumeroTelephone(NumeroTelephone numeroTelephone) {
			this.numerosTelephone.add(numeroTelephone);
			return this;
		}
		
		public Personnel build() {
			return new Personnel(this);
		}
	}
	
	private Personnel(PersonnelBuilder builder) {
		nom = builder.nom;
		prenom = builder.prenom;
		fonction = builder.fonction;
		dateNaissance = builder.dateNaissance;
		numerosTelephone = builder.numerosTelephone;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return nom + " " + prenom + " " + fonction + " "
				+ dateNaissance.format(DateTimeFormatter.ISO_DATE) + " "
				+ numerosTelephone.stream().map(NumeroTelephone::toString).collect(Collectors.toList());
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the fonction
	 */
	public String getFonction() {
		return fonction;
	}

	/**
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @return the numerosTelephone
	 */
	public List<NumeroTelephone> getNumerosTelephone() {
		return numerosTelephone;
	}
	
	@Override
	public boolean isGroupe() {
		return false;
	}
	
	
	


}

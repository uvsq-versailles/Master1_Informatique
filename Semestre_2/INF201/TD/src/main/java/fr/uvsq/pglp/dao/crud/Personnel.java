package fr.uvsq.pglp.dao.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Personnel extends PersonnelType {

	private static final long serialVersionUID = 1L;
	
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
				+ dateNaissanceString()
				+ numerosTelephone.stream().map(NumeroTelephone::toString).collect(Collectors.toList());
	}

	private String dateNaissanceString() {
		if (dateNaissance == null) return ""; 
		return (dateNaissance.format(DateTimeFormatter.ISO_DATE) + " ");
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
	
	public void addNumeroTelephone(NumeroTelephone numeroTelephone) {
		this.numerosTelephone.add(numeroTelephone);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((fonction == null) ? 0 : fonction.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numerosTelephone == null) ? 0 : numerosTelephone.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnel other = (Personnel) obj;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (fonction == null) {
			if (other.fonction != null)
				return false;
		} else if (!fonction.equals(other.fonction))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numerosTelephone == null) {
			if (other.numerosTelephone != null)
				return false;
		} else if (!numerosTelephone.equals(other.numerosTelephone))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	@Override
	public boolean isGroupe() {
		return false;
	}	
}

package banque.src;

import banque.exceptions.*;

public class CompteBancaire {
	
	private int solde;

	public CompteBancaire(int solde) {
		if (solde < 0) throw new DecouvertException();
		this.solde = solde;
	}

	public int getSolde() {
		return solde;
	}
	
	public void crediteSolde(int montant) {
		if (montant < 0) throw new MontantNegatifException();
		montant = Math.abs(montant);
		this.solde += montant;
	}
	
	public void debiteSolde(int montant) {
		if (montant < 0) throw new MontantNegatifException();
		if (this.solde - montant < 0) throw new DecouvertException();
		montant = Math.abs(montant);
		this.solde -= montant;
	}
	
	public void virement(int montant, CompteBancaire receveur) {
		if (montant < 0) throw new MontantNegatifException();
		if (this.solde - montant < 0) throw new DecouvertException();
		receveur.crediteSolde(montant);
		this.debiteSolde(montant);
	}
}
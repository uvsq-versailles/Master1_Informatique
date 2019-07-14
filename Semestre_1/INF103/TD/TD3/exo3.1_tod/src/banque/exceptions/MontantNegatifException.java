package banque.exceptions;

public class MontantNegatifException extends RuntimeException {

	public MontantNegatifException() {
		super("Le montant donné est négatif");
		// TODO Auto-generated constructor stub
	}

}
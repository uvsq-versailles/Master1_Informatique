package banque.exceptions;

public class DecouvertException extends RuntimeException {

	public DecouvertException() {
		super("L'opération implique que le compte soit à découvert");
		// TODO Auto-generated constructor stub
	}
}

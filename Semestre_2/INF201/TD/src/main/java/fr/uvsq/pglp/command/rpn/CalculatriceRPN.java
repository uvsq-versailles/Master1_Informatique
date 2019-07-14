package fr.uvsq.pglp.command.rpn;


public enum CalculatriceRPN{
	ENVIRONNEMENT;
	
	public void run( String[] args) {
		SaisieRPN saisie = new SaisieRPN();
		saisie.saisie();
	}
	
	public static void main( String[] args ) {
		ENVIRONNEMENT.run(args);
		
	}
}
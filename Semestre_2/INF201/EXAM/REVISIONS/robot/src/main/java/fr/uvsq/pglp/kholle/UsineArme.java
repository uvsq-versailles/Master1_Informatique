package fr.uvsq.pglp.kholle;

public class UsineArme extends Usine {
	
	@Override
	protected Option createOption() {
		return new Arme();
	}
}
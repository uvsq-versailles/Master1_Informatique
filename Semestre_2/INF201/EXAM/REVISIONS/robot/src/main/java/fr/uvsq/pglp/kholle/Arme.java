package fr.uvsq.pglp.kholle;

public class Arme extends Option {
	
	public void afficheOption() {
		System.out.println(returnOption());
	}
	
	public String returnOption() {
		return "Arme";
	}
}
package fr.uvsq.pglp.kholle;

public class UsineEquipement extends Usine {
	
	@Override
	protected Option createOption() {
		return new Equipement();
	}
}

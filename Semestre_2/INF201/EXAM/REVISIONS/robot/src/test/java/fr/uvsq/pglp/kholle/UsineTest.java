package fr.uvsq.pglp.kholle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsineTest {
	@Test
	public void testUsineArme() {
		Usine usineArme = new UsineArme();	
		Option arme1 = null;
		arme1 = usineArme.getOption(); 
		assertEquals(arme1.returnOption(), "Arme");
	}
	
	@Test
	public void testUsineEquipement() {
		Usine usineEquipement = new UsineEquipement();	
		Option equipement = null;
		equipement = usineEquipement.getOption(); 
		assertEquals(equipement.returnOption(), "Equipement");
	}
}

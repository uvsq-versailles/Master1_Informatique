package fr.uvsq.pglp.kholle;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class RobotTest {

	@Test
	public void testRobotVide() {
		Robot r = new Robot	
				.Builder("Wall-e")
				.build();
		assertEquals(r.getNom(), "Wall-e");
		assertEquals(r.getOptions().size(), 0);
	}
	
	@Test
	public void testRobotRempli() {
		Usine usineArme = new UsineArme();	
		Usine usineEquipement = new UsineEquipement();
		Option arme = null;
		Option equipement = null;
		arme = usineArme.getOption();
		equipement = usineEquipement.getOption(); 
		
		Robot r = new Robot	
				.Builder("Wall-e")
				.options(arme)
				.options(equipement)
				.build();
		assertEquals(r.getNom(), "Wall-e");
		assertEquals(r.getOptions().size(), 2);
		assertEquals(r.getOptions().get(0).returnOption(), "Arme");
		assertEquals(r.getOptions().get(1).returnOption(), "Equipement");
	}
}

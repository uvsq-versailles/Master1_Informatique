package fr.uvsq.pglp.kholle;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class PersonneTest {

	@Test
	public void testConstructeur1() {
		Personne p = new Personne	
				.Builder("Caumes", "Clément")
				.build();
		assertEquals(p.getNom(), "Caumes");
		assertEquals(p.getPrenom(), "Clément");
		assertEquals(p.getDateNaissance(), LocalDate.now());
		assertEquals(p.getEmail(), "inconnu@inconnu.fr");
	}
	
	@Test
	public void testConstructeur2() {
		Personne p = new Personne	
				.Builder("Caumes", "Clément")
				.email("clement.caumes@ens.uvsq.fr")
				.build();
		assertEquals(p.getNom(), "Caumes");
		assertEquals(p.getPrenom(), "Clément");
		assertEquals(p.getDateNaissance(), LocalDate.now());
		assertEquals(p.getEmail(), "clement.caumes@ens.uvsq.fr");
	}
}

package fr.uvsq.pglp.kholle.caumes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WagonTest {
	
	@Test
	public void test1A() {
		Wagon w = new Wagon("bar", 10);
		assertEquals(w.getNom(), "bar");
		assertEquals(w.getPoids(), 10);
	}
}

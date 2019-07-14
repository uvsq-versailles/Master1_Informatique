package fr.uvsq.pglp.command.rpn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RpnTest {

	@Before()
	public void setUp() {
		
	}
	
	
	@Test()
	public void testLogStr() {
		Interpreteur i = new Interpreteur();
		i.log("Bonjour");
		assertEquals("Bonjour", i.getLastLog());
	}
	
}


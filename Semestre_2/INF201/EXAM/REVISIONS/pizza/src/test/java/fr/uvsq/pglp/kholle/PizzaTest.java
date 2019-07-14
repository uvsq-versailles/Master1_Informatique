package fr.uvsq.pglp.kholle;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class PizzaTest {
	@Test
	public void testConstructeur1() {
		Pizza pizza = new Pizza();
		Sauce s = new Sauce("Barbecue");
		Pate p = new Pate("Leclerc");
		Mozarella m = new Mozarella("Italie");
		pizza.add(s);
		pizza.add(p);
		pizza.add(m);
		assertEquals(pizza.toString(), "[SauceBarbecue, PateLeclerc, MozarellaItalie, ]");
		
	}
}

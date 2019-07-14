package fr.uvsq.inf103.rogue_like.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests unitaires sur la classe Monde.
 */
public class MondeTest {

	/**
	 * Monde a tester.
	 */
	private	Monde w;

	/**
	 * Tableau d'elements representant le monde.
	 */
	private Element[][] e;

	/**
	 * Permet d'initialiser les variables que l'on va tester. 
	 */
	@Before 
	public void initialize() {
		e = new Element[50][70];
		w= new Monde(e);
	}
	
	/**
	 * Test de l'affectation de l'enumeration FLOOR à un element.
	 */
	@Test
	public void testAffectationElement() {
		w.setElement(1,1,Element.FLOOR);
		assertEquals(w.getElement(1,1),Element.FLOOR);
	}

	/**
	 * Tests accesseur de longueur.
	 */
	@Test
	public void testLongueur() {
		assertEquals(w.getLongueur(),50);
	}
	
	/**
	 * Test accesseur de largeur.
	 */
	@Test
	public void testLargeur() {
		assertEquals(w.getLargeur(),70);
	}
}

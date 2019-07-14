package fr.uvsq.inf103.calculatricerpn;


import static org.junit.Assert.*;

import org.junit.Test;

import fr.uvsq.inf103.calculatricerpn.exc.*;
import fr.uvsq.inf103.calculatricerpn.exc.DivParZeroException;
import fr.uvsq.inf103.calculatricerpn.src.Operation;

/**
 * Classe de test OperationTest - Tests sur les methodes de Operation
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 */
public class OperationTest {
	
	private Operation o;
	
	/**
	 * test la constante Plus de la classe Operation
	 */
	@Test
	public void testPlus() throws DivParZeroException {
		o= Operation.PLUS;
		boolean test=(o.eval(2,3)==5.0);
		assertEquals(test,true);
	}
	/**
	 * test la constante Moins de la classe Operation
	 */
	@Test
	public void testMoins() throws DivParZeroException {
		o= Operation.MOINS;
		boolean test=(o.eval(2,3)==1.0);
		assertEquals(test,true);
	}
	/**
	 * test la constante Mult de la classe Operation
	 */
	@Test
	public void testMult() throws DivParZeroException {
		o= Operation.MULT;
		boolean test=(o.eval(2,3)==6.0);
		assertEquals(test,true);
	}
	/**
	 * test la constante Div de la classe Operation
	 */
	@Test
	public void testDiv() throws DivParZeroException {
		o= Operation.DIV;
		boolean test=(o.eval(2,3)==1.5);
		assertEquals(test,true);
	}
	/**
	 * Ce test effectue l'operation de division par 0 et doit retourner une exception
	 */
	@Test(expected=DivParZeroException.class)
	public void testDivParZero() throws DivParZeroException {
		o= Operation.DIV;
		o.eval(0,3);
	}	
}

package fr.uvsq.inf103.calculatricerpn;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import fr.uvsq.inf103.calculatricerpn.exc.DivParZeroException;
import fr.uvsq.inf103.calculatricerpn.exc.LimiteException;
import fr.uvsq.inf103.calculatricerpn.exc.OperationException;
import fr.uvsq.inf103.calculatricerpn.src.SaisieRPN;

/**
 * Classe Test de SaisieRPN
 * @author user
 *
 */
public class SaisieRPNTest {
	
	private String data;
	private InputStream stdin;
	private Scanner sc;
	private SaisieRPN saisie;
	
	/**
	 * Initialisation des paramètres identiques des différents tests
	 */
	void init(InputStream stdin, String data, Scanner sc) 
			throws DivParZeroException, LimiteException, OperationException{
		stdin=System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		sc=new Scanner(System.in);
	}
	
	/**
	 * Test pour la saisie de "2" puis "exit"
	 */
	@Test
	public void testSaisieUnElement() throws DivParZeroException, LimiteException, OperationException{
		data="2\nexit";
		init(stdin, data, sc);

		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==2.0);
		assertEquals(test,true);
	}
	
	/**
	 * Test pour la saisie de "2", puis "3", puis "exit"
	 */
	@Test
	public void testSaisieDeuxElements() throws DivParZeroException, LimiteException, OperationException{
		data="2\n3\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==3.0);
		assertEquals(test,true);
		test=(saisie.getMoteurRPN().getPile().pop()==2.0);
		assertEquals(test,true);
	}
	
	/**
	 * Test pour la saisie "2", "3", "+" puis "exit"
	 */
	@Test
	public void testSaisiePlus() throws DivParZeroException, LimiteException, OperationException{
		data="2\n3\n+\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==5.0);
		assertEquals(test,true);
	}
	
	/**
	 * Test pour la saisie de "2", "3", "-", "exit"
	 */
	@Test
	public void testSaisieMoins() throws DivParZeroException, LimiteException, OperationException{
		data="2\n3\n-\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==-1.0);
		assertEquals(test,true);
	}
	
	/**
	 * Test la saisie de "2", "3", "*", "exit"
	 */
	@Test
	public void testSaisieMult() throws DivParZeroException, LimiteException, OperationException{
		data="2\n3\n*\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==6.0);
		assertEquals(test,true);
	}
	
	/**
	 * Test la saisie de "3", "2", "/" et "exit"
	 */
	@Test
	public void testSaisieDiv() throws DivParZeroException, LimiteException, OperationException{
		data="3\n2\n/\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
		boolean test=(saisie.getMoteurRPN().getPile().pop()==1.5);
		assertEquals(test,true);
	}
	
	/**
	 * Test la saisie de "3", "0", "/", "exit"
	 */
	@Test (expected=DivParZeroException.class)
	public void testSaisieDivParZero() throws DivParZeroException, LimiteException, OperationException{
		data="3\n0\n/\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
	}
	
	/**
	 * Test la saisie de "10000000", "0", "exit"
	 */
	@Test (expected=LimiteException.class)
	public void testSaisieMin() throws DivParZeroException, LimiteException, OperationException{
		data="10000000\n0\n/\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
	}
	
	/**
	 * Test la saisie "-10000000", "0", "exit"
	 */
	@Test (expected=LimiteException.class)
	public void testSaisieMax() throws DivParZeroException, LimiteException, OperationException{
		data="-10000000\n0\n/\nexit";
		init(stdin, data, sc);
		
		saisie = new SaisieRPN();
		saisie.saisie();
	}
}

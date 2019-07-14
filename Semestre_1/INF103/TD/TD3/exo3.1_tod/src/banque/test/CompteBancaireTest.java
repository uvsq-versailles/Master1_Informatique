package banque.test;

import banque.src.CompteBancaire;
import banque.exceptions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CompteBancaireTest {

	@Test
	public void testNotNull() {
		CompteBancaire cpte = new CompteBancaire(1);
		assertNotNull(cpte);
	}
	
	@Test
	public void testSolde() {
		CompteBancaire cpte = new CompteBancaire(3);
		int solde1 = cpte.getSolde();
		assertEquals(3,solde1);
	}
	
	@Test(expected=DecouvertException.class)
	public void testSuperieurSolde() {
		CompteBancaire cpte = new CompteBancaire(-3);
	}
		
	@Test
	public void testCredit() {
		CompteBancaire cpte = new CompteBancaire(3);
		cpte.crediteSolde(1);
		assertEquals(4, cpte.getSolde());
	}
	
	@Test(expected=MontantNegatifException.class)
	public void testCreditNegatif() {
		CompteBancaire cpte = new CompteBancaire(3);
		cpte.crediteSolde(-1);
	}
	
	@Test
	public void testDebit() {
		CompteBancaire cpte = new CompteBancaire(3);
		cpte.debiteSolde(1);
		assertEquals(2, cpte.getSolde());
	}
	
	@Test(expected=MontantNegatifException.class)
	public void testDebitNegatif() {
		CompteBancaire cpte = new CompteBancaire(3);
		cpte.debiteSolde(-1);
	}
	
	@Test(expected=DecouvertException.class)
	public void testDebitSuperieurSolde() {
		CompteBancaire cpte = new CompteBancaire(3);
		cpte.debiteSolde(5);
	}	
	
	@Test
	public void testVirement() {
		CompteBancaire cpte1 = new CompteBancaire(300);
		CompteBancaire cpte2 = new CompteBancaire(800);
		cpte1.virement(100, cpte2);
		cpte2.virement(300, cpte1);
		assertEquals(500, cpte1.getSolde());
		assertEquals(600, cpte2.getSolde());
	}
	
	@Test(expected=MontantNegatifException.class)
	public void testVirementNegatif(){
		CompteBancaire cpte1 = new CompteBancaire(300);
		CompteBancaire cpte2 = new CompteBancaire(800);
		cpte1.virement(-100, cpte2);
	}
	
	@Test(expected=DecouvertException.class)
	public void testVirementSuperieurSolde(){
		CompteBancaire cpte1 = new CompteBancaire(300);
		CompteBancaire cpte2 = new CompteBancaire(800);
		cpte1.virement(1000, cpte2);
}

}

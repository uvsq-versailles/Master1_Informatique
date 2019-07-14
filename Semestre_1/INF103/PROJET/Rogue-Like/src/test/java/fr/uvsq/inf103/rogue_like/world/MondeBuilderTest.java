package fr.uvsq.inf103.rogue_like.world;

import static org.junit.Assert.*;
import fr.uvsq.inf103.rogue_like.exception.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests unitaires sur MondeBuilder.
 */
public class MondeBuilderTest {

	/**
	 * MondeBuilder qui sera teste.
	 */
	private MondeBuilder w;

	/**
	 * Permet d'initialiser les variables que l'on va tester. 
	 */
	@Before
	public void initialize() {
		w = new MondeBuilder(50,50);
	}

	/**
	 * Test si l'élément est pas null.
	 */
	@Test
	public void testConstructeur() {
		assertNotNull(w.getElements());
	}

	/**
	 * Test si il existe de l'argent dans la map.
	 */
	@Test
	public void testExistMoney() {
		int nbmoney=0;
		w.fabriquerElements();
		for(int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				if(w.getElement(i, j)==Element.MONEY)
				nbmoney++;
			}
		}
		assertNotEquals(nbmoney,0);
	}
	
	/**
	 * Test si il existe une porte dans la map.
	 */
	@Test
	public void testBuildWall() {
		int porte=0;
		w.fabriquerElements();
		for(int i=0;i<50;i++) {
			for (int j=0;j<50;j++) {
				if(w.getElement(i, j)==Element.DOOR)
				porte++;
			}
		}
		assertNotEquals(porte,0);
	}

	/**
	 * Test si une exception PorteException est bien jetee si il manque une porte lors de la creation.
	 */
	@Test (expected=PorteException.class)
	public void testPorteException(){
		MondeBuilder monde= new MondeBuilder(80,21);
		int i, j;
		for(i=0;i<80;i++) {
			for(j=0;j<21;j++) {
				monde.setElement(i,j,Element.FLOOR);
			}
		}
		for(i=0;i<10;i++){
			monde.setElement(i,0,Element.MONEY);
		}
		monde.setElement(10,0,Element.BATTE_BASEBALL);
		monde.testerConfigurationValide();

	}

	/**
	 * Test si une exception ArmeException est bien jetee si il manque une arme sur la map.
	 */
	@Test (expected=ArmeException.class)
	public void testArmeException(){
		MondeBuilder monde= new MondeBuilder(80,21);
		int i, j;
		for(i=0;i<80;i++) {
			for(j=0;j<21;j++) {
				monde.setElement(i,j,Element.FLOOR);
			}
		}
		for(i=0;i<10;i++){
			monde.setElement(i,0,Element.MONEY);
		}
		monde.setElement(10,0,Element.DOOR);
		monde.testerConfigurationValide();

	}

	/**
	 * Test si une exception ArgentException est bien lancee si il n'y a pas assez d'argent sur la map.
	 */
	@Test (expected=ArgentException.class)
	public void testArgentException(){
		MondeBuilder monde= new MondeBuilder(80,21);
		int i, j;
		for(i=0;i<80;i++) {
			for(j=0;j<21;j++) {
				monde.setElement(i,j,Element.FLOOR);
			}
		}
		for(i=0;i<9;i++){
			monde.setElement(i,0,Element.MONEY);
		}
		monde.setElement(10,0,Element.DOOR);
		monde.setElement(11,0,Element.BATTE_BASEBALL);
		monde.testerConfigurationValide();
	}
}

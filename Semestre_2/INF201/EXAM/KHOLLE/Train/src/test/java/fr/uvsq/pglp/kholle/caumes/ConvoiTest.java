package fr.uvsq.pglp.kholle.caumes;

import static org.junit.Assert.assertEquals;
import fr.uvsq.pglp.kholle.caumes.exception.AjoutException;

import org.junit.Test;



public class ConvoiTest {
	@Test
	public void test1B() {
		Wagon w1=new Wagon("voitures", 20);
		Wagon w2=new Wagon("camions", 40);
		Convoi c = new Convoi	
				.Builder("vehicules", "Paris")
				.addBuilder(w1)
				.addBuilder(w2)
				.build();
		
		//c.add(w1);
		//c.add(w2);
		assertEquals(c.getContenu().size(), 2);
		assertEquals(c.getContenu().get(0), w1);
		assertEquals(c.getContenu().get(1), w2);
		
	}
	
	@Test
	public void test1C() {
		//Convoi c=new Convoi("vehicules");
		Wagon w1=new Wagon("voitures", 20);
		Wagon w2=new Wagon("camions", 40);
		Convoi c = new Convoi	
				.Builder("vehicules", "Paris")
				//.addBuilder(w1)
				//.addBuilder(w2)
				.build();
		assertEquals(c.getPoids(), 0);
		
		c.add(w1);
		c.add(w2);
		assertEquals(c.getPoids(), 60);
	}
	
	@Test (expected=AjoutException.class)
	public void testD() {
		//Convoi c=new Convoi("vehicules");
		Convoi c = new Convoi	
				.Builder("vehicules", "Paris")
				.build();
		c.add(c);
	}
	
	@Test (expected=AjoutException.class)
	public void test1E() {
		//Convoi c=new Convoi("vehicules");
		//Convoi c2=new Convoi("camions");
		Convoi c = new Convoi	
				.Builder("vehicules", "Paris")
				.build();
		Convoi c2 = new Convoi	
				.Builder("camions", "Paris")
				.build();
		c.add(c2);
		c2.add(c);
	}
	
	@Test
	public void test2A() {
		Convoi c = new Convoi	
				.Builder("paris1", "Paris")
				.build();
		Wagon w1=new Wagon("bar", 10);
		c.add(w1);
		assertEquals(c.getPoids(), 10);
	}
	
	@Test
	public void test2B() {
		Convoi c = new Convoi	
				.Builder("paris1", "Paris")
				.build();
		Wagon w1=new Wagon("bar", 10);
		c.add(w1);
		Convoi c2=new Convoi
				.Builder("paris1", "Paris")
				.build();
		c2.add(c);
		assertEquals(c2.getPoids(), 10);
	}
	
}

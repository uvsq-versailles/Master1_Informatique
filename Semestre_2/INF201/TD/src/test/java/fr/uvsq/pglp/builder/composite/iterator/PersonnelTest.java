package fr.uvsq.pglp.builder.composite.iterator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq.pglp.builder.composite.iterator.Personnel.PersonnelBuilder;

public class PersonnelTest {

	private Personnel pers;
	
	@Before()
	public void setUp() {
		pers = new PersonnelBuilder("Jean", "Jacques", "Plombier")
				.dateNaissance(LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_DATE))
				.addNumeroTelephone(new NumeroTelephone("Perso", "0666666666"))
				.addNumeroTelephone(new NumeroTelephone("Fax", "0612345678"))
				.build();	
	}
	
	
	@Test()
	public void testGetNom() {
		assertEquals(pers.getNom(), "Jean");
	}
	
	@Test()
	public void testGetPrenom() {
		assertEquals(pers.getPrenom(), "Jacques");
	}
	
	@Test()
	public void testGetFonction() {
		assertEquals(pers.getFonction(), "Plombier");
	}
	
	@Test()
	public void testGetDateNaissance() {
		assertEquals(pers.getDateNaissance(), 
				LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_DATE));
	}
	
	@Test()
	public void testGetNumerosTelephone() {
		List<NumeroTelephone> numList = new ArrayList<NumeroTelephone>();
		numList.add(new NumeroTelephone("Perso", "0666666666"));
		numList.add(new NumeroTelephone("Fax", "0612345678"));
		assertEquals(pers.getNumerosTelephone(), numList);	
	}
	
	@Test()
	public void testTypeStringPersonnel() {
		assertEquals(pers.typeString(), "Personnel");
	}
	
	@Test()
	public void testTypeStringGroupe() {
		assertEquals((new PersonnelGroupe()).typeString(), "Groupe");
	}
	
	
	
}

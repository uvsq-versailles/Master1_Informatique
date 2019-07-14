package systeme_fichiers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import systeme_fichiers.exceptions.TailleNegativeException;
import systeme_fichiers.exceptions.TailleNulleException;
import systeme_fichiers.main.Fichier;

/**
 * Classe de test FichierTest - Tests sur les methodes de fichier
 * 
 * @author Clément Caumes, Mehdi Merimi
 * @version 1.0
 */
public class FichierTest {

	/**
	 * Teste getTaille() de Fichier.
	 */
	@Test
	public void testGetTailleFichier() {
		Fichier f=new Fichier("test",10);
		assertEquals(f.getTaille(),10);
	}
	
	/**
	 * Teste getTaille() de Fichier pour un nombre négatif
	 */
	@Test(expected=TailleNegativeException.class)
	public void testTailleNegativeFichier() {
		Fichier f=new Fichier("test",-10);
	}
	
	/**
	 * Teste getTaille() de Fichier pour une taille nulle
	 */
	@Test(expected=TailleNulleException.class)
	public void testTailleNulleFichier() {
		Fichier f=new Fichier("test",0);
	}
	
	/**
	 * Teste getNom() de Fichier
	 */
	@Test
	public void testGetNomFichier() {
		Fichier f=new Fichier("test",10);
		assertEquals(f.getNom(),"test");
	}
	
	/**
	 * Teste contient(Element) de Fichier
	 */
	@Test
	public void testContientFichier() {
		Fichier f=new Fichier("test",10);
		Fichier autre=new Fichier("autre",15);
		assertEquals(f.contient(autre),false);
	}

}

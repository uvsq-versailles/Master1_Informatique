package systeme_fichiers.test;


import static org.junit.Assert.*;

import org.junit.Test;

import systeme_fichiers.exceptions.AjoutException;
import systeme_fichiers.main.Fichier;
import systeme_fichiers.main.Repertoire;

   /**
 	*@author Clement Caumes,Mehdi Merimi
	*@version 1.0
	*/
public class RepertoireTest {
	
	/**
	 * Teste si la taille du repertoire est vide.
	 */
	@Test
	public void testGetTailleRepertoireVide() {
		Repertoire r1=new Repertoire("repertoire1");
		assertEquals(r1.getTaille(),0);
	}
	
	/**
	 * Teste si la taille du repertoire n'est pas vide.
	 */
	@Test
	public void testGetTailleRepertoireRempli() {
		Repertoire r2=new Repertoire("repertoire1");
		Repertoire r3=new Repertoire("repertoire2");
		Fichier f2=new Fichier("fichier",15);
		r3.ajouteElement(f2);
		r2.ajouteElement(r3);
		assertEquals(r2.getTaille(),15);
	}
	
	/**
	 * Teste si la liste est vide.
	 */
	@Test
	public void testGetListeVide() {
		Repertoire r3= new Repertoire("repertoire 1");
		assertEquals(r3.getListe().size(),0);
	}
	
	/**
	 * Teste si la liste est remplie avec un élément.
	 */
	@Test
	public void testGetListeRemplie() {
		Repertoire r4= new Repertoire("repertoire 1");
		Fichier f4=new Fichier ("fichier 1",10);
		r4.ajouteElement(f4);
		assertEquals(r4.getListe().size(),1);
	}
	
	/**
	 * Teste si l'ajout d'un fichier dans un répertoire est possible
	 */
	@Test
	public void testContientFalse() {
		Repertoire r4= new Repertoire("repertoire 1");
		Fichier f4=new Fichier("fichier 1",10);
		assertEquals(r4.contient(f4),false);
	}
	
	/**
	 * Teste si l'ajout d'un fichier déjà présent dans le répertoire est bien 
	 * impossible.
	 */
	@Test
	public void testContientTrue() {
		Repertoire r5= new Repertoire("repertoire 1");
		Fichier f5=new Fichier("fichier 1",10);
		r5.ajouteElement(f5);
		assertEquals(r5.contient(f5),true);
	}
	
	/**
	 * Teste si un répertoire ne peut pas 
	 * être ajouté à lui-même (question 4).
	 */
	@Test(expected=AjoutException.class)
	public void testAjoutRepertoireLuiMeme() {
		Repertoire r10=new Repertoire("repertoire 2");
		r10.ajouteElement(r10);	
	}
	
	/**
	 * Teste si un répertoire ne peut pas être 
	 * ajouté comme descendant de lui-même (question 5).
	 */
	@Test(expected=AjoutException.class)
	public void testAjoutRepertoireDescendance() {
		Repertoire r11=new Repertoire("repertoire 1");
		Fichier f11=new Fichier("fichier 1",10);
		r11.ajouteElement(f11);
		Repertoire r12=new Repertoire("repertoire 2");
		Fichier f12=new Fichier("fichier 2",14);
		Repertoire r13=new Repertoire("repertoire 3");
		r12.ajouteElement(f12);
		r11.ajouteElement(r12);
		r12.ajouteElement(r13);
		r13.ajouteElement(r11);
	}
}
 



package fr.uvsq.pglp.kholle;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Usine usineArme = new UsineArme();	
		
		Option arme1 = null;
		System.out.println("Utilisation de la premiere fabrique");
		arme1 = usineArme.getOption(); 
		arme1.afficheOption();
    }
}

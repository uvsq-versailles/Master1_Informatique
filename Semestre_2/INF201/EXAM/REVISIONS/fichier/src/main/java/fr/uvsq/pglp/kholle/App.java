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
       System.out.println( "Hello World!" );
       Fichier f1=new Fichier
    		   	.Builder("test1.txt")
    			.taille(12)
    			.date(LocalDate.of(2018, 03, 13))
    			.build();
       
       Fichier f2=new Fichier
   		   	.Builder("test2.txt")
   			.taille(17)
   			.build();
       
       Fichier f3=new Fichier
      		   	.Builder("test3.txt")
      			.taille(2)
      			.build();
       
       Dossier d1=new Dossier("dossier1");
       d1.add(f1);
       d1.add(f2);
       
       Dossier d2=new Dossier("dossier2");
       d2.add(d1);
       d2.add(f3);
       
       d2.print();
    		   
    }
}

package fr.uvsq.pglp;

import fr.uvsq.pglp.builder.composite.iterator.AnnuairePersonnels;
import fr.uvsq.pglp.command.rpn.CalculatriceRPN;
import fr.uvsq.pglp.dao.crud.Serialization;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnuairePersonnels.main(args);
        CalculatriceRPN.main(args);
        Serialization.main(args);
    }
}

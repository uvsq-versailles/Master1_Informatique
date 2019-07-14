package fr.uvsq.pglp.kholle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Pizza pizza = new Pizza();
		Sauce s = new Sauce("Barbecue");
		Pate p = new Pate("Leclerc");
		Mozarella m = new Mozarella("Italie");
		pizza.add(s);
		pizza.add(p);
		pizza.add(m);
		System.out.println(pizza.toString());
    }
}

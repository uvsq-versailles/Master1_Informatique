
public class Main {
	public static void main(String[] args) {
		Fraction f1=new Fraction(1,3);
		System.out.println(f1.toString());
		Fraction f2=new Fraction(1,6);
		System.out.println(f2.toString());
		if(f1.equals(f2)==true) System.out.println("EGALITE");
		else System.out.println("DIFFERENCE");
		
		Fraction f3=new Fraction(1,3);
		System.out.println(f3.toString());
		Fraction f4=new Fraction(2,6);
		System.out.println(f4.toString());
		if(f3.equals(f4)==true) System.out.println("EGALITE");
		else System.out.println("DIFFERENCE");
		
		Fraction f5=new Fraction(1,3);
		System.out.println(f5.toString());
		Fraction f6=new Fraction(2,3);
		System.out.println(f6.toString());
		System.out.println(f5.compareTo(f6));

	}
}

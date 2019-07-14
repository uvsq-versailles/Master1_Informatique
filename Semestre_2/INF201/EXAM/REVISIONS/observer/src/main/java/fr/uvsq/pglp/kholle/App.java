package fr.uvsq.pglp.kholle;


public class App{
	public static void main(String[] args) {
		ConcreteObserve o = new ConcreteObserve();
		Observateur A = new ObservateurA();
		Observateur B = new ObservateurB();
		A.afficher();
		B.afficher();
		o.ajouterObs(A);
		A.setObs(o);
		o.setV(2);
		o.ajouterObs(B);
		B.setObs(o);
		A.afficher();
		B.afficher();

		o.setV(4);

		A.afficher();
		B.afficher();
	}
}

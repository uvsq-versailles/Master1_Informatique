package fr.uvsq.pglp.kholle;

abstract class Observateur implements IObservateur {
	int v = 0;
	private ConcreteObserve obs;
	void setObs(ConcreteObserve o){ obs = o; }
	public void maj(){	v = obs.getV(); }

    abstract void afficher();
}
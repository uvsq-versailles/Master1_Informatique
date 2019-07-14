package fr.uvsq.pglp.kholle;

class ConcreteObserve extends Observe{
	private int v= 0;
	public int getV() {return v;}
	public void setV(int val) {
		v = val;
		notifier();
	}
}
package fr.uvsq.pglp.kholle;

import java.util.LinkedList;
import java.util.List;

abstract class Observe {
	List<IObservateur> obs = new LinkedList<IObservateur>();
	public void notifier(){
		for(IObservateur o : obs)
			o.maj();
	}
	public void ajouterObs(IObservateur o){ obs.add(o); }
	public void enleverObs(IObservateur o){ obs.remove(o); }
	abstract void setV(int v);
}
package fr.uvsq.pglp.solid.ocp;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {

	private List<Salarie> salarieList;
	
	public Entreprise() {
		salarieList = new ArrayList<Salarie>();
	}
	
	public double getSommeTotaleSalaires() {
		double sum = 0;
		for (Salarie s : salarieList) {
			sum += s.calculSalaire();
		}
		return sum;
	}
	
	
	
	
	
}

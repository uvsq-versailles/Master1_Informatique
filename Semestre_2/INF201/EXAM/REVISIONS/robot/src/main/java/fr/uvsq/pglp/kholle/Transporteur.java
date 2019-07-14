package fr.uvsq.pglp.kholle;

import java.util.ArrayList;
import java.util.List;

public class Transporteur extends Element{
	private List<Element> contenu = new ArrayList <Element>();
	
	public Transporteur(String nom, List<Option> options) {
		super(nom, options);
	}

	@Override
	public void declineIdentite() {
		System.out.println("{");
		System.out.println(nom+" TRANSPORTEUR");
		for (Element e : contenu){
			e.declineIdentite();
		}
		System.out.println("}");
	}
}

package fr.uvsq.pglp.kholle;

import java.util.ArrayList;
import java.util.List;

class Dossier extends Element {
	
	private List<Element> contenu = new ArrayList <Element>();
	
	public Dossier(String nom) {
		super(nom, 0);
	}
	
	public void add(Element e) {
		this.contenu.add(e);
		this.taille+=e.getTaille();
	}
	
	public void print(){
		System.out.println("{ "+nom+" ");
		for (Element e : contenu){
			e.print();
		}
		System.out.println("}");
	}

}
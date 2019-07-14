package fr.uvsq.pglp.kholle;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements Element {
	private List<Element> contenu = new ArrayList <Element>();
	
	public Pizza() {}
	
	public void add(Element e) {
		this.contenu.add(e);
	}
	
	@Override
	public String toString(){
		String s="[";
		for (Element e : contenu){
			s+=e.toString()+", ";
		}
		s+="]";
		return s;
	}
}

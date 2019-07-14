package fr.uvsq.pglp.kholle;

import java.util.ArrayList;
import java.util.List;

public class Preparation extends Element{
	
	private List<Element> contenu = new ArrayList <Element>();

	public Preparation(String nom) {
		super(nom, 0);
	}
	
	public void add(Element e) throws Exception{
		if (e==this) throw new Exception();
		
		else if (contient(e)==true) {
			throw new Exception();
		}
		
		else if (contient(e)==false){
			contenu.add(e);
			cout+=e.getCout();
		}
		
	}

	@Override
	public void print() {
		System.out.println("{");
		for (Element e : contenu){
			e.print();
		}
		System.out.println("}");
		
	}

	@Override
	public boolean contient(Element e) {
		if (contenu.contains(e)) {
			return true;
		}
		else {
			boolean boolr=false;
			for (int i=0;i<contenu.size();i++) {
				boolr=this.contenu.get(i).contient(e) | boolr;
			}
			return boolr;
		}
	}

}

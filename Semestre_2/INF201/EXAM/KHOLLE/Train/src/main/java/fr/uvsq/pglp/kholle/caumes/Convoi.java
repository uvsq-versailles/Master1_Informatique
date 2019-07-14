package fr.uvsq.pglp.kholle.caumes;

import java.util.ArrayList;

import java.util.List;

import fr.uvsq.pglp.kholle.caumes.exception.AjoutException;

public class Convoi extends Element{
	
	private final String destination;
	
	private List<Element> contenu = new ArrayList <Element>();

	public static class Builder{
		
		private final String nom;
		private final String destination;
		
		// optional parameters initialize with default values
		private List<Element> contenu = new ArrayList <Element>();

		private int poids=0;
		

		public Builder(String nom, String destination){
			this.nom=nom;
			this.destination=destination;
		}
		
		public Builder addBuilder(Element e) {
			contenu.add(e);
			poids+=e.getPoids();
			return this;
			
		}

		public Convoi build(){
			return new Convoi(this);
		}
	}

	private Convoi(Builder builder){
		super(builder.nom, 0);
		destination=builder.destination;
		poids=builder.poids;
		contenu=builder.contenu;
	}
	
	public List<Element> getContenu(){
		return contenu;
	}
	
	@Override
	public boolean contient(Element e) {
		if(this.contenu.contains(e)) return true;
		else { 
			boolean test=false;
			for(int i=0;i<contenu.size();i++) {
				test=this.contenu.get(i).contient(e) | test;
			}
		
			return test;
		}
	
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	public void add(Element e) {
		// pas d'ajout a lui meme
		if (e==this) throw new AjoutException();
		
		//pas d'ajout de descendance
		else if(e.contient(this)==true) {
			throw new AjoutException();
		}
				
		else if(contient(e)==false) {
			contenu.add(e);
			poids+=e.getPoids();
		}
		else{
			throw new AjoutException(); 
		}
	}
}

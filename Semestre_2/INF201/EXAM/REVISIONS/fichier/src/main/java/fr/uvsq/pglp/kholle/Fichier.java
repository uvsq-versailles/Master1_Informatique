package fr.uvsq.pglp.kholle;

import java.time.LocalDate;

class Fichier extends Element {
	//private final String nom;
	
	private final LocalDate date;
	//private final int taille;

	public static class Builder{
		private final String nom;
		
		private LocalDate date=LocalDate.now();
		private int taille=0;

		public Builder(String nom){
			this.nom=nom;
		}

		//faire la meme chose pour chaque parametre
		public Builder date(LocalDate date){
			this.date=date;
			return this;
		}
		
		public Builder taille(int taille) {
			this.taille=taille;
			return this;
		}

		public Fichier build(){
			return new Fichier(this);
		}
	}
	
	private Fichier(Builder builder){
		
		super(builder.nom, builder.taille);
		date=builder.date;
		
	}
	
	public void print(){
		System.out.println("["+nom+" "+taille+" "+date+"]");
	}
}

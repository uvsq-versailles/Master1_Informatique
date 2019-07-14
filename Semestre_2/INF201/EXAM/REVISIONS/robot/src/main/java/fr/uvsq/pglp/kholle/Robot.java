package fr.uvsq.pglp.kholle;

import java.util.ArrayList;
import java.util.List;

public class Robot extends Element{

	public static class Builder{
		private final String nom;
		
		private List<Option> options = new ArrayList<Option>();

		public Builder(String nom){
			this.nom=nom;
		}
		
		public Builder options(Option o) {
			this.options.add(o);
			return this;
		}

		public Robot build(){
			return new Robot(this);
		}
	}

	private Robot(Builder builder){
		super(builder.nom, builder.options);
	}


	public String getNom() {
		return nom;
	}

	public List<Option> getOptions() {
		return options;
	}


	@Override
	public void declineIdentite() {
		System.out.println("["+nom+"]");
		
	}
}

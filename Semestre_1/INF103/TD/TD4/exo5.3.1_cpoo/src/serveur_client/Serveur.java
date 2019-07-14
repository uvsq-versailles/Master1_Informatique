package serveur_client;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Serveur {
	
	private ArrayList <Client> liste;
	
	public Serveur () {
		this.liste=new ArrayList <Client>();
	}
	
	public boolean connecter (Client c) {
		if(this.liste.add(c)==false) return false;
		return true;
	}
	
	public void diffuser(String message) {
		ListIterator i=this.liste.listIterator();
		int ii;
		for(ii=0;ii<this.liste.size();ii++) {
			this.liste.get(ii).recevoir(message);
		}
	}

}

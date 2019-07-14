package exo3_12_1;

/**
 * 
 * @author Clement Caumes
 * @version 1.0
 * Classe ChaineCryptee qui stocke une chaine de caracteres et la cle
 * Si la chaine de caracteres est null le chiffrement de null donnera null
 *
 */
public class ChaineCryptee {
	private String enchiffre;
	private int decalage;
	
	private ChaineCryptee (String enchiffre, int decalage) {
		this.enchiffre=enchiffre;
		this.decalage=decalage;
	}
	
	public static ChaineCryptee deEnClair(String s, int decalage) {
		if(s==null) return null;
		String str="";
		int i;
		for(i=0;i<s.length();i++) {
			str+=(ChaineCryptee.decaleCaractere(s.charAt(i),-decalage));
		}
		
		ChaineCryptee c=new ChaineCryptee(str,decalage);
		return c;
		
	}
	
	public static ChaineCryptee deCryptee(String s, int decalage) {
		return new ChaineCryptee(s,decalage);
	}
	
	public String decrypte() {

		if(this.enchiffre==null) return null;
		String s="";
		int i;
		for(i=0;i<this.enchiffre.length();i++) {
			s+=(this.decaleCaractere(this.enchiffre.charAt(i),this.decalage));
		}
		
		return s;
	}
	
	public String crypte() {
		return this.enchiffre;
	}
	
	private static char decaleCaractere(char c, int decalage) {
	    return (c < 'A' || c > 'Z')? c : (char)(((c - 'A' + decalage) % 26) + 'A');
	}
	
	
	
}

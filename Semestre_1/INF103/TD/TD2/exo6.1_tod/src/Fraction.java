
public class Fraction implements java.lang.Comparable<Fraction> {
	private int numerateur;
	private int denominateur;
	
	
	
	public Fraction (int numerateur, int denominateur) {
		this.numerateur=numerateur;
		this.denominateur=denominateur;
	}
	
	public Fraction (Constante c1) {
		if(c1==Constante.ZERO) {
			this.numerateur=0;
			this.denominateur=1;
		}
		if(c1==Constante.UN) {
			this.numerateur=1;
			this.denominateur=1;
		}
	}
	
	public Fraction (int numerateur) {
		this(numerateur,1);
	}
	
	public Fraction () {
		this(0,1);
	}
	
	@Override
	public String toString() {
		return "("+this.numerateur+"/"+this.denominateur+")";
	}
	
	public double getDouble() {
		if(denominateur==0) return -1;
		else {
			return (double)this.numerateur/this.denominateur;
		}
	}
	
	public int getNumerateur() {
		return this.numerateur;
	}
	
	public int getDenominateur() {
		return this.denominateur;
	}
	
	@Override
	public int hashCode() {
		return (int)this.getDouble();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		
		if(this.getDouble()==other.getDouble()) return true;
		else return false;
	}
	
	@Override
	public int compareTo(Fraction other) {
		if(this.getDouble()>other.getDouble()) return 1;
		else if (this.getDouble()<other.getDouble()) return -1;
		else return 0;
	}
	
}

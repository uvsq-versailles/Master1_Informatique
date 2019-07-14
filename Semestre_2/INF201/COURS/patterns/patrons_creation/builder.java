/*
	Builder permet de créer une classe avec un grand nombre d'attributs et qui doit gérer un grand nombre de constructeurs. 
*/

public class StreetMap{
	private final Point origin;
	private final Point destination;

	private final Color waterColor;
	private final Color landColor;
	private final Color highTrafficColor;
	private final Color mediumTrafficColor;
	private final Color lowTrafficColor;

	public static class Builder{
		//required parameters
		private final Point origin;
		private final Point destination;
		// optional parameters initialize with default values
		private Color waterColor = Color.BLUE;
		private Color landColor = Color.RED;
		private Color highTrafficColor = Color.YELLOW;
		private Color mediumTrafficColor = Color.PURPLE;
		private Color lowTrafficColor = Color.ORANGE;

		public Builder(Point origin, Point destination){
			this.origin=origin;
			this.detination=destination;
		}

		//faire la meme chose pour chaque parametre
		public Builder waterColor(Color color){
			this.waterColor=color;
			return this;
		}

		public StreetMap build(){
			return new StreetMap(this);
		}
	}

	private StreetMap(Builder builder){
		//required parameters
		origin = builder.origin;
		destination = builder.destination;

		//optional parameters
		waterColor = builder.waterColor;
		landColor = builder.landColor;
		highTrafficColor = builder.highTrafficColor;
		mediumTrafficColor = builder.mediumTrafficColor;
		lowTrafficColor = builder.lowTrafficColor;
	}
}

public static void main(String args[]){
	StreetMap s = new StreetMap	
		.Builder(new Point(1,2), new Point(2,3))
		.landColor(Color.GREY)
		.waterColor(Color.BLACK)
		.build();
}
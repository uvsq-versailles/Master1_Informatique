Persistance
En POO, la persistance est la propriété permettant à un objet de continuer à exister après la des-
truction de son créateur.
— C’est la capacité de sauvegarder l’état des objets, i.e. les données finales de l’application
— La persistance est dite orthogonale ou transparente si la propriété est intrinsèque à l’environ-
nement d’exécution

Serialisation
La sérialisation permet la transformation d’un objet en un flux d’octets.
— Permet le stockage des objets sur disque, leur transmission par le réseau, . . .
— L’opération inverse se nomme désérialisation
— Marshalling/unmarshalling sont des concepts équivalents

public class Personne {
	private String nom;
	private int age;
	
	public Personne(String nom, int age) {/**/}
	
	@Override
	public String toString(){/**/}
}

public abstract class DAO<T>{
	protected Connection connect = /* ... */;
	
	public abstract T create(T obj);
	public abstract T find(String id);
	public abstract T update(T obj);
	public abstract void delete (T obj);
}

public class PersonneDAO extends DAO<Personne>{
	@Override
	public Personne create(Personne obj){
		try{
			PreparedStatement prepare = connect.prepareStatement(
				"INSERT INTO personnes (nom, age) VALUES (?, ?)");
			prepare.setString(1, obj.getNom());
			prepare.setLong(2, obj.getAge());
			int result = prepare.executeUpdate();
			assert result == 1;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public Personne find(String id){
		Personne p = new Personne();
		
		try{
			PreparedStatement prepare = connect.prepareStatement(
				"SELECT * FROM personnes WHERE nom = ? ");
			prepare.setString(1, id);
			int result = prepare.executeQuery();
			if(result.first()){
				p=new Personne(result.getString("nom"), result.getString("age"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return obj;
	}
}

public class DAO Factory {
	public static DAO<Personne> getPersonneDAO(){
		return new PersonneDAO();
	}
}

public class Main {
	public static void main(String[] args){
		DAO<Personne> personneDao = DAOFactory.getPersonneDAO();
		System.out.println(personneDao.find("Dupond"));
	}
}

/////////////////////////////////////////////////////////////////////:

Exemple classe immuable : 
public final class Personnel{
	private final String nom;
	private final String prenom;
}

//////////////////////////////////:
// JPA Persistance
/////////////////////////////////

@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String summary;
	private String description;
	
	public String getSummary() {return summary;}
	public void setSummary(String summary) {this.summary = summary;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Override
	public String toString(){
		return "Todo [summary=" + summary + ", description=" + description +"]"; 
	}
}

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@OneToMany(mappedBy = "task")
	private final List<Task>todos;
}

public class Main {
	private static final String PERSISTANCE_UNIT_NAME = "todos";
	private static EntityManagerFactory factory;
	
	public static void main(){
		factory = Persistance.createManagerFactory(PERSISTANCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Todo todo = new Todo();
		todo.setSummary("This is a test");
		todo.setDescription("This is a test");
		em.persist(todo);
		em.getTransaction().commit();
		em.close();
	}
}

// COURS
//////////////////////////////////

- Les patterns sont des outils supplémentaires pour réaliser une bonne conception. 
- Les principes concernent la conception des classes (SOLID), abordent la cohésion des modules, traitent du couplage entre modules
--> Les principes et les patterns de conception sont principalement liés aux dépendances.
- Les idiomes sont une construction utilisée de façon récurrente dans un langage de programmation donné pour
réaliser une tâche « simple » ex :i++

////////////////////////////////////

- ORM : Mapping objet-relationnel
- Hibernate est un ORM open source et est une implémentation de JPA
- JPA fournit un modèle de persistance pour les objetsJava (POJO) basé l’ORM (haut niveau)
- JDBC fournissent un moyen pour interfacer un programme avec un SGBD (bas niveau)
- SGBD : systeme de gestion de base de donnees qui permet de manipuler des infos dans une bdd

///////////////////////////////////

Une unité de persistance (persistence unit) configure l’accès au SGBD pour un ensemble d’entités. 
Elle est décrite dans le fichier XML META-INF/persistence.xml

///////////////////////////////////

- Marquer une entité : @Entity
- marquer une cle primaire : @Id
- avec un ORM, on gere les associations n-n avec @ManyToMany (mappedBy = "task")

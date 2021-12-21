package fr.diginamic.jdbc.entites;

/** Cette classe correspond à la structure de la table fournisseur  
 * @author Jean-Philippe FRANCISCO
 */
public class Fournisseur{
	
	/* PRIVATE FIELDS */
	
	/** Identifiant du fournisseur */
	private Integer id;
	
	/** Nom du fournisseur */
	private String nom;
	
	
	/* CONTRUCTORS */
	
	/** Constructeur de la classe Fournisseur avec l'identifiant en paramètre 
	 * @param id Identifiant du fournisseur passé en paramètre
	 */
	public Fournisseur(Integer id) {
		super();
		this.id = id;
	}
	
	/** Constructeur de la classe Fournisseur avec le nom en paramètre
	 * @param nom Nom du fournisseur passé en paramètre
	 */
	public Fournisseur(String nom) {
		super();
		this.nom = nom;
	}
	
	/** Constructeur de la classe Fournisseur avec l'identifiant et le nom en paramètres
	 * @param id Identifiant du fournisseur passé en paramètre
	 * @param nom Nom du fournisseur passé en paramètre
	 */
	public Fournisseur(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}


	/* PUBLIC GETTERS / SETTERS */

	/** Getter de l'identifiant du fournisseur
	 * @return Identifiant du fournisseur
	 */
	public Integer getId() {
		return id;
	}
	/** Setter de l'identifiant du fournisseur
	 * @param id Identifiant du fournisseur à valoriser
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter du nom du fournisseur
	 * @return Nom du fournisseur
	 */
	public String getNom() {
		return nom;
	}
	/** Setter du nom du fournisseur
	 * @param nom Nom du fournisseur à valoriser
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	/* PUBLICS METHODS */

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}
}

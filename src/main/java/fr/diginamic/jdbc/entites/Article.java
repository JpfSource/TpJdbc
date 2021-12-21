package fr.diginamic.jdbc.entites;

/** Cette classe correspond à la structure de la table article  
 * @author Jean-Philippe FRANCISCO
 */
public class Article {
	
	/* PRIVATE FIELDS */
	
	/** Identifiant de l'article */
	private Integer id;
	
	/** Référence de l'article */
	private String reference;
	
	/** Désignation de l'article */
	private String designation;

	/** Prix de l'article */
	private Double prix;
	
	/** Fournisseur de l'article */
	private Fournisseur fournisseur;
	
	
	/* CONTRUCTORS */
	
	/** Constructeur de la classe Article avec l'identifiant en paramètre 
	 * @param id Identifiant de l'article passé en paramètre
	 */
	public Article(Integer id) {
		super();
		this.id = id;
	}
	
	/** Constructeur de la classe Article avec tous les attributs en paramètres sauf l'identifiant
	 * @param reference Référence de l'article passé en paramètre
	 * @param designation Désignation de l'article passé en paramètre
	 * @param prix Prix de l'article passé en paramètre
	 * @param fournisseur Fournisseur de l'article passé en paramètre
	 */
	public Article(String reference, String designation, Double prix, Fournisseur fournisseur) {
		super();
		this.reference = reference;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}
	
	/** Constructeur de la classe Article avec tous les attributs en paramètres
	 * @param id Identifiant de l'article passé en paramètre
	 * @param reference Référence de l'article passé en paramètre
	 * @param designation Désignation de l'article passé en paramètre
	 * @param prix Prix de l'article passé en paramètre
	 * @param fournisseur Fournisseur de l'article passé en paramètre
	 */
	public Article(Integer id, String reference, String designation, Double prix, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.reference = reference;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}


	/* PUBLIC GETTERS / SETTERS */

	/** Getter de l'identifiant de l'article
	 * @return Identifiant de l'article
	 */
	public Integer getId() {
		return id;
	}
	/** Setter de l'identifiant de l'article
	 * @param id Identifiant de l'article à valoriser
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter de la Référence de l'article
	 * @return Référence de l'article
	 */
	public String getReference() {
		return reference;
	}
	/** Setter de la Référence de l'article
	 * @param reference Référence de l'article à valoriser
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/** Getter de la Désignation de l'article
	 * @return Désignation de l'article
	 */
	public String getDesignation() {
		return designation;
	}
	/** Setter de la Désignation de l'article
	 * @param designation Désignation de l'article à valoriser
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/** Getter du Prix de l'article
	 * @return Prix de l'article
	 */
	public Double getPrix() {
		return prix;
	}
	/** Setter du Prix de l'article
	 * @param prix Prix de l'article à valoriser
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/** Getter du Fournisseur de l'article
	 * @return Fournisseur de l'article
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	/** Setter du Fournisseur de l'article
	 * @param fournisseur Fournisseur de l'article à valoriser
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	
	/* PUBLICS METHODS */
	@Override
	public String toString() {
		return "Article [id=" + id + ", reference=" + reference + ", designation=" + designation + ", prix=" + prix
				+ ", fournisseur=" + fournisseur + "]";
	}
	
}

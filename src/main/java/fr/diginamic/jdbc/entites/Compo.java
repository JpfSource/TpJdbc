package fr.diginamic.jdbc.entites;

/** Cette classe correspond à la structure de la table compo  
 * @author Jean-Philippe FRANCISCO
 */
public class Compo {

	/* PRIVATE FIELDS */
	
	/** Identifiant de la compo */
	private Integer id;
	
	/** Article de la compo */
	private Article article;
	
	/** Bon de la compo */
	private Bon bon;

	/** Quantité de la compo */
	private Integer qte;
	
	
	/* CONTRUCTORS */
	
	/** Constructeur de la classe Compo avec l'identifiant en paramètre 
	 * @param id Identifiant de la compo passé en paramètre
	 */
	public Compo(Integer id) {
		super();
		this.id = id;
	}
	
	/** Constructeur de la classe Compo avec tous les attributs en paramètres sauf l'identifiant
	 * @param article Article de la compo passé en paramètre
	 * @param bon Bon de la compo passé en paramètre
	 * @param qte Quantité de la compo passé en paramètre
	 */
	public Compo(Article article, Bon bon, Integer qte) {
		super();
		this.article = article;
		this.bon = bon;
		this.qte = qte;
	}
	
	/** Constructeur de la classe Compo avec tous les attributs en paramètres
	 * @param id Identifiant de la compo passé en paramètre
	 * @param article Article de la compo passé en paramètre
	 * @param bon Bon de la compo passé en paramètre
	 * @param qte Quantité de la compo passé en paramètre
	 */
	public Compo(Integer id, Article article, Bon bon, Integer qte) {
		super();
		this.id = id;
		this.article = article;
		this.bon = bon;
		this.qte = qte;
	}


	/* PUBLIC GETTERS / SETTERS */

	/** Getter de l'identifiant de la compo
	 * @return Identifiant de la compo
	 */
	public Integer getId() {
		return id;
	}
	/** Setter de l'identifiant de la compo
	 * @param id Identifiant de la compo à valoriser
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter de l'article de la compo
	 * @return Article de la compo
	 */
	public Article getArticle() {
		return article;
	}
	/** Setter de l'article de la compo
	 * @param article Article de la compo à valoriser
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/** Getter du bon de la compo
	 * @return Bon de la compo
	 */
	public Bon getBon() {
		return bon;
	}
	/** Setter du bon de la compo
	 * @param bon Bon de la compo à valoriser
	 */
	public void setBon(Bon bon) {
		this.bon = bon;
	}

	/** Getter de la quantité de la compo
	 * @return Quantité de la compo
	 */
	public Integer getQte() {
		return qte;
	}
	/** Setter de la quantité de la compo
	 * @param qte Quantité de la compo à valoriser
	 */
	public void setQte(Integer qte) {
		this.qte = qte;
	}

	/* PUBLICS METHODS */
	@Override
	public String toString() {
		return "Compo [id=" + id + ", article=" + article + ", bon=" + bon + ", qte=" + qte + "]";
	}

}

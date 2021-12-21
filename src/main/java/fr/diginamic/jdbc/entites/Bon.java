package fr.diginamic.jdbc.entites;

import java.util.Date;

/** Cette classe correspond à la structure de la table bon  
 * @author Jean-Philippe FRANCISCO
 */
public class Bon {

	/* PRIVATE FIELDS */
	
	/** Identifiant du bon */
	private Integer id;
	
	/** Numéro du bon */
	private Integer numero;
	
	/** Date de commande du bon */
	private Date dateCmde;

	/** Délai du bon */
	private Integer delai;
	
	/** Fournisseur du bon */
	private Fournisseur fournisseur;
	
	
	/* CONTRUCTORS */
	
	/** Constructeur de la classe Bon avec l'identifiant en paramètre 
	 * @param id Identifiant du bon passé en paramètre
	 */
	public Bon(Integer id) {
		super();
		this.id = id;
	}
	
	/** Constructeur de la classe Bon avec tous les attributs en paramètres sauf l'identifiant
	 * @param numero Numéro du bon passé en paramètre
	 * @param dateCmde Date de commande du bon passé en paramètre
	 * @param delai Délai du bon passé en paramètre
	 * @param fournisseur Fournisseur du bon passé en paramètre
	 */
	public Bon(Integer numero, Date dateCmde, Integer delai, Fournisseur fournisseur) {
		super();
		this.numero = numero;
		this.dateCmde = dateCmde;
		this.delai = delai;
		this.fournisseur = fournisseur;
	}
	
	/** Constructeur de la classe Bon avec tous les attributs en paramètres
	 * @param id Identifiant du bon passé en paramètre
	 * @param numero Numéro du bon passé en paramètre
	 * @param dateCmde Date de commande du bon passé en paramètre
	 * @param delai Délai du bon passé en paramètre
	 * @param fournisseur Fournisseur du bon passé en paramètre
	 */
	public Bon(Integer id, Integer numero, Date dateCmde, Integer delai, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.numero = numero;
		this.dateCmde = dateCmde;
		this.delai = delai;
		this.fournisseur = fournisseur;
	}


	/* PUBLIC GETTERS / SETTERS */

	/** Getter de l'identifiant du bon
	 * @return Identifiant du bon
	 */
	public Integer getId() {
		return id;
	}
	/** Setter de l'identifiant du bon
	 * @param id Identifiant du bon à valoriser
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter du numéro du bon
	 * @return Numéro du bon
	 */
	public Integer getNumero() {
		return numero;
	}
	/** Setter du numéro du bon
	 * @param numero Numéro du bon à valoriser
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/** Getter de la date de commande du bon
	 * @return Date de commande du bon
	 */
	public Date getDateCmde() {
		return dateCmde;
	}
	/** Setter de la date de commande du bon
	 * @param dateCmde Date de commande du bon à valoriser
	 */
	public void setDateCmde(Date dateCmde) {
		this.dateCmde = dateCmde;
	}

	/** Getter du délai du bon
	 * @return Délai du bon
	 */
	public Integer getDelai() {
		return delai;
	}
	/** Setter du délai du bon
	 * @param delai Délai du bon à valoriser
	 */
	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	/** Getter du Fournisseur du bon
	 * @return Fournisseur du bon
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	/** Setter du Fournisseur du bon
	 * @param fournisseur Fournisseur du bon à valoriser
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	
	/* PUBLICS METHODS */
	@Override
	public String toString() {
		return "Bon [id=" + id + ", numero=" + numero + ", dateCmde=" + dateCmde + ", delai=" + delai + ", fournisseur="
				+ fournisseur + "]";
	}
}

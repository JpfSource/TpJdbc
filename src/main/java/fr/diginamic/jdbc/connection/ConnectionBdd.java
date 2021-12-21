package fr.diginamic.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * A compléter avec l'utilisation de databa.properties
 * @author chris
 *
 */
public class ConnectionBdd {

	/* CONSTANTES / REQUETES / FOURNISSEUR */

	/** Requête SQL sur la table fournisseur pour sélection de toutes les lignes */
	public static String FOURNISSEUR_EXTRACT_ALL = "Select id, nom From fournisseur";

	/** Requête SQL sur la table fournisseur pour sélection d'une seule ligne avec identifiant */
	public static String FOURNISSEUR_EXTRACT_ONE_BY_ID = "Select id, nom From fournisseur Where id = :id";
	
	/** Requête SQL sur la table fournisseur pour sélection d'une seule ligne avec tous les autres critères pour récupérer l'identifiant */
	public static String FOURNISSEUR_EXTRACT_ONE = "Select id, nom From fournisseur Where nom = :nom";

	/** Requête SQL sur la table fournisseur pour insertion d'une ligne */
	public static String FOURNISSEUR_INSERT = "Insert Into fournisseur(nom) Values(:nom)";

	/** Requête SQL sur la table fournisseur pour mise à jour d'une ligne */
	public static String FOURNISSEUR_UPDATE = "Update fournisseur Set nom = :newNom Where id = :oldId And nom = :oldNom";

	/** Requête SQL sur la table fournisseur pour suppression d'une ligne */
	public static String FOURNISSEUR_DELETE = "Delete From fournisseur Where id = :id";

	/** Requête SQL sur la table fournisseur pour suppression des lignes de la table compo lié à un fournisseur */
	public static String FOURNISSEUR_DELETE_COMPOS = "Delete From compo Where id_art in (Select a.id from article a where a.id_fou = :id) Or id_bon in (Select b.id from bon b where b.id_fou = :id)";

	/** Requête SQL sur la table fournisseur pour suppression des lignes de la table article lié à un fournisseur */
	public static String FOURNISSEUR_DELETE_ARTICLES = "Delete From article where id_fou = :id";

	/** Requête SQL sur la table fournisseur pour suppression des lignes de la table bon lié à un fournisseur */
	public static String FOURNISSEUR_DELETE_BONS = "Delete From bon Where id_fou = :id";

	
	/* CONSTANTES / REQUETES / ARTICLE */

	/** Requête SQL sur la table article pour sélection de toutes les lignes */
	public static String ARTICLE_EXTRACT_ALL = "Select id, ref, designation, prix, id_fou From article";

	/** Requête SQL sur la table article pour sélection d'une seule ligne avec identifiant */
	public static String ARTICLE_EXTRACT_ONE_BY_ID = "Select id, ref, designation, prix, id_fou From article Where id = :id";
	
	/** Requête SQL sur la table article pour sélection d'une seule ligne avec tous les autres critères pour récupérer l'identifiant */
	public static String ARTICLE_EXTRACT_ONE = "Select id, ref, designation, prix, id_fou From article Where ref = :ref And designation = :designation And prix = :prix And id_fou = :id_fou";

	/** Requête SQL sur la table article pour insertion d'une ligne */
	public static String ARTICLE_INSERT = "Insert Into article(ref, designation, prix, id_fou) Values(:ref, :designation, :prix, :id_fou)";

	/** Requête SQL sur la table article pour mise à jour d'une ligne */
	public static String ARTICLE_UPDATE = "Update article Set ref = :newRef, designation = :newDesignation, prix = :newPrix, id_fou = :newIdFou Where id = :oldId And ref = :oldRef And designation = :oldDesignation And prix = :oldPrix And id_fou = :oldIdFou";

	/** Requête SQL sur la table article pour suppression d'une ligne */
	public static String ARTICLE_DELETE = "Delete From article Where id = :id";

	/** Requête SQL sur la table article pour suppression des lignes de la table compo lié à un article */
	public static String ARTICLE_DELETE_COMPOS = "Delete From compo Where id_art = :id";
	
	
	/* CONSTANTES / REQUETES / BON */
	
	/** Requête SQL sur la table bon pour sélection de toutes les lignes */
	public static String BON_EXTRACT_ALL = "Select id, numero, date_cmde, delai, id_fou From bon";
	
	/** Requête SQL sur la table bon pour sélection d'une seule ligne avec identifiant */
	public static String BON_EXTRACT_ONE_BY_ID = "Select id, numero, date_cmde, delai, id_fou From bon Where id = :id";
	
	/** Requête SQL sur la table bon pour sélection d'une seule ligne avec tous les autres critères pour récupérer l'identifiant */
	public static String BON_EXTRACT_ONE = "Select id, numero, date_cmde, delai, id_fou From bon Where numero = :numero And date_cmde = :date_cmde And delai = :delai And id_fou = :id_fou";

	/** Requête SQL sur la table bon pour insertion d'une ligne */
	public static String BON_INSERT = "Insert Into bon(numero, date_cmde, delai, id_fou) Values(:numero, :date_cmde, :delai, :id_fou)";

	/** Requête SQL sur la table bon pour mise à jour d'une ligne */
	public static String BON_UPDATE = "Update bon Set numero = :newNumero, date_cmde = :newDateCmde, delai = :newDelai, id_fou = :newIdFou Where id = :oldId And numero = :oldNumero And date_cmde = :oldDateCmde And delai = :oldDelai And id_fou = :oldIdFou";

	/** Requête SQL sur la table bon pour suppression d'une ligne */
	public static String BON_DELETE = "Delete From bon Where id = :id";

	/** Requête SQL sur la table bon pour suppression des lignes de la table compo lié à un bon */
	public static String BON_DELETE_COMPOS = "Delete From compo Where id_bon = :id";


	/* CONSTANTES / REQUETES / COMPO */
	
	/** Requête SQL sur la table compo pour sélection de toutes les lignes */
	public static String COMPO_EXTRACT_ALL = "Select id, id_art, id_bon, qte From compo";

	/** Requête SQL sur la table compo pour sélection des compos liées à un fournisseur */
	public static String COMPO_EXTRACT_ALL_BY_FOUR = "Select c.id, c.id_art, c.id_bon, c.qte From compo c Join article a On c.id_art = a.id Where a.id_fou = :id UNION Select c.id, c.id_art, c.id_bon, c.qte From compo c Join bon b On c.id_bon = b.id Where b.id_fou = :id";

	/** Requête SQL sur la table compo pour sélection d'une seule ligne avec identifiant */
	public static String COMPO_EXTRACT_ONE_BY_ID = "Select id, id_art, id_bon, qte From compo Where id = :id";
	
	/** Requête SQL sur la table compo pour sélection d'une seule ligne avec tous les autres critères pour récupérer l'identifiant */
	public static String COMPO_EXTRACT_ONE = "Select id, id_art, id_bon, qte From compo Where id_art = :idArt And idBon = :id_bon And qte = :qte";

	/** Requête SQL sur la table compo pour insertion d'une ligne */
	public static String COMPO_INSERT = "Insert Into compo(id_art, id_bon, qte) Values(:idArt, :idBon, :qte)";

	/** Requête SQL sur la table compo pour mise à jour d'une ligne */
	public static String COMPO_UPDATE = "Update compo Set id_art = :newIdArt, id_bon = :newIdBon, qte = :newQte Where id = :oldId And id_art = :oldIdArt And id_bon = :oldIdBon And qte = :oldQte";

	/** Requête SQL sur la table compo pour suppression d'une ligne */
	public static String COMPO_DELETE = "Delete From compo Where id = :id";

	
	/* PUBLIC METHODS */
	
	/** Cette méthode récupère la connection grâce au property file (ici par défaut)
	 * @return Connection ouverte sur la base de données
	 * @throws ClassNotFoundException Exception levée sur récupération de la Connection
	 * @throws SQLException Exception levée sur récupération de la Connection
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return getConnection("database");
	}

	/** Cette méthode récupère la connection grâce au property file
	 * @param propertyFile Property file contenant les paramètres d'accès à la base de donnée
	 * @return Connection ouverte sur la base de données
	 * @throws ClassNotFoundException Exception levée sur récupération de la Connection
	 * @throws SQLException Exception levée sur récupération de la Connection
	 */
	public static Connection getConnection(String propertyFile) throws ClassNotFoundException, SQLException {
		ResourceBundle parmDB = ResourceBundle.getBundle(propertyFile);
		Class.forName(parmDB.getString("database.driver"));
		return DriverManager.getConnection(parmDB.getString("database.url"), parmDB.getString("database.user"), parmDB.getString("database.password"));
	}

}

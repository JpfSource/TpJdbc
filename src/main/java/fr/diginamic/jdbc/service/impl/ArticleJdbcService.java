package fr.diginamic.jdbc.service.impl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.diginamic.jdbc.dao.impl.ArticleJdbcDao;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.exceptions.InvalidArticleException;
import fr.diginamic.jdbc.exceptions.InvalidFournisseurException;

/** Cette classe contient l'ensemble des méthodes métiers à appliquer dans la gestion des articles.<br>
 * Elle fait le lien entre les choix de l'utilisateur et l'accès aux données de la table article. 
 * @author Jean-Philippe FRANCISCO
 */
public class ArticleJdbcService {
	
	
	/* CONSTANTES */

	// CONSTANTES / Titre du sous-menu sélectionné par l'utilisateur
	/** Titre pour le sous-menu {@value #CREER_ARTICLE_LIB} */
	private final static String CREER_ARTICLE_LIB			= "Créer un article :";

	/** Titre pour le sous-menu {@value #MODIFIER_ARTICLE_LIB} */
	private final static String MODIFIER_ARTICLE_LIB		= "Modifier un article :";
	
	/** Titre pour le sous-menu {@value #SUPPRIMER_ARTICLE_LIB} */
	private final static String SUPPRIMER_ARTICLE_LIB		= "Supprimer un article :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_TOUS_ARTICLES_LIB} */
	private final static String AFFICHER_TOUS_ARTICLES_LIB	= "Afficher tous les articles :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_UN_ARTICLE_LIB} */
	private final static String AFFICHER_UN_ARTICLE_LIB		= "Afficher un article :";
	
	
	/* PRIVATE FIELDS */
	
	/** Instance de la classe permettant d'accéder à la table article */
	private static ArticleJdbcDao adj = new ArticleJdbcDao();
	
	
	/* PUBLICS METHODS */
	
	/** Cette méthode permet de traiter l'action {@value #CREER_ARTICLE_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	public static void creerArticle(Scanner in) throws InvalidArticleException {
		System.out.println("");
		System.out.println(CREER_ARTICLE_LIB);
		System.out.print("-> Quelle est la référence du nouvel article = ");
		String ref = in.nextLine();
		System.out.print("-> Quelle est la désignation du nouvel article = ");
		String designation = in.nextLine();
		System.out.print("-> Quel est le prix du nouvel article = ");
		String prix = in.nextLine();
		System.out.print("-> Quel est l'identifiant du fournisseur de ce nouvel article = ");
		ArticleJdbcService.creer(ref, designation, prix, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #MODIFIER_ARTICLE_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	public static void modifierArticle(Scanner in) throws InvalidArticleException {
		System.out.println("");
		System.out.println(MODIFIER_ARTICLE_LIB);
		System.out.print("-> Quel est l'identifiant de l'article à modifier = ");
		String id = in.nextLine();
		System.out.print("-> Quelle est la nouvelle référence de l'article (ne rien saisir si inchangé) = ");
		String ref = in.nextLine();
		System.out.print("-> Quelle est la nouvelle désignation de l'article (ne rien saisir si inchangé) = ");
		String designation = in.nextLine();
		System.out.print("-> Quel est le nouveau prix de l'article (ne rien saisir si inchangé) = ");
		String prix = in.nextLine();
		System.out.print("-> Quel est le nouvel identifiant du fournisseur de l'article (ne rien saisir si inchangé) = ");
		ArticleJdbcService.modifier(id, ref, designation, prix, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #SUPPRIMER_ARTICLE_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	public static void supprimerArticle(Scanner in) throws InvalidArticleException {
		System.out.println("");
		System.out.println(SUPPRIMER_ARTICLE_LIB);
		System.out.print("-> Quel est l'identifiant de l'article à supprimer = ");
		String id = in.nextLine();
		afficheImpactSuppression(id);
		System.out.print("-> Confirmez-vous la suppression de l'article n°"+ id +" (O/N) = ");
		if(in.nextLine().toUpperCase().equals("O")) {
			ArticleJdbcService.supprimer(id);
		}
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_TOUS_ARTICLES_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - La liste de tous les articles est affichée
	 */
	public static void afficherTousArticles() {
		System.out.println("");
		System.out.println(AFFICHER_TOUS_ARTICLES_LIB);
		ArticleJdbcService.afficherTous();
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_UN_ARTICLE_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	public static void afficherUnArticle(Scanner in) throws InvalidArticleException {
		System.out.println("");
		System.out.println(AFFICHER_UN_ARTICLE_LIB);
		System.out.print("-> Quel est l'identifiant de l'article à afficher = ");
		ArticleJdbcService.afficherUn(in.nextLine());
	}
	
	/** Cette méthode retourne l'article dont l'identifiant a été passé en paramètre
	 * @param id Identifiant de l'article souhaité
	 * @return Article souhaité
	 */
	public static Article extractOneById(Integer id) {
		return adj.extractOneById(id);
	}
		
	/** Cette méthode retourne la liste des articles appartenant au fournisseur dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du fournisseur souhaité
	 * @return Liste des articles du fournisseur souhaité
	 */
	public static List<Article> extractAllByFour(Integer id) {
		return adj.extractAll().stream().filter(a -> a.getFournisseur().getId() == id).collect(Collectors.toList());
	}

	/** Cette méthode contrôle l'identifiant saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param idToCheck Identifiant à contrôler 
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	public static void checkId(String idToCheck) throws InvalidArticleException {
		if(idToCheck == null || idToCheck.trim().equals("")) {
			throw new InvalidArticleException("KO : L'identifiant de l'article est obligatoire !\n");
		}
		else {
			try {
				Integer id = Integer.parseInt(idToCheck);
				
				if(!adj.isExist(id)) {
					throw new InvalidArticleException("KO : L'identifiant de l'article n'existe pas dans la table !\n");
				}
			}
			catch(NumberFormatException e) {
				throw new InvalidArticleException("KO : L'identifiant de l'article doit être un entier numérique !\n");
			}
		}
	}
	
	
	/* PRIVATE METHODS */

	/** Cette méthode contrôle les données obligatoires de l'article saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param refToCheck Référence de l'article à contrôler
	 * @param designationToCheck Désignation de l'article à contrôler
	 * @param prixToCheck Prix de l'article à contrôler
 	 * @param idFournisseurToCheck Identifiant du fournisseur de l'article à contrôler
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkData(String refToCheck, String designationToCheck, String prixToCheck, String idFournisseurToCheck) throws InvalidArticleException {
		checkData(true, refToCheck, designationToCheck, prixToCheck, idFournisseurToCheck);
	}

	/** Cette méthode contrôle les données de l'article saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param isObligatoire Booléen pour indiquer si toutes les données sont obligatoires (true) ou facultatif (false)
	 * @param refToCheck Référence de l'article à contrôler
	 * @param designationToCheck Désignation de l'article à contrôler
	 * @param prixToCheck Prix de l'article à contrôler
 	 * @param idFournisseurToCheck Identifiant du fournisseur de l'article à contrôler
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkData(Boolean isObligatoire, String refToCheck, String designationToCheck, String prixToCheck, String idFournisseurToCheck) throws InvalidArticleException {
		String msg = "";
		if(refToCheck == null || refToCheck.trim().equals("")) {
			if(isObligatoire) {
				msg += "KO : La référence de l'article est obligatoire !\n";
			}
		}
		else if (refToCheck.trim().length() > 13) {
			msg += "KO : La référence de l'article ne doit pas dépasser 13 caractères !\n";
		}
		if(designationToCheck == null || designationToCheck.trim().equals("")) {
			if(isObligatoire) {
				msg += "KO : La désignation de l'article est obligatoire !\n";
			}
		}
		else if (designationToCheck.trim().length() > 255) {
			msg += "KO : La désignation de l'article ne doit pas dépasser 255 caractères !\n";
		}
		if(prixToCheck == null || prixToCheck.trim().equals("")) {
			if(isObligatoire) {
				msg += "KO : Le prix de l'article est obligatoire !\n";
			}
		}
		else {
			try {
				Double.parseDouble(prixToCheck);
			}
			catch(NumberFormatException e) {
				msg += "KO : Le prix de l'article doit être un numérique décimal !\n";
			}
		}
		if(idFournisseurToCheck == null || idFournisseurToCheck.trim().equals("")) {
			if(isObligatoire) {
				msg += "KO : L'identifiant du fournisseur de l'article est obligatoire !\n";
			}
		}
		else {
			try {
				FournisseurJdbcService.checkId(idFournisseurToCheck);
			} catch (InvalidFournisseurException e) {
				msg += e.getMessage()+"\n";
			}
		}
		if(!msg.equals("")) {
			throw new InvalidArticleException(msg);			
		}
	}

	/** Cette méthode permet de créer un nouveau article dans la table article.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param ref Référence du nouvel article
	 * @param designation Désignation du nouvel article
	 * @param prix Prix du nouvel article
	 * @param idFournisseur Identifiant du fournisseur du nouvel article
	 * @return Identifiant de l'article créé
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static Integer creer(String ref, String designation, String prix, String idFournisseur) throws InvalidArticleException {
		Integer idCree = null;
		checkData(ref, designation, prix, idFournisseur);
		Article newarticle = adj.insert(new Article(ref, designation, Double.parseDouble(prix), FournisseurJdbcService.extractOneById(Integer.parseInt(idFournisseur))));
		if(newarticle != null) {
			idCree = newarticle.getId();
			System.out.println("OK : Nouvel article n°"+ idCree +" créé !\n");
		}
		return idCree;
	}
	
	/** Cette méthode permet de modifier un article existant dans la table article.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant de l'article à modifier
	 * @param newRef Nouvelle référence de l'article à modifier
	 * @param newDesignation Nouvelle désignation de l'article à modifier
	 * @param newPrix Nouveau prix de l'article à modifier
	 * @param newIdFournisseur Nouvel identifiant du fournisseur de l'article à modifier
	 * @return Booléen true/false pour indiquer si la modification est effective
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static Boolean modifier(String id, String newRef, String newDesignation, String newPrix, String newIdFournisseur) throws InvalidArticleException {
		Boolean rc = false;
		checkId(id);
		checkData(false, newRef, newDesignation, newPrix, newIdFournisseur);
		Article oldArticle = extractOneById(Integer.parseInt(id));
		Article newArticle = new Article( oldArticle.getId()
				                        , (newRef != null && !newRef.trim().equals("") ? newRef : oldArticle.getReference())
				                        , (newDesignation != null && !newDesignation.trim().equals("") ? newDesignation : oldArticle.getDesignation())
				                        , (newPrix != null && !newPrix.trim().equals("") ? Double.parseDouble(newPrix) : oldArticle.getPrix())
				                        , (newIdFournisseur != null && !newIdFournisseur.trim().equals("") ? FournisseurJdbcService.extractOneById(Integer.parseInt(newIdFournisseur)) : oldArticle.getFournisseur()));
		
		if(adj.update(oldArticle, newArticle)) {
			System.out.println("OK : Article n°"+ id +" modifié !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher les impacts liés à la suppression de l'article
	 * @param id Identifiant de l'article à supprimer
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static void afficheImpactSuppression(String id)  throws InvalidArticleException {
		checkId(id);
		Integer nbCompos = CompoJdbcService.extractAllByArt(Integer.parseInt(id)).size();
		if(nbCompos > 0) {
			System.out.println("La suppression de cet article aura pour conséquence de supprimer "+ nbCompos +" promo"+ (nbCompos > 1 ? "s" : ""));
		}
	}
	
	/** Cette méthode permet de supprimer un article existant dans la table article.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant du article à supprimer
	 * @return Booléen true/false pour indiquer si la suppression est effective
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static Boolean supprimer(String id) throws InvalidArticleException {
		Boolean rc = false;
		checkId(id);
		if(adj.delete(new Article(Integer.parseInt(id)))) {
			System.out.println("OK : Article n°"+ id +" supprimé !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher dans la console tous les articles de la table article
	 */
	private static void afficherTous() {
		adj.extractAll().stream().forEach(article -> System.out.println(article));
	}

	/** Cette méthode permet d'afficher un article existant dans la table article.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors le article sélectionné sera affiché dans la console.
	 * @param id Identifiant du article à afficher
	 * @throws InvalidArticleException Exception levée lorsqu'une erreur est détectée
	 */
	private static void afficherUn(String id) throws InvalidArticleException {
		checkId(id);
		System.out.println(extractOneById(Integer.parseInt(id)));
	}
}

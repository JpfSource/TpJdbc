package fr.diginamic.jdbc.service.impl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.diginamic.jdbc.dao.impl.CompoJdbcDao;
import fr.diginamic.jdbc.entites.Compo;
import fr.diginamic.jdbc.exceptions.InvalidCompoException;
import fr.diginamic.jdbc.exceptions.InvalidArticleException;
import fr.diginamic.jdbc.exceptions.InvalidBonException;


/** Cette classe contient l'ensemble des méthodes métiers à appliquer dans la gestion des compos.<br>
 * Elle fait le lien entre les choix de l'utilisateur et l'accès aux données de la table compo. 
 * @author Jean-Philippe FRANCISCO
 */
public class CompoJdbcService {
	
	
	/* CONSTANTES */

	// CONSTANTES / Titre du sous-menu sélectionné par l'utilisateur
	/** Titre pour le sous-menu {@value #CREER_COMPO_LIB} */
	private final static String CREER_COMPO_LIB				= "Créer une promo :";

	/** Titre pour le sous-menu {@value #MODIFIER_COMPO_LIB} */
	private final static String MODIFIER_COMPO_LIB			= "Modifier une promo :";
	
	/** Titre pour le sous-menu {@value #SUPPRIMER_COMPO_LIB} */
	private final static String SUPPRIMER_COMPO_LIB			= "Supprimer une promo :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_TOUS_COMPOS_LIB} */
	private final static String AFFICHER_TOUS_COMPOS_LIB	= "Afficher toutes les promos :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_UN_COMPO_LIB} */
	private final static String AFFICHER_UN_COMPO_LIB		= "Afficher une promo :";
	
	
	/* PRIVATE FIELDS */
	
	/** Instance de la classe permettant d'accéder à la table compo */
	private static CompoJdbcDao cdj = new CompoJdbcDao();
	
	
	/* PUBLICS METHODS */
	
	/** Cette méthode permet de traiter l'action {@value #CREER_COMPO_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void creerCompo(Scanner in) throws InvalidCompoException {
		System.out.println("");
		System.out.println(CREER_COMPO_LIB);
		System.out.print("-> Quel est l'identifiant de l'article de cette nouvelle promo = ");
		String idArt = in.nextLine();
		System.out.print("-> Quel est l'identifiant du bon de cette nouvelle promo = ");
		String idBon = in.nextLine();
		System.out.print("-> Quelle est la quantité de la nouvelle promo = ");
		CompoJdbcService.creer(idArt, idBon, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #MODIFIER_COMPO_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void modifierCompo(Scanner in) throws InvalidCompoException {
		System.out.println("");
		System.out.println(MODIFIER_COMPO_LIB);
		System.out.print("-> Quel est l'identifiant de la promo à modifier = ");
		String id = in.nextLine();
		System.out.print("-> Quel est le nouvel identifiant de l'article de cette promo (ne rien saisir si inchangé) = ");
		String idArt = in.nextLine();
		System.out.print("-> Quel est le nouvel identifiant du bon de cette promo (ne rien saisir si inchangé) = ");
		String idBon = in.nextLine();
		System.out.print("-> Quelle est la nouvelle quantité de cette promo (ne rien saisir si inchangé) = ");
		CompoJdbcService.modifier(id, idArt, idBon, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #SUPPRIMER_COMPO_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void supprimerCompo(Scanner in) throws InvalidCompoException {
		System.out.println("");
		System.out.println(SUPPRIMER_COMPO_LIB);
		System.out.print("-> Quel est l'identifiant de la promo à supprimer = ");
		String id = in.nextLine();
		System.out.print("-> Confirmez-vous la suppression de la promo n°"+ id +" (O/N) = ");
		if(in.nextLine().toUpperCase().equals("O")) {
			CompoJdbcService.supprimer(id);
		}
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_TOUS_COMPOS_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - La liste de tous les compos est affichée
	 */
	public static void afficherTousCompos() {
		System.out.println("");
		System.out.println(AFFICHER_TOUS_COMPOS_LIB);
		CompoJdbcService.afficherTous();
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_UN_COMPO_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void afficherUnCompo(Scanner in) throws InvalidCompoException {
		System.out.println("");
		System.out.println(AFFICHER_UN_COMPO_LIB);
		System.out.print("-> Quel est l'identifiant de la promo à afficher = ");
		CompoJdbcService.afficherUn(in.nextLine());
	}
	
	/** Cette méthode retourne la compo dont l'identifiant a été passé en paramètre
	 * @param id Identifiant de la compo souhaité
	 * @return compo souhaité
	 */
	public static Compo extractOneById(Integer id) {
		return cdj.extractOneById(id);
	}
		
	/** Cette méthode retourne la liste des compos appartenant à l'article dont l'identifiant a été passé en paramètre
	 * @param id Identifiant de l'article souhaité
	 * @return Liste des compos de l'article souhaité
	 */
	public static List<Compo> extractAllByArt(Integer id) {
		return cdj.extractAll().stream().filter(c -> c.getArticle().getId() == id).collect(Collectors.toList());
	}

	/** Cette méthode retourne la liste des compos appartenant au bon dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du bon souhaité
	 * @return Liste des compos du bon souhaité
	 */
	public static List<Compo> extractAllByBon(Integer id) {
		return cdj.extractAll().stream().filter(c -> c.getBon().getId() == id).collect(Collectors.toList());
	}

	/** Cette méthode retourne la liste des compos appartenant au fournisseur dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du fournisseur souhaité
	 * @return Liste des compos du fournisseur souhaité
	 */
	public static List<Compo> extractAllByFour(Integer id) {
		return cdj.extractAllByFour(id);
	}
	
	/** Cette méthode contrôle l'identifiant saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param idToCheck Identifiant à contrôler 
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée
	 */
	public static void checkId(String idToCheck) throws InvalidCompoException {
		if(idToCheck == null || idToCheck.trim().equals("")) {
			throw new InvalidCompoException("KO : L'identifiant de la promo est obligatoire !\n");
		}
		else {
			try {
				Integer id = Integer.parseInt(idToCheck);
				
				if(!cdj.isExist(id)) {
					throw new InvalidCompoException("KO : L'identifiant de la promo n'existe pas dans la table !\n");
				}
			}
			catch(NumberFormatException e) {
				throw new InvalidCompoException("KO : L'identifiant de la promo doit être un entier numérique !\n");
			}
		}
	}
	
	
	/* PRIVATE METHODS */

	/** Cette méthode contrôle les données de la compo saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param idArtToCheck Identifiant de l'article de la compo à contrôler 
	 * @param idBonToCheck Identifiant du bon de la compo à contrôler 
	 * @param qteToCheck Quantité de la compo à contrôler
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkData(String idArtToCheck, String idBonToCheck, String qteToCheck) throws InvalidCompoException {
		String msg = "";

		if(idArtToCheck != null && !idArtToCheck.trim().equals("")) {
			try {
				ArticleJdbcService.checkId(idArtToCheck);
			} catch (InvalidArticleException e) {
				msg += e.getMessage()+"\n";
			}
		}
		if(idBonToCheck != null && !idBonToCheck.trim().equals("")) {
			try {
				BonJdbcService.checkId(idBonToCheck);
			} catch (InvalidBonException e) {
				msg += e.getMessage()+"\n";
			}
		}
		if(qteToCheck != null && !qteToCheck.trim().equals("")) {
			try {
				Integer.parseInt(qteToCheck);
			}
			catch(NumberFormatException e) {
				msg += "KO : La quantité de la promo doit être un entier numérique !\n";
			}
		}
		if(!msg.equals("")) {
			throw new InvalidCompoException(msg);			
		}
	}

	/** Cette méthode permet de créer une nouvelle compo dans la table compo.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param idArt Identifiant de l'article de la nouvelle compo
	 * @param idBon Identifiant du bon de la nouvelle compo
	 * @param qte Quantité de la nouvelle compo
	 * @return Identifiant de la compo créé
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Integer creer(String idArt, String idBon, String qte) throws InvalidCompoException {
		Integer idCree = null;
		
		checkData(idArt, idBon, qte);
		Compo newcompo = cdj.insert(new Compo( ArticleJdbcService.extractOneById(Integer.parseInt(idArt))
				                             , BonJdbcService.extractOneById(Integer.parseInt(idBon))
				                             , (qte != null && !qte.trim().equals("") ? Integer.parseInt(qte) : null)));
		if(newcompo != null) {
			idCree = newcompo.getId();
			System.out.println("OK : Nouvelle promo n°"+ idCree +" créé !\n");
		}
		return idCree;
	}
	
	/** Cette méthode permet de modifier un compo existant dans la table compo.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant de la compo à modifier
	 * @param newIdArt Nouvel identifiant de l'article de la compo à modifier
	 * @param newIdBon Nouvel identifiant du bon de la compo à modifier
	 * @param newQte Nouvelle quantité de la compo à modifier
	 * @return Booléen true/false pour indiquer si la modification est effective
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Boolean modifier(String id, String newIdArt, String newIdBon, String newQte) throws InvalidCompoException {
		Boolean rc = false;
		checkId(id);
		checkData(newIdArt, newIdBon, newQte);
		
		Compo oldCompo = extractOneById(Integer.parseInt(id));
		Compo newCompo = new Compo( oldCompo.getId()
				                  , (newIdArt != null && !newIdArt.trim().equals("") ? ArticleJdbcService.extractOneById(Integer.parseInt(newIdArt)) : oldCompo.getArticle())
				                  , (newIdBon != null && !newIdBon.trim().equals("") ? BonJdbcService.extractOneById(Integer.parseInt(newIdBon)) : oldCompo.getBon())
				                  , (newQte != null && !newQte.trim().equals("") ? Integer.parseInt(newQte) : oldCompo.getQte()));
		if(cdj.update(oldCompo, newCompo)) {
			System.out.println("OK : Promo n°"+ id +" modifié !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet de supprimer une compo existante dans la table compo.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant de la compo à supprimer
	 * @return Booléen true/false pour indiquer si la suppression est effective
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Boolean supprimer(String id) throws InvalidCompoException {
		Boolean rc = false;
		checkId(id);
		if(cdj.delete(new Compo(Integer.parseInt(id)))) {
			System.out.println("OK : Promo n°"+ id +" supprimé !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher dans la console toutes les compos de la table compo
	 */
	private static void afficherTous() {
		cdj.extractAll().stream().forEach(compo -> System.out.println(compo));
	}

	/** Cette méthode permet d'afficher une compo existante dans la table compo.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors la compo sélectionné sera affiché dans la console.
	 * @param id Identifiant de la compo à afficher
	 * @throws InvalidCompoException Exception levée lorsqu'une erreur est détectée 
	 */
	private static void afficherUn(String id) throws InvalidCompoException {
		checkId(id);
		System.out.println(extractOneById(Integer.parseInt(id)));
	}
}

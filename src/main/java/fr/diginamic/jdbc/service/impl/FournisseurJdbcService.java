package fr.diginamic.jdbc.service.impl;

import java.util.Scanner;
import fr.diginamic.jdbc.dao.impl.FournisseurJdbcDao;
import fr.diginamic.jdbc.entites.Fournisseur;
import fr.diginamic.jdbc.exceptions.InvalidFournisseurException;

/** Cette classe contient l'ensemble des méthodes métiers à appliquer dans la gestion des fournisseurs.<br>
 * Elle fait le lien entre les choix de l'utilisateur et l'accès aux données de la table fournisseur. 
 * @author Jean-Philippe FRANCISCO
 */
public class FournisseurJdbcService {
	
	
	/* CONSTANTES */

	// CONSTANTES / Titre du sous-menu sélectionné par l'utilisateur
	/** Titre pour le sous-menu {@value #CREER_FOURNISSEUR_LIB} */
	private final static String CREER_FOURNISSEUR_LIB			= "Créer un fournisseur :";

	/** Titre pour le sous-menu {@value #MODIFIER_FOURNISSEUR_LIB} */
	private final static String MODIFIER_FOURNISSEUR_LIB		= "Modifier un fournisseur :";
	
	/** Titre pour le sous-menu {@value #SUPPRIMER_FOURNISSEUR_LIB} */
	private final static String SUPPRIMER_FOURNISSEUR_LIB		= "Supprimer un fournisseur :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_TOUS_FOURNISSEURS_LIB} */
	private final static String AFFICHER_TOUS_FOURNISSEURS_LIB	= "Afficher tous les fournisseurs :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_UN_FOURNISSEUR_LIB} */
	private final static String AFFICHER_UN_FOURNISSEUR_LIB		= "Afficher un fournisseur :";
	
	
	/* PRIVATE FIELDS */
	
	/** Instance de la classe permettant d'accéder à la table fournisseur */
	private static FournisseurJdbcDao fdj = new FournisseurJdbcDao();
	
	
	/* PUBLICS METHODS */
	
	/** Cette méthode permet de traiter l'action {@value #CREER_FOURNISSEUR_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	public static void creerFournisseur(Scanner in) throws InvalidFournisseurException {
		System.out.println("");
		System.out.println(CREER_FOURNISSEUR_LIB);
		System.out.print("-> Quel est le nom du nouveau fournisseur = ");
		creer(in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #MODIFIER_FOURNISSEUR_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	public static void modifierFournisseur(Scanner in) throws InvalidFournisseurException {
		System.out.println("");
		System.out.println(MODIFIER_FOURNISSEUR_LIB);
		System.out.print("-> Quel est l'identifiant du fournisseur à modifier = ");
		String id = in.nextLine();
		System.out.print("-> Quel est le nouveau nom du fournisseur = ");
		modifier(id, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #SUPPRIMER_FOURNISSEUR_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	public static void supprimerFournisseur(Scanner in) throws InvalidFournisseurException {
		System.out.println("");
		System.out.println(SUPPRIMER_FOURNISSEUR_LIB);
		System.out.print("-> Quel est l'identifiant du fournisseur à supprimer = ");
		String id = in.nextLine();
		afficheImpactSuppression(id);
		System.out.print("-> Confirmez-vous la suppression du fournisseur n°"+ id +" (O/N) = ");
		if(in.nextLine().toUpperCase().equals("O")) {
			supprimer(id);
		}
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_TOUS_FOURNISSEURS_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - La liste de tous les fournisseurs est affichée
	 */
	public static void afficherTousFournisseurs() {
		System.out.println("");
		System.out.println(AFFICHER_TOUS_FOURNISSEURS_LIB);
		afficherTous();
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_UN_FOURNISSEUR_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, une exception est renvoyée au programme appelant<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	public static void afficherUnFournisseur(Scanner in) throws InvalidFournisseurException {
		System.out.println("");
		System.out.println(AFFICHER_UN_FOURNISSEUR_LIB);
		System.out.print("-> Quel est l'identifiant du fournisseur à afficher = ");
		afficherUn(in.nextLine());
	}
	
	/** Cette méthode retourne le fournisseur dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du fournisseur souhaité
	 * @return Fournisseur souhaité
	 */
	public static Fournisseur extractOneById(Integer id) {
		return fdj.extractOneById(id);
	}
	
	/** Cette méthode contrôle l'identifiant saisi par l'utilisateur dans la console (au format String) :<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param idToCheck Identifiant à contrôler 
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	public static void checkId(String idToCheck) throws InvalidFournisseurException {
		if(idToCheck == null || idToCheck.trim().equals("")) {
			throw new InvalidFournisseurException("KO : L'identifiant du fournisseur est obligatoire !\n");
		}
		else {
			try {
				Integer id = Integer.parseInt(idToCheck);
				if(!fdj.isExist(id)) {
					throw new InvalidFournisseurException("KO : L'identifiant du fournisseur n'existe pas dans la table !\n");
				}
			}
			catch(NumberFormatException e) {
				throw new InvalidFournisseurException("KO : L'identifiant du fournisseur doit être un entier numérique !\n");
			}
		}
	}
	
	
	/* PRIVATE METHODS */

	/** Cette méthode contrôle le nom du fournisseur saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param nomToCheck Nom du fournisseur à contrôler 
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkNom(String nomToCheck) throws InvalidFournisseurException {
		if(nomToCheck == null || nomToCheck.trim().equals("")) {
			throw new InvalidFournisseurException("KO : Le nom du fournisseur est obligatoire !\n");
		}
		else if (nomToCheck.trim().length() > 25) {
			throw new InvalidFournisseurException("KO : Le nom du fournisseur ne doit pas dépasser 25 caractères !\n");
		}
	}

	/** Cette méthode permet de créer un nouveau fournisseur dans la table fournisseur.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param nom Nom du nouveau fournisseur
	 * @return Identifiant du fournisseur créé
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	private static Integer creer(String nom) throws InvalidFournisseurException  {
		Integer idCree = null;
		checkNom(nom);
		Fournisseur newfournisseur = fdj.insert(new Fournisseur(nom));
		if(newfournisseur != null) {
			idCree = newfournisseur.getId();
			System.out.println("OK : Nouveau fournisseur n°"+ idCree +" créé !\n");
		}
		return idCree;
	}
	
	/** Cette méthode permet de modifier un fournisseur existant dans la table fournisseur.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant du fournisseur à modifier
	 * @param newNom Nouveau nom du fournisseur
	 * @return Booléen true/false pour indiquer si la modification est effective
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	private static Boolean modifier(String id, String newNom) throws InvalidFournisseurException {
		Boolean rc = false;
		checkId(id);
		checkNom(newNom);
		Fournisseur oldFournisseur = extractOneById(Integer.parseInt(id));
		if(fdj.update(oldFournisseur, new Fournisseur(oldFournisseur.getId(), newNom))) {
			System.out.println("OK : Fournisseur n°"+ id +" modifié !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher les impacts liés à la suppression du fournisseur
	 * @param id Identifiant du fournisseur à supprimer
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	private static void afficheImpactSuppression(String id) throws InvalidFournisseurException {
		checkId(id);
		
		Integer nbArticles = ArticleJdbcService.extractAllByFour(Integer.parseInt(id)).size();
		Integer nbBons = BonJdbcService.extractAllByFour(Integer.parseInt(id)).size();
		Integer nbCompos = CompoJdbcService.extractAllByFour(Integer.parseInt(id)).size();
		if(nbArticles + nbBons + nbCompos > 0) {
			System.out.println("La suppression de ce fournisseur aura pour conséquence de :");
			if(nbArticles > 0 ) { 
				System.out.println("- supprimer "+ nbArticles +" article"+ (nbArticles > 1 ? "s" : ""));
			}
			if(nbBons > 0 ) {
				System.out.println("- supprimer "+ nbBons +" bon"+ (nbBons > 1 ? "s" : ""));
			}
			if(nbCompos > 0 ) {
				System.out.println("- supprimer "+ nbCompos +" promo"+ (nbCompos > 1 ? "s" : ""));
			}
		}
	}
	
	/** Cette méthode permet de supprimer un fournisseur existant dans la table fournisseur.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant du fournisseur à supprimer
	 * @return Booléen true/false pour indiquer si la suppression est effective
	 */
	private static Boolean supprimer(String id) {
		Boolean rc = false;
		if(fdj.delete(new Fournisseur(Integer.parseInt(id)))) {
			System.out.println("OK : Fournisseur n°"+ id +" supprimé !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher dans la console tous les fournisseurs de la table fournisseur
	 */
	private static void afficherTous() {
		fdj.extractAll().stream().forEach(fournisseur -> System.out.println(fournisseur));
	}

	/** Cette méthode permet d'afficher un fournisseur existant dans la table fournisseur.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors le fournisseur sélectionné sera affiché dans la console.
	 * @param id Identifiant du fournisseur à afficher
	 * @throws InvalidFournisseurException Exception levée lorsqu'une erreur est détectée
	 */
	private static void afficherUn(String id) throws InvalidFournisseurException{
		checkId(id);
		System.out.println(extractOneById(Integer.parseInt(id)));
	}
}

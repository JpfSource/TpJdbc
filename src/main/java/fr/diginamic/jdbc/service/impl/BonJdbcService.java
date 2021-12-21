package fr.diginamic.jdbc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.diginamic.jdbc.dao.impl.BonJdbcDao;
import fr.diginamic.jdbc.entites.Bon;
import fr.diginamic.jdbc.exceptions.InvalidBonException;
import fr.diginamic.jdbc.exceptions.InvalidFournisseurException;


/** Cette classe contient l'ensemble des méthodes métiers à appliquer dans la gestion des bons.<br>
 * Elle fait le lien entre les choix de l'utilisateur et l'accès aux données de la table bon. 
 * @author Jean-Philippe FRANCISCO
 */
public class BonJdbcService {
	
	
	/* CONSTANTES */

	// CONSTANTES / Titre du sous-menu sélectionné par l'utilisateur
	/** Titre pour le sous-menu {@value #CREER_BON_LIB} */
	private final static String CREER_BON_LIB			= "Créer un bon :";

	/** Titre pour le sous-menu {@value #MODIFIER_BON_LIB} */
	private final static String MODIFIER_BON_LIB		= "Modifier un bon :";
	
	/** Titre pour le sous-menu {@value #SUPPRIMER_BON_LIB} */
	private final static String SUPPRIMER_BON_LIB		= "Supprimer un bon :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_TOUS_BONS_LIB} */
	private final static String AFFICHER_TOUS_BONS_LIB	= "Afficher tous les bons :";
	
	/** Titre pour le sous-menu {@value #AFFICHER_UN_BON_LIB} */
	private final static String AFFICHER_UN_BON_LIB		= "Afficher un bon :";
	
	
	/* PRIVATE FIELDS */
	
	/** Instance de la classe permettant d'accéder à la table bon */
	private static BonJdbcDao bdj = new BonJdbcDao();
	
	
	/* PUBLICS METHODS */
	
	/** Cette méthode permet de traiter l'action {@value #CREER_BON_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée
	 */
	public static void creerBon(Scanner in) throws InvalidBonException {
		System.out.println("");
		System.out.println(CREER_BON_LIB);
		System.out.print("-> Quelle est le numéro du nouveau bon = ");
		String numero = in.nextLine();
		System.out.print("-> Quelle est la date de commande du nouveau bon (jj/mm/aaaa) = ");
		String dateCmde = in.nextLine();
		System.out.print("-> Quel est le délai du nouveau bon = ");
		String delai = in.nextLine();
		System.out.print("-> Quel est l'identifiant du fournisseur de ce nouveau bon = ");
		BonJdbcService.creer(numero, dateCmde, delai, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #MODIFIER_BON_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Les paramètres nécessaires pour traiter cette action sont demandés à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void modifierBon(Scanner in) throws InvalidBonException {
		System.out.println("");
		System.out.println(MODIFIER_BON_LIB);
		System.out.print("-> Quel est l'identifiant du bon à modifier = ");
		String id = in.nextLine();
		System.out.print("-> Quelle est le nouveau numéro du bon (ne rien saisir si inchangé) = ");
		String numero = in.nextLine();
		System.out.print("-> Quelle est la nouvelle date de commande du bon (jj/mm/aaaa - ne rien saisir si inchangé) = ");
		String dateCmde = in.nextLine();
		System.out.print("-> Quel est le nouveau délai du bon (ne rien saisir si inchangé) = ");
		String delai = in.nextLine();
		System.out.print("-> Quel est le nouvel identifiant du fournisseur du bon (ne rien saisir si inchangé) = ");
		BonJdbcService.modifier(id, numero, dateCmde, delai, in.nextLine());
	}
	
	/** Cette méthode permet de traiter l'action {@value #SUPPRIMER_BON_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void supprimerBon(Scanner in) throws InvalidBonException {
		System.out.println("");
		System.out.println(SUPPRIMER_BON_LIB);
		System.out.print("-> Quel est l'identifiant du bon à supprimer = ");
		String id = in.nextLine();
		afficheImpactSuppression(id);
		System.out.print("-> Confirmez-vous la suppression du bon n°"+ id +" (O/N) = ");
		if(in.nextLine().toUpperCase().equals("O")) {
			BonJdbcService.supprimer(id);
		}
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_TOUS_BONS_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - La liste de tous les bons est affichée
	 */
	public static void afficherTousBons() {
		System.out.println("");
		System.out.println(AFFICHER_TOUS_BONS_LIB);
		BonJdbcService.afficherTous();
	}
	
	/** Cette méthode permet de traiter l'action {@value #AFFICHER_UN_BON_LIB} :<br>
	 * - Le titre adéquat est affiché<br>
	 * - Le paramètre nécessaire pour traiter cette action est demandé à l'utilisateur via la saisie dans la console<br>
	 * - Si l'action échoue, un messsage d'erreur est affiché<br>
	 * - Si l'action réussi, un message d'information est affiché<br>
	 * Dans tous les cas, la liste des actions possibles est de nouveau affichée<br>   
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	public static void afficherUnBon(Scanner in) throws InvalidBonException {
		System.out.println("");
		System.out.println(AFFICHER_UN_BON_LIB);
		System.out.print("-> Quel est l'identifiant du bon à afficher = ");
		BonJdbcService.afficherUn(in.nextLine());
	}
	
	/** Cette méthode retourne le bon dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du bon souhaité
	 * @return Bon souhaité
	 */
	public static Bon extractOneById(Integer id) {
		return bdj.extractOneById(id);
	}
		
	/** Cette méthode retourne la liste des bons appartenant au fournisseur dont l'identifiant a été passé en paramètre
	 * @param id Identifiant du fournisseur souhaité
	 * @return Liste des bons du fournisseur souhaité
	 */
	public static List<Bon> extractAllByFour(Integer id) {
		return bdj.extractAll().stream().filter(a -> a.getFournisseur().getId() == id).collect(Collectors.toList());
	}

	/** Cette méthode contrôle l'identifiant saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param idToCheck Identifiant à contrôler 
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée
	 */
	public static void checkId(String idToCheck) throws InvalidBonException {
		if(idToCheck == null || idToCheck.trim().equals("")) {
			throw new InvalidBonException("KO : L'identifiant du bon est obligatoire !\n");
		}
		else {
			try {
				Integer id = Integer.parseInt(idToCheck);
				
				if(!bdj.isExist(id)) {
					throw new InvalidBonException("KO : L'identifiant du bon n'existe pas dans la table !\n");
				}
			}
			catch(NumberFormatException e) {
				throw new InvalidBonException("KO : L'identifiant du bon doit être un entier numérique !\n");
			}
		}
	}
	
	
	/* PRIVATE METHODS */

	/** Cette méthode contrôle les données obligatoires du bon saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param numeroToCheck Numéro du bon à contrôler
	 * @param dateCmdeToCheck Date de commande du bon à contrôler
	 * @param delaiToCheck Délai du bon à contrôler
	 * @param idFournisseurToCheck Identifiant du fournisseur du bon à contrôler
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkData(String numeroToCheck, String dateCmdeToCheck, String delaiToCheck, String idFournisseurToCheck) throws InvalidBonException {
		checkData(true, numeroToCheck, dateCmdeToCheck, delaiToCheck, idFournisseurToCheck);
	}

	/** Cette méthode contrôle les données du bon saisi par l'utilisateur dans la console (au format String).<br>
	 * Si une erreur est détectée, une exception est levée avec le message correspondant à l'erreur détectée<br>
	 * @param isObligatoire Booléen pour indiquer si toutes les données sont obligatoires (true) ou facultatif (false)
	 * @param numeroToCheck Numéro du bon à contrôler
	 * @param dateCmdeToCheck Date de commande du bon à contrôler
	 * @param delaiToCheck Délai du bon à contrôler
	 * @param idFournisseurToCheck Identifiant du fournisseur du bon à contrôler
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée
	 */
	private static void checkData(Boolean isObligatoire, String numeroToCheck, String dateCmdeToCheck, String delaiToCheck, String idFournisseurToCheck) throws InvalidBonException {
		String msg = "";
		
		if(numeroToCheck != null && !numeroToCheck.trim().equals("")) {
			try {
				Integer.parseInt(numeroToCheck);
			}
			catch(NumberFormatException e) {
				msg += "KO : Le numéro du bon doit être un entier numérique !\n";
			}
		}
		if(dateCmdeToCheck != null && !dateCmdeToCheck.trim().equals("")) {
			try {
				new SimpleDateFormat("dd/MM/yyyy").parse(dateCmdeToCheck); 
			}
			catch(ParseException e) {
				msg += "KO : La date de commande du bon doit être au format aaaa-mm-jj !\n";
			}
		}
		if(delaiToCheck != null && !delaiToCheck.trim().equals("")) {
			try {
				Integer.parseInt(delaiToCheck);
			}
			catch(NumberFormatException e) {
				msg += "KO : Le délai du bon doit être un entier numérique !\n";
			}
		}
		if(idFournisseurToCheck == null || idFournisseurToCheck.trim().equals("")) {
			if(isObligatoire) {
				msg += "KO : L'identifiant du fournisseur du bon est obligatoire !\n";
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
			throw new InvalidBonException(msg);			
		}
	}

	/** Cette méthode permet de créer un nouveau bon dans la table bon.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param numero Numéro du nouveau bon
	 * @param dateCmde Date de commande du nouveau bon
	 * @param delai Délai du nouveau bon
	 * @param idFournisseur Identifiant du fournisseur du nouveau
	 * @return Identifiant du bon créé
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Integer creer(String numero, String dateCmde, String delai, String idFournisseur) throws InvalidBonException {
		Integer idCree = null;
		
		checkData(numero, dateCmde, delai, idFournisseur);
		
		Date dtCommande = null;
		try {
			dtCommande = new SimpleDateFormat("dd/MM/yyyy").parse(dateCmde); 
		}
		catch(ParseException e) {
			e.printStackTrace();
		}

		Bon newbon = bdj.insert(new Bon( (numero != null && !numero.trim().equals("") ? Integer.parseInt(numero) : null)
				                       , (dateCmde != null && !dateCmde.trim().equals("") ? dtCommande : null)
				                       , (delai != null && !delai.trim().equals("") ? Integer.parseInt(delai) : null)
				                       , FournisseurJdbcService.extractOneById(Integer.parseInt(idFournisseur))));
		if(newbon != null) {
			idCree = newbon.getId();
			System.out.println("OK : Nouveau bon n°"+ idCree +" créé !\n");
		}
		return idCree;
	}
	
	/** Cette méthode permet de modifier un bon existant dans la table bon.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant du bon à modifier
	 * @param newNumero Nouveau numéro du bon à modifier
	 * @param newDateCmde Nouvelle date de commande du bon à modifier
	 * @param newDelai Nouveau délai du bon à modifier
	 * @param newIdFournisseur Nouvel identifiant du fournisseur du bon à modifier
	 * @return Booléen true/false pour indiquer si la modification est effective
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Boolean modifier(String id, String newNumero, String newDateCmde, String newDelai, String newIdFournisseur) throws InvalidBonException {
		Boolean rc = false;
		checkId(id);
		checkData(false, newNumero, newDateCmde, newDelai, newIdFournisseur);

		Date dtCommande = null;
		try {
			dtCommande = new SimpleDateFormat("dd/MM/yyyy").parse(newDateCmde); 
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		
		Bon oldBon = extractOneById(Integer.parseInt(id));
		Bon newBon = new Bon( oldBon.getId()
				            , (newNumero != null && !newNumero.trim().equals("") ? Integer.parseInt(newNumero) : oldBon.getNumero())
		                    , (newDateCmde != null && !newDateCmde.trim().equals("") ? dtCommande : oldBon.getDateCmde())
		                    , (newDelai != null && !newDelai.trim().equals("") ? Integer.parseInt(newDelai) : oldBon.getDelai())
				            , (newIdFournisseur != null && !newIdFournisseur.trim().equals("") ? FournisseurJdbcService.extractOneById(Integer.parseInt(newIdFournisseur)) : oldBon.getFournisseur()));
		
		if(bdj.update(oldBon, newBon)) {
			System.out.println("OK : Bon n°"+ id +" modifié !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher les impacts liés à la suppression du bon
	 * @param id Identifiant du bon à supprimer
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	private static void afficheImpactSuppression(String id) throws InvalidBonException {
		checkId(id);
		Integer nbCompos = CompoJdbcService.extractAllByBon(Integer.parseInt(id)).size();
		if(nbCompos > 0) {
			System.out.println("La suppression de ce bon aura pour conséquence de supprimer "+ nbCompos +" promo"+ (nbCompos > 1 ? "s" : ""));
		}
	}
	
	/** Cette méthode permet de supprimer un bon existant dans la table bon.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors un message d'information adéquat sera affiché dans la console.
	 * @param id Identifiant du bon à supprimer
	 * @return Booléen true/false pour indiquer si la suppression est effective
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	private static Boolean supprimer(String id) throws InvalidBonException {
		Boolean rc = false;
		checkId(id);
		if(bdj.delete(new Bon(Integer.parseInt(id)))) {
			System.out.println("OK : Bon n°"+ id +" supprimé !\n");
			rc = true;
		}
		return rc;
	}
	
	/** Cette méthode permet d'afficher dans la console tous les bons de la table bon
	 */
	private static void afficherTous() {
		bdj.extractAll().stream().forEach(bon -> System.out.println(bon));
	}

	/** Cette méthode permet d'afficher un bon existant dans la table bon.<br>
	 * Si une exception est levée lors des contrôles de données, le message d'erreur sera affiché dans la console et le traitement ne sera pas réalisé.<br>
	 * Sinon si le traitement réussit, alors le bon sélectionné sera affiché dans la console.
	 * @param id Identifiant du bon à afficher
	 * @throws InvalidBonException Exception levée lorsqu'une erreur est détectée 
	 */
	private static void afficherUn(String id) throws InvalidBonException {
		checkId(id);
		System.out.println(extractOneById(Integer.parseInt(id)));
	}
}

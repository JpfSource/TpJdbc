package fr.diginamic.jdbc;


import java.util.Scanner;

import fr.diginamic.jdbc.exceptions.InvalidArticleException;
import fr.diginamic.jdbc.exceptions.InvalidBonException;
import fr.diginamic.jdbc.exceptions.InvalidCompoException;
import fr.diginamic.jdbc.exceptions.InvalidFournisseurException;
import fr.diginamic.jdbc.service.impl.ArticleJdbcService;
import fr.diginamic.jdbc.service.impl.BonJdbcService;
import fr.diginamic.jdbc.service.impl.CompoJdbcService;
import fr.diginamic.jdbc.service.impl.FournisseurJdbcService;

/**
 * @author christophe Germain
 * 
 * Programme principal 
 * 
 * Objectif du programme :
 * 
 * Gérer des Bons de commande d'Articles
 * 
 * 1 - CRUD Fournisseur
 * 2 - CRUD Article
 * 3 - CRUD Bon
 * 4 - Gérer des Compo pour faire le lien entre un article et un bon avec une qté
 * 
 *  En reprenant les consignes des TP 4 - 5 - 6
 *  
 *  Gérer un menu avec l'utilisation de la classe scanner pour pouvoir choisir
 *  1 Gérer Fournisseur
 *  2 Gérer Article
 *  3 Gérer Bon
 *  4 Créer lien Promo (on aura directement la possibilité de saisir le nom de l'article
 *  , le numéro du bon et de la qté )
 *  
 *  
 *  Sous Menus des choix 1 2 3
 *  1 Créer
 *  2 Modifier (demander le nom ou le numéro (pour le bon) )
 *  3 Supprimer (demander le nom ou le numéro (pour le bon) )
 *  4 Liste Compléte
 *  5 Visualisation d'un élément (demander le nom ou le numéro (pour le bon) )
 *  
 *  (pour la création : saisir les informations correspondantes aux rubriques 
 *  de la table correspondante )
 *  
 *  Gérer les Dao et les services pour répondre à la gestion des menus 
 *  
 *   La validation des informations se fait par la touche Entrée 
 **/
public class App {

	/* CONSTANTES */

	// CONSTANTES / Menu principal
	/** Titre pour le menu principal */
	private final static String MENU_PRINCIPAL_LIB	= "Menu principal :";
	
	/** Pour demander le choix de l'utilisateur */
	private final static String QUEL_CHOIX_LIB		= "-> Quel est votre choix = ";
	
	/** Pour afficher un message d'erreur pour le choix sélectionné par l'utilisateur */
	private final static String CHOIX_INCONNU		= "KO : Choix inconnu !";

	
	// CONSTANTES / Menu principal / Choix / Codes
	/** Code du choix {@value #MENU_PRINCIPAL_QUITTER_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_QUITTER		= "0";
	
	/** Code du choix {@value #MENU_PRINCIPAL_FOURNISSEURS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_FOURNISSEURS = "1";
	
	/** Code du choix {@value #MENU_PRINCIPAL_ARTICLES_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_ARTICLES		= "2";
	
	/** Code du choix {@value #MENU_PRINCIPAL_BONS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_BONS			= "3";
	
	/** Code du choix {@value #MENU_PRINCIPAL_COMPOS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_COMPOS		= "4";

	
	// CONSTANTES / Menu principal / Choix / Libellés
	/** Libellé complet du choix {@value #MENU_PRINCIPAL_QUITTER_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_QUITTER_LIB		= MENU_PRINCIPAL_QUITTER		+ ". Quitter le programme";

	/** Libellé complet du choix {@value #MENU_PRINCIPAL_FOURNISSEURS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_FOURNISSEURS_LIB	= MENU_PRINCIPAL_FOURNISSEURS	+ ". Gérer les fournisseurs";
	
	/** Libellé complet du choix {@value #MENU_PRINCIPAL_ARTICLES_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_ARTICLES_LIB		= MENU_PRINCIPAL_ARTICLES		+ ". Gérer les articles";
	
	/** Libellé complet du choix {@value #MENU_PRINCIPAL_BONS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_BONS_LIB			= MENU_PRINCIPAL_BONS			+ ". Gérer les bons";
	
	/** Libellé complet du choix {@value #MENU_PRINCIPAL_COMPOS_LIB} pour le menu principal */
	private final static String MENU_PRINCIPAL_COMPOS_LIB		= MENU_PRINCIPAL_COMPOS			+ ". Gérer les promos";

	
	// CONSTANTES / Sous-menus / Titres
	/** Titre du sous menus du choix {@value #MENU_PRINCIPAL_FOURNISSEURS_LIB} dans le menu principal */
	private final static String FOURNISSEURS_LIB	= "Gérer les fournisseurs :";
	
	/** Titre du sous menus du choix {@value #MENU_PRINCIPAL_ARTICLES_LIB} dans le menu principal */
	private final static String ARTICLES_LIB		= "Gérer les articles :";

	/** Titre du sous menus du choix {@value #MENU_PRINCIPAL_BONS_LIB} dans le menu principal */
	private final static String BONS_LIB			= "Gérer les bons :";
	
	/** Titre du sous menus du choix {@value #MENU_PRINCIPAL_COMPOS_LIB} dans le menu principal */
	private final static String COMPOS_LIB			= "Gérer les promos :";
	
	
	// CONSTANTES / Sous-menus / Choix / Codes
	/** Code du choix {@value #SOUS_MENU_RETOUR_MENU_LIB} pour le sous-menu */
	private final static String SOUS_MENU_RETOUR_MENU	= "0";
	
	/** Code du choix {@value #SOUS_MENU_CREER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_CREER			= "1";
	
	/** Code du choix {@value #SOUS_MENU_MODIFIER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_MODIFIER		= "2";
	
	/** Code du choix {@value #SOUS_MENU_SUPPRIMER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SUPPRIMER		= "3";
	
	/** Code du choix {@value #SOUS_MENU_SHOW_ALL_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SHOW_ALL		= "4";
	
	/** Code du choix {@value #SOUS_MENU_SHOW_ONE_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SHOW_ONE		= "5";

	
	// CONSTANTES / Sous-menus / Choix / Libellés
	/** Libellé complet du choix {@value #SOUS_MENU_RETOUR_MENU_LIB} pour le sous-menu */
	private final static String SOUS_MENU_RETOUR_MENU_LIB	= SOUS_MENU_RETOUR_MENU	+ ". Revenir au menu principal";
	
	/** Libellé complet du choix {@value #SOUS_MENU_CREER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_CREER_LIB			= SOUS_MENU_CREER		+ ". Créer";
	
	/** Libellé complet du choix {@value #SOUS_MENU_MODIFIER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_MODIFIER_LIB		= SOUS_MENU_MODIFIER	+ ". Modifier";
	
	/** Libellé complet du choix {@value #SOUS_MENU_SUPPRIMER_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SUPPRIMER_LIB		= SOUS_MENU_SUPPRIMER	+ ". Supprimer";
	
	/** Libellé complet du choix {@value #SOUS_MENU_SHOW_ALL_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SHOW_ALL_LIB		= SOUS_MENU_SHOW_ALL	+ ". Liste complète";
	
	/** Libellé complet du choix {@value #SOUS_MENU_SHOW_ONE_LIB} pour le sous-menu */
	private final static String SOUS_MENU_SHOW_ONE_LIB		= SOUS_MENU_SHOW_ONE	+ ". Afficher";


	
	/* MAIN */
	
	/** Affichage du menu principal et aiguillage sur le sous-menu adéquat.
	 * @param args Arguments en entrée si nécessaire
	 */
	public static void main(String[] args) {
		System.out.println("Début du programme...");
		Scanner in = new Scanner(System.in);

		String reponse = "";
		while(!reponse.equals(MENU_PRINCIPAL_QUITTER)) {
			reponse = afficherMenuPrincipal(in);
	        switch(reponse) {
	        	case MENU_PRINCIPAL_QUITTER :
	        		break;
	        	case MENU_PRINCIPAL_FOURNISSEURS :
	        		gererFournisseurs(in);
	        		break;
	        	case MENU_PRINCIPAL_ARTICLES :
	        		gererArticles(in);
	        		break;
	        	case MENU_PRINCIPAL_BONS :
	        		gererBons(in);
	        		break;
	        	case MENU_PRINCIPAL_COMPOS :
	        		gererPromos(in);
	        		break;
	        	default :
	        		System.out.println(CHOIX_INCONNU);
	        		break;
	        }
		}
		in.close();
		System.out.println("\nFin du programme !");
	}

	
	/* METHODES PRIVEES STATIQUES  */

	/** Affiche la liste des choix possibles pour le menu principal
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @return Choix du menu principal saisi par l'utilisateur
	 */
	private static String afficherMenuPrincipal(Scanner in) {
		System.out.println("");
		System.out.println(MENU_PRINCIPAL_LIB);
		System.out.println(MENU_PRINCIPAL_QUITTER_LIB);
		System.out.println(MENU_PRINCIPAL_FOURNISSEURS_LIB);
		System.out.println(MENU_PRINCIPAL_ARTICLES_LIB);
		System.out.println(MENU_PRINCIPAL_BONS_LIB);
		System.out.println(MENU_PRINCIPAL_COMPOS_LIB);
		System.out.print  (QUEL_CHOIX_LIB);	
		return in.nextLine();
	}
	
	/** Affiche la liste des choix possibles pour le sous-menu
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 * @return Choix du sous-menu saisi par l'utilisateur
	 */
	private static String afficherSousMenu(Scanner in) {
		System.out.println(SOUS_MENU_RETOUR_MENU_LIB);
		System.out.println(SOUS_MENU_CREER_LIB);
		System.out.println(SOUS_MENU_MODIFIER_LIB);
		System.out.println(SOUS_MENU_SUPPRIMER_LIB);
		System.out.println(SOUS_MENU_SHOW_ALL_LIB);
		System.out.println(SOUS_MENU_SHOW_ONE_LIB);
		System.out.print  (QUEL_CHOIX_LIB);	
		return in.nextLine();
	}

	/** Traite le choix saisi par l'utilisateur au niveau du sous-menu {@value #FOURNISSEURS_LIB}
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 */
	private static void gererFournisseurs(Scanner in) {
		String reponse = "";
		System.out.println("");
		while(!reponse.equals(SOUS_MENU_RETOUR_MENU)) {
			System.out.println(FOURNISSEURS_LIB);
			reponse = afficherSousMenu(in);
			try {
		        switch(reponse) {
	        	case SOUS_MENU_RETOUR_MENU :
	        		break;
	        	case SOUS_MENU_CREER :
	        		FournisseurJdbcService.creerFournisseur(in);
	        		break;
	        	case SOUS_MENU_MODIFIER :
	        		FournisseurJdbcService.modifierFournisseur(in);
	        		break;
	        	case SOUS_MENU_SUPPRIMER :
	        		FournisseurJdbcService.supprimerFournisseur(in);
	        		break;
	        	case SOUS_MENU_SHOW_ALL :
	        		FournisseurJdbcService.afficherTousFournisseurs();
	        		break;
	        	case SOUS_MENU_SHOW_ONE :
	        		FournisseurJdbcService.afficherUnFournisseur(in);
	        		break;
	        	default :
	        		System.out.println(CHOIX_INCONNU);
	        		break;
		        }
			}
			catch(InvalidFournisseurException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/** Traite le choix saisi par l'utilisateur au niveau du sous-menu {@value #ARTICLES_LIB}
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 */
	private static void gererArticles(Scanner in) {
		String reponse = "";
		System.out.println("");
		while(!reponse.equals(SOUS_MENU_RETOUR_MENU)) {
			System.out.println(ARTICLES_LIB);
			reponse = afficherSousMenu(in);
			try {
		        switch(reponse) {
	        	case SOUS_MENU_RETOUR_MENU :
	        		break;
	        	case SOUS_MENU_CREER :
	        		ArticleJdbcService.creerArticle(in);
	        		break;
	        	case SOUS_MENU_MODIFIER :
	        		ArticleJdbcService.modifierArticle(in);
	        		break;
	        	case SOUS_MENU_SUPPRIMER :
	        		ArticleJdbcService.supprimerArticle(in);
	        		break;
	        	case SOUS_MENU_SHOW_ALL :
	        		ArticleJdbcService.afficherTousArticles();
	        		break;
	        	case SOUS_MENU_SHOW_ONE :
	        		ArticleJdbcService.afficherUnArticle(in);
	        		break;
	        	default :
	        		System.out.println(CHOIX_INCONNU);
	        		break;
		        }
			}
			catch(InvalidArticleException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/** Traite le choix saisi par l'utilisateur au niveau du sous-menu {@value #BONS_LIB}
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 */
	private static void gererBons(Scanner in) {
		String reponse = "";
		System.out.println("");
		while(!reponse.equals(SOUS_MENU_RETOUR_MENU)) {
			System.out.println(BONS_LIB);
			reponse = afficherSousMenu(in);
			try {
		        switch(reponse) {
	        	case SOUS_MENU_RETOUR_MENU :
	        		break;
	        	case SOUS_MENU_CREER :
	        		BonJdbcService.creerBon(in);
	        		break;
	        	case SOUS_MENU_MODIFIER :
	        		BonJdbcService.modifierBon(in);
	        		break;
	        	case SOUS_MENU_SUPPRIMER :
	        		BonJdbcService.supprimerBon(in);
	        		break;
	        	case SOUS_MENU_SHOW_ALL :
	        		BonJdbcService.afficherTousBons();
	        		break;
	        	case SOUS_MENU_SHOW_ONE :
	        		BonJdbcService.afficherUnBon(in);
	        		break;
	        	default :
	        		System.out.println(CHOIX_INCONNU);
	        		break;
		        }
			}
			catch(InvalidBonException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/** Traite le choix saisi par l'utilisateur au niveau du sous-menu {@value #COMPOS_LIB}
	 * @param in Scanner permettant de lire le choix saisi par l'utilisateur
	 */
	private static void gererPromos(Scanner in) {
		String reponse = "";
		System.out.println("");
		while(!reponse.equals(SOUS_MENU_RETOUR_MENU)) {
			System.out.println(COMPOS_LIB);
			reponse = afficherSousMenu(in);
			try {
		        switch(reponse) {
	        	case SOUS_MENU_RETOUR_MENU :
	        		break;
	        	case SOUS_MENU_CREER :
	        		CompoJdbcService.creerCompo(in);
	        		break;
	        	case SOUS_MENU_MODIFIER :
	        		CompoJdbcService.modifierCompo(in);
	        		break;
	        	case SOUS_MENU_SUPPRIMER :
	        		CompoJdbcService.supprimerCompo(in);
	        		break;
	        	case SOUS_MENU_SHOW_ALL :
	        		CompoJdbcService.afficherTousCompos();
	        		break;
	        	case SOUS_MENU_SHOW_ONE :
	        		CompoJdbcService.afficherUnCompo(in);
	        		break;
	        	default :
	        		System.out.println(CHOIX_INCONNU);
	        		break;
		        }
			}
			catch(InvalidCompoException e) {
				System.err.println(e.getMessage());
			}
			
		}
	}

}

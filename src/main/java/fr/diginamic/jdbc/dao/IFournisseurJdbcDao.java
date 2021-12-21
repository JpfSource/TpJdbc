package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Fournisseur;

/** Cette interface contient la liste des méthodes qui doivent être implémentées dans la classe FournisseurJdbcDao
 * @author Jean-Philippe FRANCISCO
 */
public interface IFournisseurJdbcDao {

	/** Cette méthode permet d'insérer un fournisseur dans la table fournisseur 
	 * @param fournisseur Fournisseur à créer
	 * @return Fournisseur créé avec l'identifiant valorisé
	 */
	Fournisseur insert(Fournisseur fournisseur);

	/** Cette méthode permet de mettre à jour un fournisseur dans la table fournisseur
	 * @param oldFournisseur Fournisseur avec les anciennes données
	 * @param newFournisseur Fournisseur avec les nouvelles données 
	 * @return true si la mise à jour a réussie, sinon false en cas d'échec
	 */
	Boolean update(Fournisseur oldFournisseur, Fournisseur newFournisseur);
	
	/** Cette méthode permet de supprimer un fournisseur dans la table fournisseur
	 * @param fournisseur Fournisseur à supprimer
	 * @return true si la suppression a réussie, sinon false en cas d'échec
	 */
	Boolean delete(Fournisseur fournisseur);
	
	/** Cette méthode permet d'extraire tous les fournisseurs de la table fournisseur
	 * @return Liste des fournisseurs de la table fournisseur
	 */
	List<Fournisseur> extractAll();
	
	/** Cette méthode permet d'extraire un fournisseur de la table fournisseur en fonction de l'identifiant
	 * @param id Identifiant du fournisseur souhaité
	 * @return Fournisseur trouvé sinon null
	 */
	Fournisseur extractOneById(Integer id);
	
	/** Cette méthode permet d'extraire le premier fournisseur trouvé de la table fournisseur en fonction du nom
	 * @param fournisseur Fournisseur avec le nom souhaité
	 * @return Fournisseur trouvé sinon null
	 */
	Fournisseur extractOne(Fournisseur fournisseur);
}

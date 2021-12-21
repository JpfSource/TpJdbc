package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Bon;

/** Cette interface contient la liste des méthodes qui doivent être implémentées dans la classe BonJdbcDao
 * @author Jean-Philippe FRANCISCO
 */
public interface IBonJdbcDao {

	/** Cette méthode permet d'insérer un bon dans la table bon 
	 * @param bon Bon à créer
	 * @return Bon créé avec l'identifiant valorisé
	 */
	Bon insert(Bon bon);

	/** Cette méthode permet de mettre à jour un bon dans la table bon
	 * @param oldBon Bon avec les anciennes données
	 * @param newBon Bon avec les nouvelles données 
	 * @return true si la mise à jour a réussie, sinon false en cas d'échec
	 */
	Boolean update(Bon oldBon, Bon newBon);
	
	/** Cette méthode permet de supprimer un bon dans la table bon
	 * @param bon Bon à supprimer
	 * @return true si la suppression a réussie, sinon false en cas d'échec
	 */
	Boolean delete(Bon bon);
	
	/** Cette méthode permet d'extraire tous les bons de la table bon
	 * @return Liste des bons de la table bon
	 */
	List<Bon> extractAll();
	
	/** Cette méthode permet d'extraire un bon de la table bon en fonction de l'identifiant
	 * @param id Identifiant du bon souhaité
	 * @return Bon trouvé sinon null
	 */
	Bon extractOneById(Integer id);
	
	/** Cette méthode permet d'extraire le premier bon trouvé de la table bon en fonction des critères renseignés dans le bon
	 * @param bon Bon avec les données recherchés
	 * @return Bon trouvé sinon null
	 */
	Bon extractOne(Bon bon);
}
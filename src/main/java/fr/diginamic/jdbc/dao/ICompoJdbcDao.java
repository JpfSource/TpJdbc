package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Compo;

/** Cette interface contient la liste des méthodes qui doivent être implémentées dans la classe CompoJdbcDao
 * @author Jean-Philippe FRANCISCO
 */
public interface ICompoJdbcDao {

	/** Cette méthode permet d'insérer une compo dans la table compo 
	 * @param compo Compo à créer
	 * @return Compo créée avec l'identifiant valorisé
	 */
	Compo insert(Compo compo);

	/** Cette méthode permet de mettre à jour une compo dans la table compo
	 * @param oldCompo Compo avec les anciennes données
	 * @param newCompo Compo avec les nouvelles données 
	 * @return true si la mise à jour a réussie, sinon false en cas d'échec
	 */
	Boolean update(Compo oldCompo, Compo newCompo);
	
	/** Cette méthode permet de supprimer une compo dans la table compo
	 * @param compo Compo à supprimer
	 * @return true si la suppression a réussie, sinon false en cas d'échec
	 */
	Boolean delete(Compo compo);
	
	/** Cette méthode permet d'extraire toutes les compos de la table compo
	 * @return Liste des compos de la table compo
	 */
	List<Compo> extractAll();
	
	/** Cette méthode permet d'extraire toutes les compos de la table compo qui sont liées au fournisseur fourni en paramètre
	 * @param id Identifiant du fournisseur souhaité
	 * @return Liste des compos du fournisseur souhaité
	 */
	List<Compo> extractAllByFour(Integer id);
	
	/** Cette méthode permet d'extraire une compo de la table compo en fonction de l'identifiant
	 * @param id Identifiant de la compo souhaité
	 * @return Compo trouvée sinon null
	 */
	Compo extractOneById(Integer id);
	
	/** Cette méthode permet d'extraire la première compo trouvée de la table compo en fonction des critères renseignés dans la compo
	 * @param compo Compo avec les données recherchés
	 * @return Compo trouvé sinon null
	 */
	Compo extractOne(Compo compo);
}
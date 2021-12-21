package fr.diginamic.jdbc.dao;

import java.sql.ResultSet;

/** Cette interface contient la liste des méthodes qui doivent être implémentées dans toutes les classes qui héritent de ParentJdbcDao
 * @author Jean-Philippe FRANCISCO
 */
public interface IParentJdbcDao {
	
	/** Cette méthode permet de savoir si une ligne existe bien dans la table en fonction de l'identifiant passé en paramètre 
	 * @param id Identifiant de la ligne cherchée
	 * @return  true si la recherche a réussie, sinon false en cas d'échec
	 */
	Boolean isExist(Integer id);
	
	
	/** Cette méthode permet de transformer un ResultSet en Objet souhaité
	 * @param rsa ResultSet contenant les données à formatter
	 * @return Object formaté avec les données du ResultSet
	 */
	Object fromResultSetToObject(ResultSet rsa);
}

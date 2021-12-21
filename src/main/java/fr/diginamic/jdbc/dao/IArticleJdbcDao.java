package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Article;

/** Cette interface contient la liste des méthodes qui doivent être implémentées dans la classe ArticleJdbcDao
 * @author Jean-Philippe FRANCISCO
 */
public interface IArticleJdbcDao {

	/** Cette méthode permet d'insérer un article dans la table article 
	 * @param article Article à créer
	 * @return Article créé avec l'identifiant valorisé
	 */
	Article insert(Article article);

	/** Cette méthode permet de mettre à jour un article dans la table article
	 * @param oldArticle Article avec les anciennes données
	 * @param newArticle Article avec les nouvelles données 
	 * @return true si la mise à jour a réussie, sinon false en cas d'échec
	 */
	Boolean update(Article oldArticle, Article newArticle);
	
	/** Cette méthode permet de supprimer un article dans la table article
	 * @param article Article à supprimer
	 * @return true si la suppression a réussie, sinon false en cas d'échec
	 */
	Boolean delete(Article article);
	
	/** Cette méthode permet d'extraire tous les articles de la table article
	 * @return Liste des articles de la table article
	 */
	List<Article> extractAll();
	
	/** Cette méthode permet d'extraire un article de la table article en fonction de l'identifiant
	 * @param id Identifiant de l'article souhaité
	 * @return Article trouvé sinon null
	 */
	Article extractOneById(Integer id);
	
	/** Cette méthode permet d'extraire le premier article trouvé de la table article en fonction des critères renseignés dans l'article
	 * @param article Article avec les données recherchés
	 * @return Article trouvé sinon null
	 */
	Article extractOne(Article article);
}

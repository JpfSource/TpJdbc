package fr.diginamic.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.diginamic.jdbc.connection.ConnectionBdd;
import fr.diginamic.jdbc.dao.IArticleJdbcDao;
import fr.diginamic.jdbc.dao.IParentJdbcDao;
import fr.diginamic.jdbc.entites.Article;

/** Cette classe contient l'ensemble des méthodes qui permettent d'accéder à la table article, en utilisant notamment les méthodes disponibles dans la classe ParentJdbcDao
* @author Jean-Philippe FRANCISCO
*/
public class ArticleJdbcDao extends ParentJdbcDao implements IArticleJdbcDao, IParentJdbcDao{

	/* PUBLIC METHODS FROM IParentJdbcDao */
	
	@Override
	public Boolean isExist(Integer id) {
		return (extractOneById(id) != null);
	}

	@Override
	public Object fromResultSetToObject(ResultSet rsa){
		Article article = null;
		if(rsa != null) {
			try {
				FournisseurJdbcDao fdj = new FournisseurJdbcDao();
				article = new Article(rsa.getInt("id")
						             ,rsa.getString("ref")
						             ,rsa.getString("designation")
						             ,rsa.getDouble("prix")
						             ,fdj.extractOneById(rsa.getInt("id_fou")));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}

	
	/* PUBLIC METHODS FROM IArticleJdbcDao */
	
	@Override
	public Article insert(Article article) {
		Article newArticle = null;
		if(super.modify(ConnectionBdd.ARTICLE_INSERT, parmsArticleToSelectInsert(article))) {
			newArticle = extractOne(article); 
		}
		return newArticle;
	}	

	@Override
	public List<Article> extractAll() {
		List<Article> articles = new ArrayList<Article>();
		super.extractAll(ConnectionBdd.ARTICLE_EXTRACT_ALL).stream().forEach(article -> articles.add((Article)article));
		return articles;
	}

	@Override
	public Article extractOneById(Integer id) {
		return (Article)super.extractOneById(ConnectionBdd.ARTICLE_EXTRACT_ONE_BY_ID, "id", id);
	}

	@Override
	public Article extractOne(Article article) {
		return (Article)super.extractOne(ConnectionBdd.ARTICLE_EXTRACT_ONE, parmsArticleToSelectInsert(article));
	}

	@Override
	public Boolean update(Article oldArticle, Article newArticle) {
		return super.modify(ConnectionBdd.ARTICLE_UPDATE, parmsArticleToUpdate(oldArticle, newArticle));
	}

	@Override
	public Boolean delete(Article article) {
		Map<String, Object> parms = parmsArticleToDelete(article);
		super.modify(ConnectionBdd.ARTICLE_DELETE_COMPOS, parms);
		
		return super.modify(ConnectionBdd.ARTICLE_DELETE, parms);
	}
	
	
	/* PRIVATE METHODS */
	
	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Select ou Insert sur la table article
	 * @param article Article dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsArticleToSelectInsert(Article article){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(article != null ) {
			parms.put("ref", article.getReference());
			parms.put("designation", article.getDesignation());
			parms.put("prix", article.getPrix());
			parms.put("id_fou", (article.getFournisseur() != null ? article.getFournisseur().getId() : null));
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Update sur la table article
	 * @param oldArticle Article contenant les anciennes données qui doivent être mappés
	 * @param newArticle Article contenant les nouvelles données qui doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsArticleToUpdate(Article oldArticle, Article newArticle){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(oldArticle != null && newArticle != null) {
			parms.put("newRef", newArticle.getReference());
			parms.put("newDesignation", newArticle.getDesignation());
			parms.put("newPrix", newArticle.getPrix());
			parms.put("newIdFou", (newArticle.getFournisseur() != null ? newArticle.getFournisseur().getId() : null));
			
			parms.put("oldId", oldArticle.getId());
			parms.put("oldRef", oldArticle.getReference());
			parms.put("oldDesignation", oldArticle.getDesignation());
			parms.put("oldPrix", oldArticle.getPrix());
			parms.put("oldIdFou", (oldArticle.getFournisseur() != null ? oldArticle.getFournisseur().getId() : null));
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Delete sur la table article
	 * @param article Article dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsArticleToDelete(Article article){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("id", (article != null ? article.getId() : null));
		return parms;
	}

}

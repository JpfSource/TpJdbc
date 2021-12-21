package fr.diginamic.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.diginamic.jdbc.connection.ConnectionBdd;
import fr.diginamic.jdbc.dao.ICompoJdbcDao;
import fr.diginamic.jdbc.dao.IParentJdbcDao;
import fr.diginamic.jdbc.entites.Compo;

/** Cette classe contient l'ensemble des méthodes qui permettent d'accéder à la table compo, en utilisant notamment les méthodes disponibles dans la classe ParentJdbcDao
* @author Jean-Philippe FRANCISCO
*/
public class CompoJdbcDao extends ParentJdbcDao implements ICompoJdbcDao, IParentJdbcDao{

	/* PUBLIC METHODS FROM IParentJdbcDao */
	
	@Override
	public Boolean isExist(Integer id) {
		return (extractOneById(id) != null);
	}

	@Override
	public Object fromResultSetToObject(ResultSet rsa){
		Compo compo = null;
		if(rsa != null) {
			try {
				ArticleJdbcDao adj = new ArticleJdbcDao();
				BonJdbcDao bdj = new BonJdbcDao();
				compo = new Compo(rsa.getInt("id")
						         ,adj.extractOneById(rsa.getInt("id_art"))
						         ,bdj.extractOneById(rsa.getInt("id_bon"))
						         ,rsa.getInt("qte"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return compo;
	}

	
	/* PUBLIC METHODS FROM ICompoJdbcDao */
	
	@Override
	public Compo insert(Compo compo) {
		Compo newCompo = null;
		if(super.modify(ConnectionBdd.COMPO_INSERT, parmsCompoToSelectInsert(compo))) {
			newCompo = extractOne(compo); 
		}
		return newCompo;
	}	

	@Override
	public List<Compo> extractAll() {
		List<Compo> compos = new ArrayList<Compo>();
		super.extractAll(ConnectionBdd.COMPO_EXTRACT_ALL).stream().forEach(compo -> compos.add((Compo)compo));
		return compos;
	}

	@Override
	public List<Compo> extractAllByFour(Integer id) {
		List<Compo> compos = new ArrayList<Compo>();
		super.extractAll(ConnectionBdd.COMPO_EXTRACT_ALL_BY_FOUR, "id", id).stream().forEach(compo -> compos.add((Compo)compo));
		return compos;
	}

	@Override
	public Compo extractOneById(Integer id) {
		return (Compo)super.extractOneById(ConnectionBdd.COMPO_EXTRACT_ONE_BY_ID, "id", id);
	}

	@Override
	public Compo extractOne(Compo compo) {
		return (Compo)super.extractOne(ConnectionBdd.COMPO_EXTRACT_ONE, parmsCompoToSelectInsert(compo));
	}

	@Override
	public Boolean update(Compo oldCompo, Compo newCompo) {
		return super.modify(ConnectionBdd.COMPO_UPDATE, parmsCompoToUpdate(oldCompo, newCompo));
	}

	@Override
	public Boolean delete(Compo compo) {
		return super.modify(ConnectionBdd.COMPO_DELETE, parmsCompoToDelete(compo));
	}
	
	
	/* PRIVATE METHODS */
	
	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Select ou Insert sur la table compo
	 * @param compo Compo dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsCompoToSelectInsert(Compo compo){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(compo != null ) {
			parms.put("idArt", (compo.getArticle() != null ? compo.getArticle().getId() : null));
			parms.put("idBon", (compo.getBon() != null ? compo.getBon().getId() : null));
			parms.put("qte", compo.getQte());
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Update sur la table compo
	 * @param oldCompo Compo contenant les anciennes données qui doivent être mappés
	 * @param newCompo Compo contenant les nouvelles données qui doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsCompoToUpdate(Compo oldCompo, Compo newCompo){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(oldCompo != null && newCompo != null) {
			parms.put("newIdArt", (newCompo.getArticle() != null ? newCompo.getArticle().getId() : null));
			parms.put("newIdBon", (newCompo.getBon() != null ? newCompo.getBon().getId() : null));
			parms.put("newQte", newCompo.getQte());
			
			parms.put("oldId", oldCompo.getId());
			parms.put("oldIdArt", (oldCompo.getArticle() != null ? oldCompo.getArticle().getId() : null));
			parms.put("oldIdBon", (oldCompo.getBon() != null ? oldCompo.getBon().getId() : null));
			parms.put("oldQte", oldCompo.getQte());
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Delete sur la table compo
	 * @param compo Compo dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsCompoToDelete(Compo compo){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("id", (compo != null ? compo.getId() : null));
		return parms;
	}
}

package fr.diginamic.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.diginamic.jdbc.connection.ConnectionBdd;
import fr.diginamic.jdbc.dao.IBonJdbcDao;
import fr.diginamic.jdbc.dao.IParentJdbcDao;
import fr.diginamic.jdbc.entites.Bon;

/** Cette classe contient l'ensemble des méthodes qui permettent d'accéder à la table bon, en utilisant notamment les méthodes disponibles dans la classe ParentJdbcDao
* @author Jean-Philippe FRANCISCO
*/
public class BonJdbcDao extends ParentJdbcDao implements IBonJdbcDao, IParentJdbcDao{

	/* PUBLIC METHODS FROM IParentJdbcDao */
	
	@Override
	public Boolean isExist(Integer id) {
		return (extractOneById(id) != null);
	}

	@Override
	public Object fromResultSetToObject(ResultSet rsa){
		Bon bon = null;
		if(rsa != null) {
			try {
				FournisseurJdbcDao fdj = new FournisseurJdbcDao();
				bon = new Bon(rsa.getInt("id")
						     ,rsa.getInt("numero")
						     ,rsa.getDate("date_cmde")
						     ,rsa.getInt("delai")
						     ,fdj.extractOneById(rsa.getInt("id_fou")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bon;
	}

	
	/* PUBLIC METHODS FROM IBonJdbcDao */
	
	@Override
	public Bon insert(Bon bon) {
		Bon newBon = null;
		if(super.modify(ConnectionBdd.BON_INSERT, parmsBonToSelectInsert(bon))) {
			newBon = extractOne(bon); 
		}
		return newBon;
	}	

	@Override
	public List<Bon> extractAll() {
		List<Bon> bons = new ArrayList<Bon>();
		super.extractAll(ConnectionBdd.BON_EXTRACT_ALL).stream().forEach(bon -> bons.add((Bon)bon));
		return bons;
	}

	@Override
	public Bon extractOneById(Integer id) {
		return (Bon)super.extractOneById(ConnectionBdd.BON_EXTRACT_ONE_BY_ID, "id", id);
	}

	@Override
	public Bon extractOne(Bon bon) {
		return (Bon)super.extractOne(ConnectionBdd.BON_EXTRACT_ONE, parmsBonToSelectInsert(bon));
	}

	@Override
	public Boolean update(Bon oldBon, Bon newBon) {
		return super.modify(ConnectionBdd.BON_UPDATE, parmsBonToUpdate(oldBon, newBon));
	}

	@Override
	public Boolean delete(Bon bon) {
		Map<String, Object> parms = parmsBonToDelete(bon);
		super.modify(ConnectionBdd.BON_DELETE_COMPOS, parms);
		
		return super.modify(ConnectionBdd.BON_DELETE, parms);
	}
	
	
	/* PRIVATE METHODS */
	
	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Select ou Insert sur la table bon
	 * @param bon Bon dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsBonToSelectInsert(Bon bon){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(bon != null ) {
			parms.put("numero", bon.getNumero());
			parms.put("date_cmde", bon.getDateCmde());
			parms.put("delai", bon.getDelai());
			parms.put("id_fou", (bon.getFournisseur() != null ? bon.getFournisseur().getId() : null));
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Update sur la table bon
	 * @param oldBon Bon contenant les anciennes données qui doivent être mappés
	 * @param newBon Bon contenant les nouvelles données qui doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsBonToUpdate(Bon oldBon, Bon newBon){
		Map<String, Object> parms = new HashMap<String, Object>();
		if(oldBon != null && newBon != null) {
			parms.put("newNumero", newBon.getNumero());
			parms.put("newDateCmde", newBon.getDateCmde());
			parms.put("newDelai", newBon.getDelai());
			parms.put("newIdFou", (newBon.getFournisseur() != null ? newBon.getFournisseur().getId() : null));
			
			parms.put("oldId", oldBon.getId());
			parms.put("oldNumero", oldBon.getNumero());
			parms.put("oldDateCmde", oldBon.getDateCmde());
			parms.put("oldDelai", oldBon.getDelai());
			parms.put("oldIdFou", (oldBon.getFournisseur() != null ? oldBon.getFournisseur().getId() : null));
		}
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Delete sur la table bon
	 * @param bon Bon dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsBonToDelete(Bon bon){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("id", (bon != null ? bon.getId() : null));
		return parms;
	}
}

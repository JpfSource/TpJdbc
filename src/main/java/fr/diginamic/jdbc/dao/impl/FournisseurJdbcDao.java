package fr.diginamic.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.diginamic.jdbc.connection.ConnectionBdd;
import fr.diginamic.jdbc.dao.IFournisseurJdbcDao;
import fr.diginamic.jdbc.dao.IParentJdbcDao;
import fr.diginamic.jdbc.entites.Fournisseur;

/** Cette classe contient l'ensemble des méthodes qui permettent d'accéder à la table fournisseur, en utilisant notamment les méthodes disponibles dans la classe ParentJdbcDao
* @author Jean-Philippe FRANCISCO
*/
public class FournisseurJdbcDao extends ParentJdbcDao implements IFournisseurJdbcDao, IParentJdbcDao{

	/* PUBLIC METHODS FROM IParentJdbcDao */
	
	@Override
	public Boolean isExist(Integer id) {
		return (extractOneById(id) != null);
	}

	@Override
	public Object fromResultSetToObject(ResultSet rsa){
		Fournisseur fournisseur = null;
		if(rsa != null) {
			try {
				fournisseur = new Fournisseur(rsa.getInt("id")
                                             ,rsa.getString("nom"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fournisseur;
	}

	
	/* PUBLIC METHODS FROM IFournisseurJdbcDao */
	
	@Override
	public Fournisseur insert(Fournisseur fournisseur) {
		Fournisseur newFournisseur = null;
		if(super.modify(ConnectionBdd.FOURNISSEUR_INSERT, parmsFournisseurToSelectInsert(fournisseur))) {
			newFournisseur = extractOne(fournisseur); 
		}
		return newFournisseur;
	}	

	@Override
	public List<Fournisseur> extractAll() {
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		super.extractAll(ConnectionBdd.FOURNISSEUR_EXTRACT_ALL).stream().forEach(fournisseur -> fournisseurs.add((Fournisseur)fournisseur));
		return fournisseurs;
	}

	@Override
	public Fournisseur extractOneById(Integer id) {
		return (Fournisseur)super.extractOneById(ConnectionBdd.FOURNISSEUR_EXTRACT_ONE_BY_ID, "id", id);
	}

	@Override
	public Fournisseur extractOne(Fournisseur fournisseur) {
		return (Fournisseur)super.extractOne(ConnectionBdd.FOURNISSEUR_EXTRACT_ONE, parmsFournisseurToSelectInsert(fournisseur));
	}

	@Override
	public Boolean update(Fournisseur oldFournisseur, Fournisseur newFournisseur) {
		return super.modify(ConnectionBdd.FOURNISSEUR_UPDATE, parmsFournisseurToUpdate(oldFournisseur, newFournisseur));
	}

	@Override
	public Boolean delete(Fournisseur fournisseur) {
		Map<String, Object> parms = parmsFournisseurToDelete(fournisseur);
		super.modify(ConnectionBdd.FOURNISSEUR_DELETE_COMPOS, parms);		
		super.modify(ConnectionBdd.FOURNISSEUR_DELETE_ARTICLES, parms);		
		super.modify(ConnectionBdd.FOURNISSEUR_DELETE_BONS, parms);
		
		return super.modify(ConnectionBdd.FOURNISSEUR_DELETE, parms);		
	}
	
	
	/* PRIVATE METHODS */
	
	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Select ou Insert sur la table fournisseur
	 * @param fournisseur Fournisseur dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsFournisseurToSelectInsert(Fournisseur fournisseur){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("nom", (fournisseur != null ? fournisseur.getNom() : null));
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Update sur la table fournisseur
	 * @param oldFournisseur Fournisseur contenant les anciennes données qui doivent être mappés
	 * @param newFournisseur Fournisseur contenant les nouvelles données qui doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsFournisseurToUpdate(Fournisseur oldFournisseur, Fournisseur newFournisseur){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("newNom", (newFournisseur != null ? newFournisseur.getNom() : null));
		parms.put("oldId", (oldFournisseur != null ? oldFournisseur.getId() : null));
		parms.put("oldNom", (oldFournisseur != null ? oldFournisseur.getNom() : null));
		return parms;
	}

	/** Cette méthode permet de constituer une Map avec les paramètres nécessaires à l'exécution d'une requête SQL de type Delete sur la table fournisseur
	 * @param fournisseur Fournisseur dont les attibuts doivent être mappés
	 * @return Map contenant l'ensemble des paramètres à utiliser lors de l'exécution du SQL 
	 */
	private static Map<String, Object> parmsFournisseurToDelete(Fournisseur fournisseur){
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("id", (fournisseur != null ? fournisseur.getId() : null));
		return parms;
	}
}

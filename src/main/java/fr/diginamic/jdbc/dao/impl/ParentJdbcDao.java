package fr.diginamic.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.diginamic.jdbc.connection.ConnectionBdd;
import fr.diginamic.jdbc.dao.IParentJdbcDao;
import fr.diginamic.jdbc.util.NamedParameterStatement;

/** Cette classe abstraite permet aux autres classes du package d'hériter de méthodes d'accès à la base de données.<br>
 * @author Jean-Philippe FRANCISCO
 */
public abstract class ParentJdbcDao implements IParentJdbcDao {
	
	/* PRIVATE FIELDS */
	
	/** Connection à la base de données */
	private Connection connection;

	
	/* CONSTRUCTEUR */
	
	/** Constructeur de la classe (appelé lors des constructeurs des classes filles) qui récupère une connection à la base de données */
	public ParentJdbcDao() {
		super();
		try {
			this.connection = ConnectionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	/* PROTECTED METHODS */
	
	@Override
	protected void finalize() throws Throwable {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/** Cette méthode renvoie une liste d'objets correspondants à la requête SQL
	 * @param sql Requête SQL à éxécuter
	 * @return Liste d'objet correspondant à la requête SQL
	 */
	protected List<Object> extractAll(String sql){
		return extractAll(sql, null, null);
	}
	
	/** Cette méthode renvoie une liste d'objets correspondants à la requête SQL, filtrés par key et value
	 * @param sql Requête SQL à éxécuter
	 * @param key Paramètre indiquée dans la requête SQL
	 * @param value Valeur à injecter dans la requête SQL
	 * @return Liste d'objet correspondant à la requête SQL, filtrés par key et value
	 */
	protected List<Object> extractAll(String sql, String key, Integer value){
		NamedParameterStatement nps = null;
		ResultSet rs = null;
		List<Object> objects = new ArrayList<Object>();
		
		try {
			nps = new NamedParameterStatement(connection, sql);
			if(key != null && value != null) {
				nps.setInt(key, value);
			}
			rs = nps.executeQuery();

			if(rs != null) {
				while(rs.next()) {
					objects.add(fromResultSetToObject(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(nps != null) {
					nps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objects;
	}

	/** Cette méthode renvoie un objet unique correspondant à la requête SQL et filtré par key et value
	 * @param sql Requête SQL à éxécuter
	 * @param key Paramètre indiquée dans la requête SQL
	 * @param value Valeur à injecter dans la requête SQL
	 * @return Objet unique récupéré par la requête SQL et filtré par key et value
	 */
	protected Object extractOneById(String sql, String key, Integer value) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put(key, value);
		return extractOne(sql, parms);
	}
	
	/** Cette méthode renvoie un objet unique correspondant à la requête SQL et filtré par une liste de paramètres sous la forme Key/Value
	 * @param sql Requête SQL à éxécuter
	 * @param parms Map contenant la liste des paramètres sous la forme Key/Value
	 * @return Objet unique récupéré par la requête SQL et la liste des paramètres
	 */
	protected Object extractOne(String sql, Map<String, Object> parms) {
		NamedParameterStatement nps = null;
		ResultSet rs = null;
		Object result = null;
		
		try {
			nps = new NamedParameterStatement(connection, sql);
			setParameters(parms, nps);
			rs = nps.executeQuery();

			if(rs != null) {
				if(rs.next()) {
					result = fromResultSetToObject(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(nps != null) {
					nps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/** Cette méthode exécute une requête de modification des données en base (Insert, Update ou Delete) grâce à la requête SQL et filtré par une liste de paramètres sous la forme Key/Value
	 * @param sql Requête SQL à éxécuter
	 * @param parms Map contenant la liste des paramètres sous la forme Key/Value
	 * @return Si une seule ligne mise à jour (Insert, Update ou Delete) alors true sinon false
	 */
	protected Boolean modify(String sql, Map<String, Object> parms) {
		int nbRowsAffected = 0;
		NamedParameterStatement nps = null;
		try {
			nps = new NamedParameterStatement(connection, sql);
			setParameters(parms, nps);
			nbRowsAffected = nps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(nps != null) {
					nps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (nbRowsAffected == 1);
	}

	
	/* PRIVATE METHODS */

	/** Cette méthode injecte les paramètres dans la NamedParameterStatement
	 * @param parms Map contenant la liste des paramètres sous la forme Key/Value 
	 * @param nps NamedParameterStatement utilisé dans l'éxécution de la requête
	 */
	private void setParameters(Map<String, Object> parms, NamedParameterStatement nps) {
		try {
			for (Map.Entry<String, Object> entry : parms.entrySet()) {
				if(entry.getValue() != null) {
					nps.setObject(entry.getKey(), entry.getValue());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

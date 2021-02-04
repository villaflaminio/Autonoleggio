package dao.jpa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.DAOFactory;
import dao.interfacce.AutomobileDAO;
import dao.jdbc.JdbcDAOFactory;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;

public class JpaAutomobileDAO implements AutomobileDAO {
	private static JpaAutomobileDAO instance;
	private static final String TABELLA_AUTOMOBILE = "automobile";
	private static final String ID = "id" ;
	private static final String MARCA = "marca";
	private static final String TARGA = "targa";
	private static final String NUMERO_PORTE = "numeroPorte";
	private static final String CATEGORIA = "categoria";
	private static final String PRENOTABILE = "prenotabile";
	private static final String TABELLA_NOLEGGIO = "noleggio";
	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();

	public static JpaAutomobileDAO getInstance() {
		if (instance == null) {
			instance = new JpaAutomobileDAO();
		}
		return instance;
	}
	public boolean inserisciAutomobile(Automobile automobile,Categoria categoria) {
		EntityManager manager = JpaDAOFactory.getManager();
		 EntityTransaction transaction = manager.getTransaction();
		 	transaction.begin();
		 manager.merge(automobile);
		 transaction.commit();
		 return true;
	}	

	/*
	public boolean modificaAutomobile(Automobile automobile) {
		EntityManager manager = JpaDAOFactory.getManager();
		
		manager.getTransaction().begin();
		if (!manager.contains(automobile)) {
			automobile = manager.merge(automobile);
		}
		manager.merge(automobile);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}	
	
	*/
	public List<Automobile> getListAutomobile() {
		 EntityManager manager = JpaDAOFactory.getManager();
		 return manager.createNamedQuery("Automobile.findAll").getResultList();
		 }

	public List<Automobile> getAutomobiliPerCategoria(Categoria categoria){
	    Query query = JpaDAOFactory.getManager().createQuery("SELECT a FROM Automobile a WHERE a.categoria =:categoria");
	    query.setParameter("categoria",categoria);
	    List<Automobile> automobili = query.getResultList();
	    
	    return automobili;
	  }
	 
	public Automobile getAutomobile(int id) {
		 Query query = JpaDAOFactory
		 .getManager()
		 .createQuery("SELECT a from Automobile a WHERE a.id =:id ");
		query.setParameter("id", id);
		try {
			return (Automobile)query.getSingleResult();
		}catch (Exception e) {
			
		}
		return null;
		
	}
	public List<Integer> getCategorieAutoDisponibili() {
		
		  Query query = JpaDAOFactory.getManager().createQuery("SELECT distinct a.categoria from Automobile a WHERE a.prenotabile = 1 ");
		    List<Integer> categorie = query.getResultList();
		    
		    return categorie;
	}
	public List<Automobile> getAutoDisponibili(){
	    Query query = JpaDAOFactory.getManager().createQuery("SELECT a FROM Automobile a WHERE a.prenotabile = 1");
	    List<Automobile> automobili = query.getResultList();
	    
	    return automobili;
	  }
	
	public boolean existAutomobile(String targa) {
		Automobile utente = null;
		Query query = JpaDAOFactory
				 .getManager()
				 .createQuery("SELECT a from Automobile a WHERE a.targa =:targa ");
				query.setParameter("targa", targa);
		try {
			 utente = (Automobile) query.getSingleResult();
			} catch (Exception e){
				return false;
			}
		  
		if(utente != null)	
			return true;
		return false;
		
	}
	
	
	public List<Automobile> getAutoDisponibili(Date dtInizio, Date dtFine,int idCategoria ) {
		List<Automobile> automobile = new ArrayList();
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT DISTINCT id FROM " + TABELLA_AUTOMOBILE +
						 "  WHERE id NOT IN(" +
						 " SELECT DISTINCT automobile FROM " + TABELLA_NOLEGGIO +
						 " WHERE dataInizio <= "+ " ?"+
						 " AND dataFine >= "+ "? ) AND prenotabile =1 AND categoria =" + " ?";
			     
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, dtFine);
			statement.setDate(2, dtInizio );
			statement.setInt(3, idCategoria );
	
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				
				int idAuto = set.getInt(set.findColumn(ID));
				Automobile a = getAutomobile(idAuto);
				automobile.add(a);
	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return automobile;
	}
	public List<Automobile> getAutomobiliPrenotabili() {
		List<Automobile> automobile = new ArrayList();
		Automobile a = null;
		
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					PRENOTABILE + " = 1 ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria= resultSet.getInt(resultSet.findColumn(CATEGORIA));

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);

				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
				
				automobile.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return automobile;
	}
	
	
}

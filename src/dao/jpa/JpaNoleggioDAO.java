package dao.jpa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.interfacce.NoleggioDAO;
import dao.jdbc.JdbcDAOFactory;
import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;

public class JpaNoleggioDAO implements NoleggioDAO{
	private static JpaNoleggioDAO instance;
	private static final String TABELLA_NOLEGGIO = "noleggio";
	private static final String TABELLA_AUTOMOBILE = "automobile";
	private static final String ID = "id" ;
	private static final String UTENTE = "utente" ;
	private static final String AUTOMOBILE = "automobile";
	private static final String DATAINIZIO = "dataInizio";
	private static final String DATAFINE = "dataFine" ;
	private static final String PREZZO = "prezzo" ;
	private static final String CATEGORIA = "categoria";
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	JpaCategoriaDAO dbCategoria = JpaCategoriaDAO.getInstance();
	JpaUtenteDAO dbUtente = JpaUtenteDAO.getInstance();
	JpaAutomobileDAO dbAutomobile = JpaAutomobileDAO.getInstance();
	public static JpaNoleggioDAO getInstance() {
		if (instance == null) {
			instance = new JpaNoleggioDAO();
		}
		return instance;
	}
	
	public List<Noleggio> getNoleggiUtente(Utente utente){
		Query query = JpaDAOFactory
		.getManager()
		.createQuery("SELECT n FROM Noleggio n WHERE n.utente in (SELECT u FROM Utente u where u.id=:id)");
		query.setParameter("id", utente.getId());
		try{return (List<Noleggio>)query.getResultList();} catch (Exception e) {}
		return null;
	}
	
	
	
		public List<Noleggio> getNoleggi() {
			 EntityManager manager = JpaDAOFactory.getManager();
			 return manager.createNamedQuery("Noleggio.findAll").getResultList();
		 }
		
		public boolean  inserisciNoleggio(Noleggio noleggio) {
			EntityManager manager = JpaDAOFactory.getManager();
			 EntityTransaction transaction = manager.getTransaction();
			 	transaction.begin();
			 manager.persist(noleggio);
			 transaction.commit();
			 return true;
		}	
		
		public boolean  deleteNoleggio(Noleggio noleggio) {
			EntityManager manager = JpaDAOFactory.getManager();
			
			manager.getTransaction().begin();
			if (!manager.contains(noleggio)) {
				noleggio = manager.merge(noleggio);
			}
			manager.remove(noleggio);
			manager.getTransaction().commit();
			manager.close();
			return true;
		}	
		

		public Noleggio getNoleggioById(int id) {
			
			Query query = JpaDAOFactory.getManager()
					.createQuery("SELECT n from Noleggio n WHERE n.id=:id ");
					query.setParameter("id", id);
					try {return (Noleggio) query.getSingleResult();} catch (Exception e){}
					return null;
		}
		
		public ArrayList<Automobile> getAutoDisponibili(Date dtInizio, Date dtFine ) {
			ArrayList<Automobile> automobile = new ArrayList();  ;

			try(Connection connection = JdbcDAOFactory.getConnection()) {
				String sql = "SELECT DISTINCT id FROM " + TABELLA_AUTOMOBILE +
							 "  WHERE id NOT IN(" +
							 " SELECT DISTINCT automobile FROM " + TABELLA_NOLEGGIO +
							 " WHERE dataInizio <= "+ " ?"+
							 " AND dataFine >= "+ "? ) AND prenotabile =1";
				     
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setDate(1, dtFine);
				statement.setDate(2, dtInizio );

				ResultSet set = statement.executeQuery();
				while (set.next()) {
					
					int idAuto = set.getInt(set.findColumn(ID));
					Automobile a = dbAutomobile.getAutomobile(idAuto);
					automobile.add(a);

					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return automobile;
		}
	
	
	public ArrayList<Categoria> getCategoriaAutoDisponibili(Date dtInizio, Date dtFine ) {
		ArrayList<Categoria> categoria = new ArrayList();
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT DISTINCT categoria FROM " + TABELLA_AUTOMOBILE +
						 "  WHERE id NOT IN(" +
						 " SELECT DISTINCT automobile FROM " + TABELLA_NOLEGGIO +
						 " WHERE dataInizio <= "+ " ?"+
						 " AND dataFine >= "+ "? ) AND prenotabile =1";
			     
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, dtFine);
			statement.setDate(2, dtInizio );

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				
				int idCategoria = set.getInt(set.findColumn(CATEGORIA));
				Categoria c = dbCategoria.getCategoria(idCategoria);
				categoria.add(c);

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoria;
	}

		
		
}
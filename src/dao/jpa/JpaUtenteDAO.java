package dao.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.interfacce.UtenteDAO;
import dao.jdbc.JdbcDAOFactory;
import model.Utente;

public class JpaUtenteDAO  implements UtenteDAO{

	private static JpaUtenteDAO instance;

	public static JpaUtenteDAO getInstance() {
		if (instance == null) {
			instance = new JpaUtenteDAO();
		}
		return instance;
	}
	@Override
	public boolean inserisciUtente(Utente utente) {
		 EntityManager manager = JpaDAOFactory.getManager();
		 EntityTransaction transaction = manager.getTransaction();
		 transaction.begin();
		 manager.persist(utente);
		 transaction.commit();
		 return true;
		 } 
	
	public Utente getUtente(String user, String pass) {
		 Query query = JpaDAOFactory
		 .getManager()
		 .createQuery("SELECT u from Utente u WHERE u.email like :user and u.password like :pass");
		query.setParameter("user", user);
		query.setParameter("pass", pass);
		try {
			return (Utente)query.getSingleResult();
		}catch (Exception e) {
			
		}
		return null;
		
		}
	
	public boolean existUtente(String email) {
		Utente utente = null;
		Query query = JpaDAOFactory
				 .getManager()
				 .createQuery("SELECT u from Utente u WHERE u.email =:email ");
				query.setParameter("email", email);
		try {
			 utente = (Utente) query.getSingleResult();
			} catch (Exception e){
				return false;
			}
		  
		if(utente != null)	
			return true;
		return false;

		
		
	}
	
	public Utente getUtenteById(int id) {
		Query query = JpaDAOFactory.getManager()
				.createQuery("SELECT u FROM Utente u WHERE u.id =:id");
				query.setParameter("id", id);
				try {return (Utente) query.getSingleResult();} catch (Exception e){}
				return null;
	}
	
	
}

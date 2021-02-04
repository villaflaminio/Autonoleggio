package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAOFactory;
import dao.interfacce.AutomobileDAO;
import dao.interfacce.CategoriaDAO;
import dao.interfacce.NoleggioDAO;
import dao.interfacce.UtenteDAO;

public class JpaDAOFactory extends DAOFactory{
	@Override
	public UtenteDAO getUtenteDAO() {
		return JpaUtenteDAO.getInstance();
	}

	@Override
	public AutomobileDAO getAutomobileDAO() {
		return JpaAutomobileDAO.getInstance();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return JpaCategoriaDAO.getInstance();
	}
	@Override
	public NoleggioDAO getNoleggioDAO() {
		
		return JpaNoleggioDAO.getInstance();
	}
	
	public static EntityManager getManager() {
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 } catch (ClassNotFoundException e) {
		 e.printStackTrace();
		 }
		 EntityManagerFactory factory = Persistence
		 .createEntityManagerFactory("autonoleggio");
		 EntityManager manager = factory.createEntityManager();
		return manager;
		}


	

	

}

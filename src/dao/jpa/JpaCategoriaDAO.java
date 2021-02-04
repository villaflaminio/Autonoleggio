package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.interfacce.CategoriaDAO;
import dao.jdbc.JdbcCategoriaDAO;
import model.Automobile;
import model.Categoria;
import model.Utente;

public class JpaCategoriaDAO implements CategoriaDAO {
	private static JpaCategoriaDAO instance;

	public static JpaCategoriaDAO getInstance() {
		if (instance == null) {
			instance = new JpaCategoriaDAO();
		}
		return instance;
	}
	
	public boolean inserisciCategoria(Categoria categoria) {
		EntityManager manager = JpaDAOFactory.getManager();
		 EntityTransaction transaction = manager.getTransaction();
		 	transaction.begin();
		 manager.persist(categoria);
		 transaction.commit();
		 return true;
	}
	public Categoria getCategoria(int idRicerca) {
		 Query query = JpaDAOFactory
		 .getManager()
		 .createQuery("SELECT c from Categoria c WHERE c.id =:idRicerca ");
		query.setParameter("idRicerca", idRicerca);
		try {
			return (Categoria)query.getSingleResult();
		}catch (Exception e) {
			
		}
		return null;
		
	}
	
	public List<Categoria> getListCategoria() {
		 EntityManager manager = JpaDAOFactory.getManager();
		 return manager.createNamedQuery("Categoria.findAll").getResultList();
	 }
	
	
	public boolean existCategoria(String nome) {
			Categoria categoria = null;
			Query query = JpaDAOFactory
					 .getManager()
					 .createQuery("SELECT c from Categoria c WHERE c.nome like :nome ");
					query.setParameter("nome", nome);
			
			try {
				 categoria  =  (Categoria)query.getSingleResult();
				} catch (Exception e){
					return false;
				}
			  
			if(categoria != null)	
				return true;
			return false;
	}
	
	
	
	
}

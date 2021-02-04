package dao;

import dao.interfacce.*;
import dao.jdbc.*;
import dao.jpa.*;



public abstract class DAOFactory {
	public abstract UtenteDAO getUtenteDAO();
	public abstract AutomobileDAO getAutomobileDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract NoleggioDAO getNoleggioDAO();

	public static DAOFactory getDAOFactory() {
		return new JpaDAOFactory();
	}
	
}

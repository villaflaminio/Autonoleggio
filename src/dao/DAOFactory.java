package dao;

import dao.interfacce.*;
import dao.jdbc.*;



public abstract class DAOFactory {
	public abstract UtenteDAO getUtenteDAO();
	public abstract AutomobileDAO getAutomobileDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	
	public static DAOFactory getDAOFactory() {
		return new JdbcDAOFactory();
	}
}

package dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.DAOFactory;
import dao.interfacce.AutomobileDAO;
import dao.interfacce.CategoriaDAO;
import dao.interfacce.NoleggioDAO;
import dao.interfacce.UtenteDAO;

public class JdbcDAOFactory extends DAOFactory {
	

	@Override
	public UtenteDAO getUtenteDAO() {
		return JdbcUtenteDAO.getInstance();
	}

	@Override
	public AutomobileDAO getAutomobileDAO() {
		return JdbcAutomobileDAO.getInstance();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return JdbcCategoriaDAO.getInstance();
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			DataSource source = InitialContext.doLookup("java:comp/env/jdbc/autonoleggio");
			return source.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public NoleggioDAO getNoleggioDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}

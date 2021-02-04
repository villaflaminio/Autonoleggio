package dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.interfacce.UtenteDAO;
import model.Utente;

public class JdbcUtenteDAO implements UtenteDAO {
	private static JdbcUtenteDAO utenteDAO;
	
	private static final String TABELLA_UTENTE = "utente";
	private static final String ID = "id";

	private static final String UTENTE_NOME = "nome";
	private static final String UTENTE_COGNOME = "cognome";
	private static final String UTENTE_EMAIL = "email";
	private static final String UTENTE_PASSWORD = "password";
	private static final String UTENTE_DATANASCITA = "dateNascita";
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	private JdbcUtenteDAO() {}
	
	public static JdbcUtenteDAO getInstance() {
		if(utenteDAO == null)
			utenteDAO = new JdbcUtenteDAO();
		return utenteDAO;
	}

	@Override
	public Utente getUtente(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserisciUtente(Utente utente) {
		// TODO Auto-generated method stub
		return false;
	}

	
/*
	public Utente getUtente(String email, String password) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_UTENTE + " WHERE " +
					UTENTE_EMAIL + " = ? AND " + UTENTE_PASSWORD + " = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String nome = resultSet.getString(resultSet.findColumn(UTENTE_NOME));
				String cognome = resultSet.getString(resultSet.findColumn(UTENTE_COGNOME));
				String dateNascita = resultSet.getString(resultSet.findColumn(UTENTE_DATANASCITA));
				java.util.Date dataNascita = null; 
				try {
					dataNascita = dateFormat.parse(dateNascita);

				} 
				catch (ParseException e) {
				    e.printStackTrace();
				}

				utente = new Utente(id,email, password, nome, cognome,dataNascita);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return utente;
	}*/
	/*
	public boolean existUtente(String email) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_UTENTE + " WHERE " +
					UTENTE_EMAIL + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				return true;

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public Utente getUtenteByMail(String email) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_UTENTE + " WHERE " +
					UTENTE_EMAIL + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				String nome = resultSet.getString(resultSet.findColumn(UTENTE_NOME));
				String password = resultSet.getString(resultSet.findColumn(UTENTE_PASSWORD));

				String cognome = resultSet.getString(resultSet.findColumn(UTENTE_COGNOME));
				String dateNascita = resultSet.getString(resultSet.findColumn(UTENTE_DATANASCITA));

				utente = new Utente(email, password, nome, cognome,dateNascita);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return utente;
	}*/
	/*
	public Utente getUtenteById(int id) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_UTENTE + " WHERE " +
					ID + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				
				String email = resultSet.getString(resultSet.findColumn(UTENTE_EMAIL));
				String password = resultSet.getString(resultSet.findColumn(UTENTE_PASSWORD));
				String nome = resultSet.getString(resultSet.findColumn(UTENTE_NOME));
				String cognome = resultSet.getString(resultSet.findColumn(UTENTE_COGNOME));
				String dateNascita = resultSet.getString(resultSet.findColumn(UTENTE_DATANASCITA));
				java.util.Date dataNascita = null; 
				try {
					dataNascita = dateFormat.parse(dateNascita);

				} 
				catch (ParseException e) {
				    e.printStackTrace();
				}

				utente = new Utente(id,email, password, nome, cognome,dataNascita);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return utente;
	}

	
	public boolean inserisciUtente(Utente utente) {
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "INSERT INTO " + TABELLA_UTENTE + "("
					+ UTENTE_EMAIL + ","
					+ UTENTE_PASSWORD + ","
					+ UTENTE_NOME + ","
					+ UTENTE_COGNOME + ","
					+ UTENTE_DATANASCITA + ") VALUES (?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);			
			statement.setString(1, utente.getEmail());
			statement.setString(2, utente.getPassword());
			statement.setString(3, utente.getNome());
			statement.setString(4, utente.getCognome());
			statement.setDate(5, (Date) utente.getDateNascita());


			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	*/


}

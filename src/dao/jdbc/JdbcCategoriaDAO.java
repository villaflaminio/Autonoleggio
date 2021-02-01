package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfacce.CategoriaDAO;
import model.Categoria;
import model.Utente;

public class JdbcCategoriaDAO implements CategoriaDAO{
	
	private static JdbcCategoriaDAO instance;
	private static final String TABELLA_CATEGORIA = "categoria";
	private static final String ID = "id" ;
	private static final String NOME = "nome" ;
	private static final String PREZZO_GIORNALIERO = "prezzoGiornaliero";
	private static final String PREZZO_SETTIMANALE = "prezzoSettimanale";
	private static final String PREZZO_MENSILE = "prezzoMensile" ;
	private static final String DESCRIZIONE = "descrizione";
	
	private JdbcCategoriaDAO() {}
	
	public static JdbcCategoriaDAO getInstance() {
		if (instance == null) {
			instance = new JdbcCategoriaDAO();
		}
		return instance;
	}
	public boolean inserisciCategoria(Categoria categoria) {
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "INSERT INTO " + TABELLA_CATEGORIA + "("
					+ NOME + ","
					+ PREZZO_GIORNALIERO + ","
					+ PREZZO_SETTIMANALE + ","
					+ PREZZO_MENSILE + ","
					+ DESCRIZIONE + ") VALUES (?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, categoria.getNome());
			statement.setInt(2, categoria.getPrezzoGiornaliero());
			statement.setInt(3, categoria.getPrezzoSettimanale());
			statement.setInt(4, categoria.getPrezzoMensile());
			statement.setString(5, categoria.getDescrizione());

			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	public Categoria getCategoria(int idRicerca) {
		Categoria categoria = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_CATEGORIA + " WHERE " +
					ID + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idRicerca);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String nome= resultSet.getString(resultSet.findColumn(NOME));
				int prezzoGiornaliero = resultSet.getInt(resultSet.findColumn(PREZZO_GIORNALIERO));
				int prezzoSettimanale = resultSet.getInt(resultSet.findColumn(PREZZO_SETTIMANALE));
				int prezzoMensile = resultSet.getInt(resultSet.findColumn(PREZZO_MENSILE));
				String descrizione= resultSet.getString(resultSet.findColumn(DESCRIZIONE));
				
				categoria = new Categoria(id,nome,prezzoGiornaliero,prezzoSettimanale,prezzoMensile,descrizione);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return categoria;
	}
	
	public ArrayList<Categoria> getListCategoria() {
		ArrayList<Categoria> categoria = new ArrayList();
		Categoria c = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_CATEGORIA ;
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String nome= resultSet.getString(resultSet.findColumn(NOME));
				int prezzoGiornaliero = resultSet.getInt(resultSet.findColumn(PREZZO_GIORNALIERO));
				int prezzoSettimanale = resultSet.getInt(resultSet.findColumn(PREZZO_SETTIMANALE));
				int prezzoMensile = resultSet.getInt(resultSet.findColumn(PREZZO_MENSILE));
				String descrizione= resultSet.getString(resultSet.findColumn(DESCRIZIONE));
				
				c = new Categoria(id,nome,prezzoGiornaliero,prezzoSettimanale,prezzoMensile,descrizione);
				categoria.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return categoria;
	}
	public boolean existCateogira(String nome) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_CATEGORIA + " WHERE " +
					NOME + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				return true;

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
}

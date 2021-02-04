package dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

import model.Automobile;
import model.Categoria;
import model.Noleggio;
import model.Utente;

public class JdbcNoleggioDAO {
	private static JdbcNoleggioDAO instance;

	private static final String TABELLA_NOLEGGIO = "noleggio";
	private static final String TABELLA_AUTOMOBILE = "automobile";
	private static final String ID = "id" ;
	private static final String UTENTE = "utente" ;
	private static final String AUTOMOBILE = "automobile";
	private static final String DATAINIZIO = "dataInizio";
	private static final String DATAFINE = "dataFine" ;
	private static final String PREZZO = "prezzo" ;
	private static final String CATEGORIA = "categoria";

	JdbcAutomobileDAO dbAutomobile = JdbcAutomobileDAO.getInstance();
	JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();
	JdbcUtenteDAO dbUtente = JdbcUtenteDAO.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static JdbcNoleggioDAO getInstance() {
		if (instance == null) {
			instance = new JdbcNoleggioDAO();
		}
		return instance;
	}
	
	 /*
	public List<Noleggio> getNoleggiUtente(Utente u) {
		List<Noleggio> noleggi = new ArrayList<>();
		
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_NOLEGGIO + " WHERE " +  UTENTE + " = ?"+" ORDER BY dataInizio DESC" ;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, u.getId());

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				int id = set.getInt(set.findColumn(ID));
				int idAuto = set.getInt(set.findColumn(AUTOMOBILE));
				String dInizio = set.getString(set.findColumn(DATAINIZIO));
				String dFine = set.getString(set.findColumn(DATAFINE));
				double prezzo = set.getDouble(set.findColumn(PREZZO));

				Automobile a = dbAutomobile.getAutomobileById(idAuto);

				java.util.Date dataInizio = null; 
				java.util.Date dataFine = null;
				
				try {
					dataInizio = dateFormat.parse(dInizio);
					dataFine = dateFormat.parse(dFine);

				} 
				catch (ParseException e) {
				    e.printStackTrace();
				}
				
				Noleggio noleggio = new Noleggio(id, u, a, dataInizio, dataFine, prezzo);
				noleggi.add(noleggio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noleggi;
	}
	public List<Noleggio> getNoleggi() {
		List<Noleggio> noleggi = new ArrayList<>();
		
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_NOLEGGIO ;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				int id = set.getInt(set.findColumn(ID));
				int utente = set.getInt(set.findColumn(UTENTE));
				int idAuto = set.getInt(set.findColumn(AUTOMOBILE));
				String dInizio = set.getString(set.findColumn(DATAINIZIO));
				String dFine = set.getString(set.findColumn(DATAFINE));
				double prezzo = set.getDouble(set.findColumn(PREZZO));

				Automobile a = dbAutomobile.getAutomobileById(idAuto);
				Utente u = dbUtente.getUtenteById(utente);  

				java.util.Date dataInizio = null; 
				java.util.Date dataFine = null;
				
				try {
					dataInizio = dateFormat.parse(dInizio);
					dataFine = dateFormat.parse(dFine);

				} 
				catch (ParseException e) {
				    e.printStackTrace();
				}
				
				Noleggio noleggio = new Noleggio(id, u, a, dataInizio, dataFine, prezzo);
				noleggi.add(noleggio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noleggi;
	}
	
		

	
	
	public boolean inserisciNoleggio(Noleggio noleggio) {
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "INSERT INTO " + TABELLA_NOLEGGIO + "("
					+ ID + ","
					+ UTENTE + ","
					+ AUTOMOBILE + ","
					+ DATAINIZIO + ","
					+ DATAFINE + ","
					+ PREZZO + ") VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, noleggio.getId());
			statement.setInt(2, noleggio.getUtente().getId());
			statement.setInt(3, noleggio.getAutomobile().getId());
			statement.setDate(4, (Date) noleggio.getDataInizio());
			statement.setDate(5, (Date) noleggio.getDataFine());
			statement.setDouble(6, noleggio.getPrezzo());

			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	
	   
	public ArrayList<Automobile> getAutoDisponibili(Date dtInizio, Date dtFine ) {
			ArrayList<Automobile> automobile = new ArrayList();
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
					Automobile a = dbAutomobile.getAutomobileById(idAuto);
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
	
	public boolean deleteNoleggio(int idNoleggio) {
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = " DELETE FROM "+TABELLA_NOLEGGIO+" WHERE id = "+ " ?";
			     
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idNoleggio);
			return statement.execute();


			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public Noleggio getNoleggioById(int id) {
		
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT * FROM " + TABELLA_NOLEGGIO + " WHERE " +  ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				int idAuto = set.getInt(set.findColumn(AUTOMOBILE));
				String dInizio = set.getString(set.findColumn(DATAINIZIO));
				int idUtente = set.getInt(set.findColumn(UTENTE));

				String dFine = set.getString(set.findColumn(DATAFINE));
				double prezzo = set.getDouble(set.findColumn(PREZZO));

				Automobile a = dbAutomobile.getAutomobileById(idAuto);
				Utente u = dbUtente.getUtenteById(idUtente);
				java.util.Date dataInizio = null; 
				java.util.Date dataFine = null;
				
				try {
					dataInizio = dateFormat.parse(dInizio);
					dataFine = dateFormat.parse(dFine);

				} 
				catch (ParseException e) {
				    e.printStackTrace();
				}

				Noleggio noleggio = new Noleggio(id, u, a, dataInizio, dataFine, prezzo);
				return noleggio;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
*/
}

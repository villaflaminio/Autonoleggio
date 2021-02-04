package dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.interfacce.AutomobileDAO;
import model.Automobile;
import model.Automobile;
import model.Categoria;
import model.Utente;

public class JdbcAutomobileDAO implements AutomobileDAO {
	
	private static JdbcAutomobileDAO instance;
	private static final String TABELLA_AUTOMOBILE = "automobile";
	private static final String ID = "id" ;
	private static final String MARCA = "marca";
	private static final String TARGA = "targa";
	private static final String NUMERO_PORTE = "numeroPorte";
	private static final String CATEGORIA = "categoria";
	private static final String PRENOTABILE = "prenotabile";
	private static final String TABELLA_NOLEGGIO = "noleggio";

	JdbcCategoriaDAO dbCategoria = JdbcCategoriaDAO.getInstance();

	private JdbcAutomobileDAO() {}
	
	public static JdbcAutomobileDAO getInstance() {
		if (instance == null) {
			instance = new JdbcAutomobileDAO();
		}
		return instance;
	}
	/*
	public boolean inserisciAutomobile(Automobile automobile,Categoria categoria) {
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "INSERT INTO " + TABELLA_AUTOMOBILE + "("
					+ MARCA + ","
					+ TARGA + ","
					+ NUMERO_PORTE + ","					
					+ CATEGORIA + ") VALUES (?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, automobile.getMarca());
			statement.setString(2, automobile.getTarga());
			statement.setInt(3, automobile.getNumeroPorte());
			statement.setInt(4, categoria.getId());

			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	*/
	/* USO modificaAutomobile
	 * 
	public boolean modificaPrenotabile(int id,int prenotabile) {
		try(Connection connection = JdbcDAOFactory.getConnection()){

	         String query = "update automobile set prenotabile=? where id=? ";


			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, prenotabile);
			statement.setInt(2, id);

			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}*/
	/*
	public ArrayList<Automobile> getListAutomobile() {
		ArrayList<Automobile> automobile = new ArrayList();
		Automobile a = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE ;
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria= resultSet.getInt(resultSet.findColumn(CATEGORIA));

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);
				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
				
				automobile.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return automobile;
	}

	
	
	
	public ArrayList<Automobile> getAutomobiliPrenotabili() {
		ArrayList<Automobile> automobile = new ArrayList();
		Automobile a = null;
		
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					PRENOTABILE + " = 1 ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria= resultSet.getInt(resultSet.findColumn(CATEGORIA));

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);

				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
				
				automobile.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return automobile;
	}*/
	/*
	public ArrayList<Automobile> getAutomobiliCategoria(int idRicerca) {
		ArrayList<Automobile> automobile = new ArrayList();
		Automobile a = null;
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					CATEGORIA + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idRicerca);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria= resultSet.getInt(resultSet.findColumn(CATEGORIA));

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);

				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
				
				automobile.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return automobile;
	}
	*/
	/*
	public int getCategoriaFromId(int idRicerca) {
		
		int idCategoria = 0;
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					ID + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idRicerca);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				 idCategoria = resultSet.getInt(resultSet.findColumn(CATEGORIA));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return idCategoria;
	}
	
	public Automobile getAutomobileById(int idRicerca) {
		Automobile a = null;
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					ID + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idRicerca);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria= resultSet.getInt(resultSet.findColumn(CATEGORIA));

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);

				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	

	public boolean modificaAutomobile(int id, String targaInserita,String marcaInserita,int numeroPorteInserita) {
		try(Connection connection = JdbcDAOFactory.getConnection()){

	         String query = "update automobile set targa=? , marca=? , numeroPorte=?   where id=? ";


			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, targaInserita);
			statement.setString(2, marcaInserita);
			statement.setInt(3, numeroPorteInserita);
			statement.setInt(4, id);

			return statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Integer> getCategorieAutoDisponibili() {
		ArrayList<Integer> categorieConAutoDisponibili = new ArrayList();
		
		try(Connection connection = JdbcDAOFactory.getConnection()){

	         String query = "select distinct categoria from automobile where prenotabile=1 ";

	         PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();

				
					
				int categoria =resultSet.getInt(resultSet.findColumn(CATEGORIA)); 				
				categorieConAutoDisponibili.add(categoria);
				
				
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return categorieConAutoDisponibili;
	}
		*/
	 /*
	public ArrayList<Automobile> getAutoDisponibili(int idRicerca) {
		ArrayList<Automobile> automobile = new ArrayList();
		Automobile a = null;
		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					CATEGORIA + " = ? "+ " AND " +PRENOTABILE +" = 1" ;
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idRicerca);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn(ID));
				String marca= resultSet.getString(resultSet.findColumn(MARCA));
				String targa= resultSet.getString(resultSet.findColumn(TARGA));
				int numeroPorte= resultSet.getInt(resultSet.findColumn(NUMERO_PORTE));
				int idCategoria =resultSet.getInt(resultSet.findColumn(CATEGORIA)); 				

				boolean prenotabile= resultSet.getBoolean(resultSet.findColumn(PRENOTABILE));

				Categoria c = dbCategoria.getCategoria(idCategoria);

				a = new Automobile(id,marca,targa,numeroPorte,c,prenotabile);
				
				automobile.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return automobile;
	}
	
	public ArrayList<Automobile> getAutoDisponibili(Date dtInizio, Date dtFine,int idCategoria ) {
		ArrayList<Automobile> automobile = new ArrayList();
		try(Connection connection = JdbcDAOFactory.getConnection()) {
			String sql = "SELECT DISTINCT id FROM " + TABELLA_AUTOMOBILE +
						 "  WHERE id NOT IN(" +
						 " SELECT DISTINCT automobile FROM " + TABELLA_NOLEGGIO +
						 " WHERE dataInizio <= "+ " ?"+
						 " AND dataFine >= "+ "? ) AND prenotabile =1 AND categoria =" + " ?";
			     
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, dtFine);
			statement.setDate(2, dtInizio );
			statement.setInt(3, idCategoria );

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				
				int idAuto = set.getInt(set.findColumn(ID));
				Automobile a = getAutomobileById(idAuto);
				automobile.add(a);

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return automobile;
	} /*
	public boolean existAutomobile(String targa) {
		Utente utente = null;

		try(Connection connection = JdbcDAOFactory.getConnection()){
			String query = "SELECT * FROM " + TABELLA_AUTOMOBILE + " WHERE " +
					TARGA + " = ? ";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, targa);
			ResultSet resultSet = statement.executeQuery();

			if(resultSet.next()) {
				return true;

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	*/

	@Override
	public boolean inserisciAutomobile(Automobile automobile, Categoria categoria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Automobile> getListAutomobile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Automobile> getAutomobiliPerCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Automobile getAutomobile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCategorieAutoDisponibili() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existAutomobile(String targa) {
		// TODO Auto-generated method stub
		return false;
	}

}

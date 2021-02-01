package dataBase;

import model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Automobile;
import model.Categoria;
public class DataBase {
	private static DataBase instance;

	private static final String ID = "id" ;

	private DataBase() {

	}

	public static DataBase getInstance() {
		if(instance == null) {
			instance = new DataBase();
		}

		return instance;
	}

	
		

	
}
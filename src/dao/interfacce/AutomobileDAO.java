package dao.interfacce;

import java.util.ArrayList;

import model.Automobile;
import model.Categoria;

public interface AutomobileDAO {
	
	public boolean inserisciAutomobile(Automobile automobile,Categoria categoria);
	public boolean modificaPrenotabile(int id,int prenotabile);
	public ArrayList<Automobile> getListAutomobile();
	public ArrayList<Automobile> getAutomobiliCategoria(int idRicerca);

}

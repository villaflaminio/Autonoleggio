package dao.interfacce;

import java.util.ArrayList;
import java.util.List;

import model.Automobile;
import model.Categoria;

public interface AutomobileDAO {
	
	public boolean inserisciAutomobile(Automobile automobile,Categoria categoria);
	public List<Automobile> getListAutomobile();			
	public List<Automobile> getAutomobiliPerCategoria(Categoria categoria);		
	public Automobile getAutomobile(int id);
	public List<Integer> getCategorieAutoDisponibili();			
	public boolean existAutomobile(String targa);
	
}

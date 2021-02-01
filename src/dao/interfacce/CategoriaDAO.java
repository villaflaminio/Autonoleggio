package dao.interfacce;

import java.util.ArrayList;

import model.Categoria;

public interface CategoriaDAO{
	public boolean inserisciCategoria(Categoria categoria);
	public Categoria getCategoria(int idRicerca);
	public ArrayList<Categoria> getListCategoria();



	
}

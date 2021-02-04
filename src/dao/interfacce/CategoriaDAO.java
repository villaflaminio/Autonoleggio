package dao.interfacce;

import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public interface CategoriaDAO{
	public boolean inserisciCategoria(Categoria categoria);
	
	public Categoria getCategoria(int idRicerca);
	
	public List<Categoria> getListCategoria();

	public boolean existCategoria(String nome);
}

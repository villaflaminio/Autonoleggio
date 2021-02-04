package dao.interfacce;

import java.util.List;

import model.Noleggio;
import model.Utente;

public interface  NoleggioDAO {
	
	public List<Noleggio> getNoleggiUtente(Utente u);
	

	public List<Noleggio> getNoleggi();
	public boolean  inserisciNoleggio(Noleggio noleggio);
	public boolean  deleteNoleggio(Noleggio noleggio);
	public Noleggio getNoleggioById(int id);
	
}

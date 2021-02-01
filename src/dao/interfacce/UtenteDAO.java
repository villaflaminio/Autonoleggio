package dao.interfacce;

import model.Utente;

public interface UtenteDAO {
	public Utente getUtente(String email, String password);
	public boolean inserisciUtente(Utente utente);
}

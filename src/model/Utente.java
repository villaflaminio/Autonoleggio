package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dateNascita;
	

	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="utente")
	private List<Noleggio> noleggios;

	public Utente() {
	}
	public Utente(int id, String nome, String cognome, String email, String password, Date dateNascita) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.dateNascita = dateNascita;
	}
	public Utente( String nome, String cognome, String email, String password, Date dateNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.dateNascita = dateNascita;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateNascita() {
		return this.dateNascita;
	}

	public void setDateNascita(Date dateNascita) {
		this.dateNascita = dateNascita;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggios;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggios = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setUtente(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setUtente(null);

		return noleggio;
	}

}
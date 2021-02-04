package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the noleggio database table.
 * 
 */
@Entity
@NamedQuery(name="Noleggio.findAll", query="SELECT n FROM Noleggio n")
public class Noleggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Temporal(TemporalType.DATE)
	private Date dataFine;
	@Temporal(TemporalType.DATE)
	private Date dataInizio;
	private double prezzo;
	//bi-directional many-to-one association to Utente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="utente")
	private Utente utente;

	//bi-directional many-to-one association to Automobile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="automobile")
	private Automobile automobile;

	public Noleggio() {
	}
	
	public Noleggio(int id,Utente utente,Automobile automobile,Date dataInizio ,Date dataFine, double prezzo) {
		this.id=id;
		this.utente= utente;
		this.automobile = automobile;
		this.dataFine = dataFine;
		this.dataInizio = dataInizio;
		this.prezzo = prezzo;
		
	}
	public Noleggio(Utente utente,Automobile automobile,Date dataInizio ,Date dataFine, double prezzo) {
		this.utente= utente;
		this.automobile = automobile;
		this.dataFine = dataFine;
		this.dataInizio = dataInizio;
		this.prezzo = prezzo;
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Automobile getAutomobile() {
		return this.automobile;
	}

	public void setAutomobile(Automobile automobile) {
		this.automobile = automobile;
	}

}
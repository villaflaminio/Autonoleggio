package model;

import java.util.Date;

public class Noleggio {
	private int id;
	private Utente utente;
	private Automobile automobile;
	private Date dataInizio, dataFine;
	private double prezzo;
	public Noleggio(int id, Utente utente, Automobile automobile, Date dataInizio, Date dataFine,double prezzo) {
		this.id = id;
		this.utente = utente;
		this.automobile = automobile;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
	}

	public Noleggio(Utente utente, Automobile automobile, Date dataInizio, Date dataFine,double prezzo) {
		this.utente = utente;
		this.automobile = automobile;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Automobile getAutomobile() {
		return automobile;
	}

	public void setAutomobile(Automobile automobile) {
		this.automobile = automobile;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
    public double getPrezzo() {
		return prezzo;
	}
    public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
    

}

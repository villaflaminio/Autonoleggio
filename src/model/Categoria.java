package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String nome;
	private double prezzoGiornaliero;
	private double prezzoMensile;
	private double prezzoSettimanale;
	private String descrizione;

	//bi-directional many-to-one association to Automobile
	@OneToMany(mappedBy="categoria")
	private List<Automobile> automobiles;

	public Categoria() {
	}
	public Categoria(int id,String nome,double prezzoGiornaliero,double prezzoMensile,double prezzoSettimanale,String descrizione) {
	
		this.id = id;
		this.nome = nome;
		this.prezzoGiornaliero = prezzoGiornaliero;
		this.prezzoSettimanale = prezzoSettimanale;
		this.prezzoMensile = prezzoMensile;
		this.descrizione = descrizione;
				
	}
	public Categoria(String nome,double prezzoGiornaliero,double prezzoMensile,double prezzoSettimanale,String descrizione) {
		
		this.nome = nome;
		this.prezzoGiornaliero = prezzoGiornaliero;
		this.prezzoSettimanale = prezzoSettimanale;
		this.prezzoMensile = prezzoMensile;
		this.descrizione = descrizione;
				
	}
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzoGiornaliero() {
		return this.prezzoGiornaliero;
	}

	public void setPrezzoGiornaliero(double prezzoGiornaliero) {
		this.prezzoGiornaliero = prezzoGiornaliero;
	}

	public double getPrezzoMensile() {
		return this.prezzoMensile;
	}

	public void setPrezzoMensile(double prezzoMensile) {
		this.prezzoMensile = prezzoMensile;
	}

	public double getPrezzoSettimanale() {
		return this.prezzoSettimanale;
	}

	public void setPrezzoSettimanale(double prezzoSettimanale) {
		this.prezzoSettimanale = prezzoSettimanale;
	}

	public List<Automobile> getAutomobiles() {
		return this.automobiles;
	}

	public void setAutomobiles(List<Automobile> automobiles) {
		this.automobiles = automobiles;
	}

	public Automobile addAutomobile(Automobile automobile) {
		getAutomobiles().add(automobile);
		automobile.setCategoria(this);

		return automobile;
	}

	public Automobile removeAutomobile(Automobile automobile) {
		getAutomobiles().remove(automobile);
		automobile.setCategoria(null);

		return automobile;
	}

}
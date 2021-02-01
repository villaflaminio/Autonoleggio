package model;

public class Categoria {
    private int id;
    private String nome;

    private int prezzoGiornaliero;
    private int prezzoSettimanale;
    private int prezzoMensile;
    private String descrizione;
    
    public Categoria() {
    }

    public Categoria(String nome, int prezzoGiornaliero, int prezzoSettimanale, int prezzoMensile, String descrizione) {
        this.nome = nome;
        this.prezzoGiornaliero = prezzoGiornaliero;
        this.prezzoSettimanale = prezzoSettimanale;
        this.prezzoMensile = prezzoMensile;
        this.descrizione = descrizione;
    }
    public Categoria(int id, String nome, int prezzoGiornaliero, int prezzoSettimanale, int prezzoMensile, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.prezzoGiornaliero = prezzoGiornaliero;
        this.prezzoSettimanale = prezzoSettimanale;
        this.prezzoMensile = prezzoMensile;
        this.descrizione = descrizione;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrezzoGiornaliero() {
        return prezzoGiornaliero;
    }

    public void setPrezzoGiornaliero(int prezzoGiornaliero) {
        this.prezzoGiornaliero = prezzoGiornaliero;
    }

    public int getPrezzoSettimanale() {
        return prezzoSettimanale;
    }

    public void setPrezzoSettimanale(int prezzoSettimanale) {
        this.prezzoSettimanale = prezzoSettimanale;
    }

    public int getPrezzoMensile() {
        return prezzoMensile;
    }

    public void setPrezzoMensile(int prezzoMensile) {
        this.prezzoMensile = prezzoMensile;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}

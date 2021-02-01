package model;

import java.util.Date;

public class Utente {
    private int id;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private Date dateNascita;

    
    public Utente() {
    }

    public Utente(int id,String email, String password, String nome, String cognome,Date dateNascita) {
        this.id = id;
    	this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dateNascita =dateNascita;

    }
    public Utente(String email, String password, String nome, String cognome,Date dateNascita) {
    	this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dateNascita =dateNascita;

    }


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
	public Date getDateNascita() {
		return dateNascita;
	}
	public void setDateNascita(Date dateNascita) {
		this.dateNascita = dateNascita;
	}
}

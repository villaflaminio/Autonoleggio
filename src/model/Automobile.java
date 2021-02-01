package model;

public class Automobile {
    private int id;
    private String marca;
    private String targa;
    private int numeroPorte;
    private Categoria categoria;
    private boolean prenotabile;
    public Automobile() {

    }

    public Automobile(int id, String marca, String targa, int numeroPorte, Categoria categoria, boolean prenotabile) {
        this.id = id;
        this.marca = marca;
        this.targa = targa;
        this.numeroPorte = numeroPorte;
        this.categoria = categoria;
        this.prenotabile = prenotabile;
    }
    public Automobile(String marca, String targa, int numeroPorte, boolean prenotabile) {
        this.marca = marca;
        this.targa = targa;
        this.numeroPorte = numeroPorte;
        this.prenotabile = prenotabile;

    }
    public Automobile(int id,String marca, String targa, int numeroPorte, boolean prenotabile) {
        this.id = id;

        this.marca = marca;
        this.targa = targa;
        this.numeroPorte = numeroPorte;
        this.prenotabile = prenotabile;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public boolean isPrenotabile() {
    	return prenotabile;
    }
    public void setPrenotabile(boolean prenotabile) {
	this.prenotabile = prenotabile;
}
}

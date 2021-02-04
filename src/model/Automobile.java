package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the automobile database table.
 * 
 */
@Entity
@NamedQuery(name="Automobile.findAll", query="SELECT a FROM Automobile a")
public class Automobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String marca;
	private String targa;
	private int numeroPorte;
	private boolean prenotabile;


	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Noleggio
	@OneToMany(mappedBy="automobile")
	private List<Noleggio> noleggios;

	public Automobile() {
	}
	public Automobile(int id,String marca,String targa,int numeroPorte,Categoria categoria,boolean prenotabile) {
		this.id = id;
		this.marca = marca;
		this.targa = targa;
		this.numeroPorte = numeroPorte;
		this.categoria = categoria;
		this.prenotabile = prenotabile;		
	}
	public Automobile(String marca,String targa,int numeroPorte,Categoria categoria,boolean prenotabile) {
		this.marca = marca;
		this.targa = targa;
		this.numeroPorte = numeroPorte;
		this.categoria = categoria;
		this.prenotabile = prenotabile;		
	}
	public Automobile(String marca,String targa,int numeroPorte,boolean prenotabile) {
		this.marca = marca;
		this.targa = targa;
		this.numeroPorte = numeroPorte;
		this.prenotabile = prenotabile;		
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getNumeroPorte() {
		return this.numeroPorte;
	}

	public void setNumeroPorte(int numeroPorte) {
		this.numeroPorte = numeroPorte;
	}

	public boolean getPrenotabile() {
		return this.prenotabile;
	}

	public void setPrenotabile(boolean prenotabile) {
		this.prenotabile = prenotabile;
	}

	public String getTarga() {
		return this.targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Noleggio> getNoleggios() {
		return this.noleggios;
	}

	public void setNoleggios(List<Noleggio> noleggios) {
		this.noleggios = noleggios;
	}

	public Noleggio addNoleggio(Noleggio noleggio) {
		getNoleggios().add(noleggio);
		noleggio.setAutomobile(this);

		return noleggio;
	}

	public Noleggio removeNoleggio(Noleggio noleggio) {
		getNoleggios().remove(noleggio);
		noleggio.setAutomobile(null);

		return noleggio;
	}

}
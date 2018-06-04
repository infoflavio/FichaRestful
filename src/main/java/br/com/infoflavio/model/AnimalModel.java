package br.com.infoflavio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="animal")
public class AnimalModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static AnimalModel instance;
	
	public static AnimalModel getInstance() {
		if (instance == null)
			instance = new AnimalModel();
		return instance;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@ManyToOne 
	@JoinColumn(name="ficha_id")
	private FichaModel fichaModel;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FichaModel getFichaModel() {
		return fichaModel;
	}

	public void setFichaModel(FichaModel fichaModel) {
		this.fichaModel = fichaModel;
	}

	public AnimalModel() {
	}
	
	public AnimalModel create(FichaModel fichaModel, String nome){
		this.setId(0);
		this.setNome(nome);
		this.setFichaModel(fichaModel);
		
		return this;		
	}

	

}
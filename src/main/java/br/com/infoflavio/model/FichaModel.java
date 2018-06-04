package br.com.infoflavio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ficha")
public class FichaModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static FichaModel instance;
	
	public static FichaModel getInstance() {
		if (instance == null)
			instance = new FichaModel();
		return instance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_cadastro", nullable = false)
	private Date dtCadastro;

	@Column(name = "status", length = 11)
	String status;
	
	@Column(name = "observacao", length = 100)
	String observacao;
	
	@OneToMany (mappedBy="fichaModel", targetEntity=AnimalModel.class)
	private List<AnimalModel> animais; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<AnimalModel> getAnimais() {
		return animais;
	}

	public void setAnimais(List<AnimalModel> animais) {
		this.animais = animais;
	}

	public FichaModel create(Date dataCadastro, String status, String obs){
		this.setDtCadastro(dataCadastro);
		this.setStatus(status);
		this.setObservacao(obs);
		
		return this;		
	}
	
	public AnimalModel addAnimal(AnimalModel animal) {
		getAnimais().add(animal);
		animal.setFichaModel(this);
		
		return animal;
	}

	public AnimalModel removeAnimal(AnimalModel animal) {
		getAnimais().remove(animal);
		animal.setFichaModel(null);

		return animal;
	}

}

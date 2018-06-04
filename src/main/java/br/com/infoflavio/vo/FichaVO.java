package br.com.infoflavio.vo;

import java.util.Date;
import java.util.List;

public class FichaVO {
	
	private Integer id;
	private Date dataCadastro;
	private String status;
	private String observacao;
	private List<AnimalVO> animais;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	public List<AnimalVO> getAnimais() {
		return animais;
	}
	public void setAnimais(List<AnimalVO> animais) {
		this.animais = animais;
	}	
}
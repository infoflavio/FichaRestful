package br.com.infoflavio.vo;

public class AnimalVO {
	
	private Integer id;
	private String nome;
	private Integer idFicha;
	private Boolean selecionado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(Integer idFicha) {
		this.idFicha = idFicha;
	}
	public Boolean getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	
}
package br.com.infoflavio.exception;

public class RestFulBeanException extends RestFulException {
	
	/** Atributo <code>serialVersionUID</code> */
	private static final long serialVersionUID = 8864100005468488541L;
	
	private String detalhes;
	
	public RestFulBeanException(String mensagem) {
		super (mensagem);
		this.detalhes = mensagem;
	}
	
	@SuppressWarnings("rawtypes")
	public RestFulBeanException(String mensagem, Class classeOrigem) {
		super ( mensagem, classeOrigem );
		this.detalhes = mensagem;
	}
	
	@SuppressWarnings("rawtypes")
	public RestFulBeanException(String mensagem, Class classeOrigem, Throwable excecaoCapturada) {
		super ( mensagem, classeOrigem, excecaoCapturada);
		this.detalhes = mensagem;
	}
	
	public RestFulBeanException(String detalhes, String mensagem) {
		super (mensagem);
		this.detalhes = detalhes;
	}
	
	@SuppressWarnings("rawtypes")
	public RestFulBeanException(String detalhes, String mensagem, Class classeOrigem) {
		super ( mensagem, classeOrigem );
		this.detalhes = detalhes;
	}
	
	@SuppressWarnings("rawtypes")
	public RestFulBeanException(String detalhes, String mensagem, Class classeOrigem, Throwable excecaoCapturada) {
		super ( mensagem, classeOrigem, excecaoCapturada);
		this.detalhes = detalhes;
	}
	
	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
}

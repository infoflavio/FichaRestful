package br.com.infoflavio.exception;


public abstract class RestFulException extends RestFulBaseRuntimeException {

	/** Atributo <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1099597172070881385L;

	
	/** 
	 * Este construtor recebe apenas uma mensagem personalizada
	 * @param mensagem Mensagem personalizada da Exception
	 */
	public RestFulException(String mensagem) {
		super(mensagem);
	}	

	/**
	 * Construtor que recebe uma mensagem personalizada e a classe de origem
	 * da Exception
	 * @param mensagem Mensagem personalizada da Exception
	 * @param classeOrigem Classe onde a Exception foi originada
	 */
	@SuppressWarnings("rawtypes")
	public RestFulException(String mensagem, Class classeOrigem) {
		super(mensagem, classeOrigem);
	}

	
	/**
	 * Construtor que recebe uma mensagem personalizada, a classe de origem
	 * da Exception e a Exception que foi capturada e a qual será usada para
	 * fazer um printStackTrace no log
	 * @param mensagem Mensagem personalizada da Exception
	 * @param classeOrigem Classe onde a Exception foi originada
	 * @param excecaoCapturada Exception capturada
	 */
	@SuppressWarnings("rawtypes")
	public RestFulException(String mensagem, Class classeOrigem, Throwable excecaoCapturada) {
		super(mensagem, classeOrigem, excecaoCapturada);
	}

}

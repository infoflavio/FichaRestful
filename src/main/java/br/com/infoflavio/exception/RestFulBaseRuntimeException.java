package br.com.infoflavio.exception;

public class RestFulBaseRuntimeException extends RuntimeException {
	
	/** Atributo <code>serialVersionUID</code> */
	private static final long serialVersionUID = 5000918520213021576L;


	/** Método construtor onde se passa uma mensagem personalizada 
	 * @param mensagem Mensagem personalizada */
	public RestFulBaseRuntimeException(String mensagem) {
		this ( mensagem, null );
	}	
	
	@SuppressWarnings("rawtypes")
	public RestFulBaseRuntimeException(String mensagem, Class classeOrigem) {
		super ( mensagem );
	}	
	
	@SuppressWarnings("rawtypes")
	public RestFulBaseRuntimeException(String mensagem, Class classeOrigem, Throwable excecaoCapturada) {
		super ( mensagem );		
		//Class origem = (classeOrigem==null? this.getClass(): classeOrigem);
		//Throwable excecao = (excecaoCapturada==null? this: excecaoCapturada);
	}	
	
}
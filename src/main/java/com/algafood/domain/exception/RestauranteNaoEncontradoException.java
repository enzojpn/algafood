package com.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
 
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}


	public RestauranteNaoEncontradoException(Long restauranteId) {
		super(String.format("Não existe restaurante com a id igual a %d",restauranteId));
	}

 

	
	
}

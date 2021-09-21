package com.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
 
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
		 
	}
	
	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		super(String.format("Produto com a id %d não encontrado para o restaurante de codigo %d", produtoId ,restauranteId));
	}

}

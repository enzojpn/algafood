package com.algafood.domain.exception;

public class FotoProdutoNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	public FotoProdutoNaoEncontradaException(String msg) {
		super(msg);
	}
	public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId ) {
		super(String.format("Não foi possível encontrar foto do produto código %d do restaurante de id %d", produtoId, restauranteId));
	}


}

package com.algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
 
	public CidadeNaoEncontradoException(Long cidadeId) {
		super(String.format("Cidade não encontrada com o código %d", cidadeId));
	}

}

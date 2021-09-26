package com.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
	}

	public PermissaoNaoEncontradaException(Long permissaoId) {
		super(String.format("Permissao com a id %d não encontrada ", permissaoId));
	}

}

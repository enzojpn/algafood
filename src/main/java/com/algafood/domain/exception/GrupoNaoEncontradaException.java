package com.algafood.domain.exception;

public class GrupoNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradaException(String msg) {
		super(msg);
	}

	public GrupoNaoEncontradaException(Long grupoId) {
		super(String.format("Grupo com a id %d não encontrado", grupoId));
	}

}

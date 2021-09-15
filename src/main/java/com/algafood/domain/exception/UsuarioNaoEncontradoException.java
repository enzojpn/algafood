package com.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends NegocioException{
 
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}

	public UsuarioNaoEncontradoException(Long usuarioId) {
		super(String.format("Não existe usuario com a id %d", usuarioId));
	}

	
	
	
}

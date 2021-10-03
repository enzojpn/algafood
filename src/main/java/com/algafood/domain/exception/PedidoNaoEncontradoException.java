package com.algafood.domain.exception;

public class PedidoNaoEncontradoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}
	public PedidoNaoEncontradoException(Long pedidoId) {
		
		super(String.format("Pedido com a id %d não foi encontrado", pedidoId));
	}


	
}

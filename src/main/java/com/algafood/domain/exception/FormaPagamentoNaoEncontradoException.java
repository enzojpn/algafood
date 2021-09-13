package com.algafood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
 
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}


	public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
		super(String.format("Não existe forma de pagamento com a id igual a %d",formaPagamentoId));
	}

 

	
	
}

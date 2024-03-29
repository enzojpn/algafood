package com.algafood.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(Long pedidoId) {

		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.confirmar();
	}

	@Transactional
	public void entregar(Long pedidoId) {

		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.entregar();
	}

	@Transactional
	public void cancelar(Long pedidoId) {

		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		pedido.cancelar();
	}

}

package com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.PedidoModelAssembler;
import com.algafood.api.model.PedidoModel;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<PedidoModel> buscar(){
		List<Pedido> todosPedidos =  pedidoRepository.findAll();
		
		return pedidoModelAssembler.toCollectionModel(todosPedidos);
		
	}
	
}

package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.PedidoInputDisassembler;
import com.algafood.api.assembler.PedidoModelAssembler;
import com.algafood.api.model.PedidoModel;
import com.algafood.api.model.input.PedidoInput;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.repository.filter.PedidoFilter;
import com.algafood.domain.service.EmissaoPedidoService;
import com.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@GetMapping
	public List<PedidoModel> pesquisar(PedidoFilter filtro){
		List<Pedido> todosPedidos =  pedidoRepository.findAll(PedidoSpecs.usandofiltro(filtro));
		
		return pedidoModelAssembler.toCollectionModel(todosPedidos);
		
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoModel buscar(@PathVariable Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			
		Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
		
		//TODO pegar usuario autenticado
		novoPedido.setCliente(new Usuario());
		novoPedido.getCliente().setId(2L);
		
		novoPedido = emissaoPedidoService.emitir(novoPedido);
		return pedidoModelAssembler.toModel(novoPedido);
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage() , e);
		}
		
		
	}
}

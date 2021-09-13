package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.FormaPagamentoAssembler;
import com.algafood.api.model.FormaPagamentoModel;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;
import com.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(){
	List<FormaPagamento>	todasAsFormasDePagamento = formaPagamentoRepository.findAll();
		
		return formaPagamentoAssembler.toCollectionModel(todasAsFormasDePagamento);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamento buscar(@PathVariable Long formaPagamentoId){
		 FormaPagamento	FormasDePagamento =	formaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		return FormasDePagamento;
	}
	
}

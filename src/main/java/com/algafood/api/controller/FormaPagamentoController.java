package com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.FormaPagamentoAssembler;
import com.algafood.api.model.FormaPagamentoModel;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(){
	List<FormaPagamento>	todasAsFormasDePagamento = formaPagamentoRepository.findAll();
		
		return formaPagamentoAssembler.toCollectionModel(todasAsFormasDePagamento);
	}
	
}

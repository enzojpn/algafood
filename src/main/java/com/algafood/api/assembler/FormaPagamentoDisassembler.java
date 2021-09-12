package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.FormaPagamentoInput;
import com.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamento toDomainObjecto(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}
	
	public void copyToDomainObject (FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoInput,formaPagamento);
	}
	
}

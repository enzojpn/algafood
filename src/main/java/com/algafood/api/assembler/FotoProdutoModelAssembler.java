package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.FotoProdutoModel;
import com.algafood.api.model.ProdutoModel;
import com.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoModel toMoldel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoModel.class);
	}
	
	
	
}

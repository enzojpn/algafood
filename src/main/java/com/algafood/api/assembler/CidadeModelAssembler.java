package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.CidadeModel;
import com.algafood.api.model.RestauranteModel;
import com.algafood.core.modelmapper.ModelMapperConfig;
import com.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler {
	
	@Autowired
	ModelMapperConfig modelMapper;
	
	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.modelMapper().map(cidade, CidadeModel.class);

	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades){
		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
	}


}

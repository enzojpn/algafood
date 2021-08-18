package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.CozinhaModel;
import com.algafood.api.model.RestauranteModel;
import com.algafood.core.modelmapper.ModelMapperConfig;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {
	
	@Autowired
	ModelMapperConfig modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		return modelMapper.modelMapper().map(restaurante, RestauranteModel.class);

	}

	public List<RestauranteModel> toCollectionModel(List<Restaurante > restaurantes){
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}


}

package com.algafood.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.RestauranteInput;
import com.algafood.core.modelmapper.ModelMapperConfig;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {
	@Autowired
	ModelMapperConfig modelmapper;
	
	public  Restaurante toDomainObject(RestauranteInput restauranteInput) {

		return modelmapper.modelMapper().map(restauranteInput, Restaurante.class);
		
	}

}

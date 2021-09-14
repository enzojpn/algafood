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
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		//Para evitar problema JPA -> identifier of an instance of com.algafood.domain.model.Cozinha was altered from 3 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelmapper.modelMapper().map(restauranteInput, restaurante);
	}
	

}

package com.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algafood.api.model.CozinhaModel;
import com.algafood.api.model.RestauranteModel;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante > restaurantes){
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}

	public RestauranteModel toModel(Restaurante restaurante) {
		CozinhaModel cozinhaModel = new CozinhaModel();
		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setName(restaurante.getCozinha().getNome());

		RestauranteModel restauranteModel = new RestauranteModel();
		restauranteModel.setId(restaurante.getId());
		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteModel.setCozinha(cozinhaModel);
		return restauranteModel;
	}

}

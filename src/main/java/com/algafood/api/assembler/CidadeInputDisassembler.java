package com.algafood.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.input.CidadeInput;
import com.algafood.api.model.input.RestauranteInput;
import com.algafood.core.modelmapper.ModelMapperConfig;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Estado;
import com.algafood.domain.model.Restaurante;

@Component
public class CidadeInputDisassembler {
	@Autowired
	ModelMapperConfig modelmapper;
	
	public  Cidade toDomainObject(CidadeInput cidadeInput) {

		return modelmapper.modelMapper().map(cidadeInput, Cidade.class);
		
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		//Para evitar problema JPA -> identifier of an instance of com.algafood.domain.model.Cozinha was altered from 3 to 2
		cidade.setEstado(new Estado());
		
		modelmapper.modelMapper().map(cidadeInput, cidade);
	}
	

}

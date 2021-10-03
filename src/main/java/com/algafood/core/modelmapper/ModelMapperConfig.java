package com.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.api.model.EnderecoModel;
import com.algafood.api.model.input.ItemPedidoInput;
import com.algafood.domain.model.Endereco;
import com.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		TypeMap<Endereco, EnderecoModel> enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class,
				EnderecoModel.class);

		enderecoToEnderecoModelTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));

		return modelMapper;
	}

}

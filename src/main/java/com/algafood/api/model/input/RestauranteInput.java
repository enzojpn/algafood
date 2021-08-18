package com.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RestauranteInput {

	@NotBlank
	private String nome;
	
	@NotNull
	private BigDecimal taxaFrete;
	
	@NotNull
	private CozinhaInput cozinha;
	
	
}

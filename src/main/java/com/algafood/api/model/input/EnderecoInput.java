package com.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@NotBlank
	@Size(min = 9, max = 9, message = "Tamanho do Cep inválido")
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "formato do Cep inválido")
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@NotNull
	@Valid
	private CidadeIdInput cidade;
}

package com.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Endereco;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@JsonIgnore
	private Endereco endereco;

	private OffsetDateTime dataCadastro;

	private OffsetDateTime dataAtualizacao;

	@JsonIgnore
	private List<Produto> produtos;

}

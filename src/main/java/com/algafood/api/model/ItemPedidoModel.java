package com.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

	private Long produtoId;
	private String produtoNome;
	 
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
}

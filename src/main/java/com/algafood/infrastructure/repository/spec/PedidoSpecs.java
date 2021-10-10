package com.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algafood.domain.model.Pedido;
import com.algafood.domain.repository.filter.PedidoFilter;

public class PedidoSpecs {

	public static Specification<Pedido> usandofiltro(PedidoFilter pedidoFilter) {

		return (root, query, builder) -> {

			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			
			ArrayList<Predicate> predicate = new ArrayList<Predicate>();

			if (pedidoFilter.getClienteId() != null) {
				predicate.add(builder.equal(root.get("cliente"), pedidoFilter.getClienteId()));
			}
			if (pedidoFilter.getRestauranteId() != null) {
				predicate.add(builder.equal(root.get("restaurante"), pedidoFilter.getRestauranteId()));
			}

			if (pedidoFilter.getDataCriacaoInicio() != null) {
				predicate.add(
						builder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));
			}

			if (pedidoFilter.getDataCriacaoFim() != null) {
				predicate.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoFim()));
			}

			return builder.and(predicate.toArray(new Predicate[0]));
		};
	}

}

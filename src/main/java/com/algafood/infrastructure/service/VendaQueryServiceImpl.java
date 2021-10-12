package com.algafood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.StatusPedido;
import com.algafood.domain.model.dto.VendaDiaria;
import com.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
		Root<Pedido> root = query.from(Pedido.class);

		Expression<Date> expression = builder.function("date", Date.class, root.get("dataCriacao"));

		CompoundSelection<VendaDiaria> selection = builder.construct(VendaDiaria.class, expression,
				builder.count(root.get("id")), builder.sum(root.get("valorTotal")));
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filter.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
		}
		
		if (filter.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),filter.getDataCriacaoInicio()));
		}

		if (filter.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
		}
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(expression);

		return entityManager.createQuery(query).getResultList();
	}

}

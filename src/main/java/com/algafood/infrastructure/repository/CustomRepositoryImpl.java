package com.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algafood.domain.repository.CustomRepository;

public class CustomRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID>{

	EntityManager manager;
	
	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
this.manager= entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		String jpql = "from " +getDomainClass().getName();
		T entity =  manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
		return Optional.ofNullable(entity);
	}

}

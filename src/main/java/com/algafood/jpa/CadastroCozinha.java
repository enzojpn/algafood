package com.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	public Cozinha busca(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional	
	public Cozinha adiciona(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	public void remover(Cozinha cozinha) {
		cozinha = this.busca(cozinha.getId());
		manager.remove(cozinha);
	}
}

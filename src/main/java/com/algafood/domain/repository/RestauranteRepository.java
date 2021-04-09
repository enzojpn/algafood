package com.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import com.algafood.domain.model.Restaurante;

public interface RestauranteRepository  extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{

	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	 
}

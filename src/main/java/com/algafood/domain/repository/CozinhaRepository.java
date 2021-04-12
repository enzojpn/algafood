package com.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Cozinha;

public interface CozinhaRepository  extends CustomRepository<Cozinha, Long> {

}

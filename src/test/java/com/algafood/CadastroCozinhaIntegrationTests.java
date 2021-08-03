package com.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	CadastroCozinhaService cozinhaService;

	@Test
	public void testarCadastroDeCozinhaComSucesso() {
		// cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// acao
		novaCozinha = cozinhaService.salvar(novaCozinha);

		// validacao
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void testarCadastroCozinhaSemNome() {
		// cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		// acao

		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cozinhaService.salvar(novaCozinha);
		});

		// validacao
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class,
				() -> {
					cozinhaService.remove(4L);
				});
		assertThat(erroEsperado).isNotNull();

	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		CozinhaNaoEncontradoException erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradoException.class,
				() -> {
					cozinhaService.remove(32L);
				});
		assertThat(erroEsperado).isNotNull();

	}
}

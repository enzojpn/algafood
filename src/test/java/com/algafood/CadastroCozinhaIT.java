package com.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.HamcrestCondition;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.service.CadastroCozinhaService;
import com.algafood.utils.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Autowired 
	private DatabaseCleaner databaseCleaner;
	
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void testRetornar201_QuandoCadastrarCozinha() {

		RestAssured.given().
			body(" {\"nome\":\"Nórdica\"} ").contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when().post().
		then().statusCode(HttpStatus.CREATED.value());
	}
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConterCozinhaJaponesa_QuandoConsultarCozinha() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().body("nome", hasItem("Japonesa"));
	}


	@Test
	public void testeTer4Cozinhas_QuandoConsultarCozinha() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(4));
	}
	
	private void prepararDados() {
		Cozinha cozinha1 =  new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaService.salvar(cozinha1);
		Cozinha cozinha2 =  new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaService.salvar(cozinha2);
		Cozinha cozinha3 =  new Cozinha();
		cozinha3.setNome("Mexicana");
		cozinhaService.salvar(cozinha3);
		Cozinha cozinha4 =  new Cozinha();
		cozinha4.setNome("Japonesa");
		cozinhaService.salvar(cozinha4);
	}
	/*
	 * @Test public void testarCadastroDeCozinhaComSucesso() { // cenario Cozinha
	 * novaCozinha = new Cozinha(); novaCozinha.setNome("Chinesa");
	 * 
	 * // acao novaCozinha = cozinhaService.salvar(novaCozinha);
	 * 
	 * // validacao assertThat(novaCozinha).isNotNull();
	 * assertThat(novaCozinha.getId()).isNotNull(); }
	 * 
	 * @Test public void testarCadastroCozinhaSemNome() { // cenario Cozinha
	 * novaCozinha = new Cozinha(); novaCozinha.setNome(null);
	 * 
	 * // acao
	 * 
	 * ConstraintViolationException erroEsperado =
	 * Assertions.assertThrows(ConstraintViolationException.class, () -> {
	 * cozinhaService.salvar(novaCozinha); });
	 * 
	 * // validacao assertThat(erroEsperado).isNotNull(); }
	 * 
	 * @Test public void deveFalhar_QuandoExcluirCozinhaEmUso() {
	 * EntidadeEmUsoException erroEsperado =
	 * Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
	 * cozinhaService.remove(4L); }); assertThat(erroEsperado).isNotNull();
	 * 
	 * }
	 * 
	 * @Test public void deveFalhar_QuandoExcluirCozinhaInexistente() {
	 * CozinhaNaoEncontradoException erroEsperado =
	 * Assertions.assertThrows(CozinhaNaoEncontradoException.class, () -> {
	 * cozinhaService.remove(32L); }); assertThat(erroEsperado).isNotNull();
	 * 
	 * }
	 */
}

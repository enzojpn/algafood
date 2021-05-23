package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return cadastroRestaurante.listar();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {

	 
		return cadastroRestaurante.buscarOuFalhar(restauranteId);

	}

	@PostMapping
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> alterar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

		if (restauranteAtual.isPresent()) {
			try {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formasPagamento", "endereco",
						"dataCadastro", "produtos");

				cadastroRestaurante.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteAtual);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/consultar-nome")
	public List<Restaurante> consultarPorNome(String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}

	@GetMapping("/por-nome-frete")
	public List<Restaurante> porNomeFrete(String nome, BigDecimal freteInicial, BigDecimal freteFinal) {
		return restauranteRepository.findCriteria(nome, freteInicial, freteFinal);
	}

	@GetMapping("/por-frete-gratis")
	public List<Restaurante> comFreteGratis(String nome) {

		return restauranteRepository.findComFreteGratis(nome);

	}

	@GetMapping("/primeiro")
	public Restaurante primeiro() {
		return restauranteRepository.buscarPrimeiro().get();
	}

	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> alterarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos , HttpServletRequest request) {

		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual.get(), request);

		return alterar(restauranteId, restauranteAtual.get());
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest servletHttpRequest = new ServletServerHttpRequest(request);
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + " :  " + valorPropriedade + " = " + novoValor);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
		}catch (IllegalArgumentException e){
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest );
			
		}

	}

}

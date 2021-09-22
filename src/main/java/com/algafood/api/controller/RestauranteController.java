package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.RestauranteInputDisassembler;
import com.algafood.api.assembler.RestauranteModelAssembler;
import com.algafood.api.model.CozinhaModel;
import com.algafood.api.model.RestauranteModel;
import com.algafood.api.model.input.RestauranteInput;
import com.algafood.domain.exception.CozinhaNaoEncontradoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cozinha;
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
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;

	@GetMapping
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		return restauranteModelAssembler.toModel(restaurante);

	}

	@PostMapping
	public Restaurante salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			return cadastroRestaurante.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public RestauranteModel alterar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {

		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}

	}
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
	}
	
	@PutMapping("/{restauranteId}/ativar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void  ativar(@PathVariable Long restauranteId) {		
		cadastroRestaurante.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/ativar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void  inativar(@PathVariable Long restauranteId) {		
		cadastroRestaurante.inativar(restauranteId);
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

	/*
	 * @PatchMapping("/{restauranteId}") public ResponseEntity<?>
	 * alterarParcial(@PathVariable Long restauranteId, @RequestBody Map<String,
	 * Object> campos , HttpServletRequest request) {
	 * 
	 * Optional<Restaurante> restauranteAtual =
	 * restauranteRepository.findById(restauranteId);
	 * 
	 * if (restauranteAtual.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * 
	 * merge(campos, restauranteAtual.get(), request);
	 * 
	 * return alterar(restauranteId, restauranteAtual.get()); }
	 */
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
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest);

		}

	}

}

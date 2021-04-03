package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.RestauranteRepository;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return cadastroRestaurante.listar();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Optional<Restaurante>> buscar(@PathVariable Long restauranteId) {

		Optional<Restaurante> restaurante = repository.findById(restauranteId);
		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {
			cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> alterar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = repository.findById(restauranteId);

		if (restauranteAtual.isPresent()) {
			try {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");

				 cadastroRestaurante.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteAtual);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}

		return ResponseEntity.notFound().build();
	}
	
}

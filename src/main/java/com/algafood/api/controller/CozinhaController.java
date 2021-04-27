package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.config.RepositoryConfigurationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaID}")
	public ResponseEntity<Optional<Cozinha>> Busca(@PathVariable Long cozinhaID) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaID);

		if (!cozinha.isEmpty()) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		// Exemplo
		// HttpHeaders headers = new HttpHeaders();
		// headers.add(HttpHeaders.LOCATION, "http://algafood.com.local:8081/cozinhas");
		// return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adiciona(@RequestBody Cozinha cozinha) {

		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> altera(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			cadastroCozinha.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaAtual.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/primeiro")
	public Cozinha primeiro() {
		return cozinhaRepository.buscarPrimeiro().get();
	}
	/*
	 * @DeleteMapping("/{cozinhaId}") public ResponseEntity<Cozinha>
	 * remove(@PathVariable Long cozinhaId) { try {
	 * 
	 * cadastroCozinha.remove(cozinhaId); return ResponseEntity.noContent().build();
	 * 
	 * } catch (EntidadeEmUsoException e) { System.out.println(e); return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build(); } catch
	 * (EntidadeNaoEncontradaException e) { System.out.println(e); return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 * 
	 * }
	 * 
	 * }
	 */

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long cozinhaId) {
		cadastroCozinha.remove(cozinhaId);

	}

}

package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	CadastroCidadeService cadastroCidade;

	@Autowired
	CidadeRepository cidadeRepository;

	@ApiOperation("lista as cidades")
	@GetMapping
	public List<Cidade> listar() {
		return cadastroCidade.listar();
	}

	
	@ApiOperation("busca uma cidade por Id")
	@GetMapping("/{cidadeId}")
	public Cidade busca(
			@ApiParam(value = "Id de uma cidade", example = "1" )
			@PathVariable Long cidadeId) {
		return cadastroCidade.buscarOuFalhar(cidadeId);
	}

	@PostMapping
	public Cidade salvar(@RequestBody @Valid Cidade cidade) {
		try {
			cadastroCidade.salvar(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		return cidade;
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> alterar(@PathVariable Long cidadeId, @RequestBody @Valid Cidade cidade) {

		Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
		if ( cidadeAtual.isPresent()) {
			try {
				BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
				cadastroCidade.salvar(cidadeAtual.get());
				return ResponseEntity.ok(cidadeAtual.get());

			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}
		return ResponseEntity.notFound().build();

	}

}

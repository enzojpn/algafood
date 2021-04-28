package com.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MGS_CIDADE_NAO_ENCONTRADA = "Cidade não encontrada com o código %d";

	@Autowired
	CidadeRepository cidadeRepository;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade busca(Long cidadeId) {

		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		if (cidade.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format(MGS_CIDADE_NAO_ENCONTRADA, cidadeId));
		}

		return cidade.get();

	}

}

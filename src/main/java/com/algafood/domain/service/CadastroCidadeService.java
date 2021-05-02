package com.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private static final String MGS_CIDADE_NAO_ENCONTRADA = "Cidade não encontrada com o código %d";
	private static final String MGS_ESTADO_NAO_ENCONTRADO = "Estado não encontrado com o código %d";

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EstadoRepository estadoRepository;

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarOuFalhar(Long cidadeId) {

		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MGS_CIDADE_NAO_ENCONTRADA, cidadeId)));


	}

	public Cidade salva(Cidade cidade) {

		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MGS_CIDADE_NAO_ENCONTRADA, estadoId)));

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
}

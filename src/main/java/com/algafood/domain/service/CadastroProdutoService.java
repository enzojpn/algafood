package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algafood.domain.model.Produto;
import com.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto buscarOuFalhar(Long produtoID, Long restauranteId) {
		return produtoRepository.findById(restauranteId, produtoID)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoID, restauranteId));
	}

}

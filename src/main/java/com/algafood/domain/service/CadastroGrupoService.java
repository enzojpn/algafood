package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.GrupoNaoEncontradaException;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.model.Permissao;
import com.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	@Autowired
	private CadastroPermissaoService permissaoService;

	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradaException(grupoId));
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

		grupo.removerPermissao(permissao);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		grupo.adicionarPermissao(permissao);
	}

}

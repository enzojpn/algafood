package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired 
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario buscarOuFalhar(Long usuarioId) {
	
		return usuarioRepository.findById(usuarioId).orElseThrow(()-> new UsuarioNaoEncontradoException(usuarioId));
		
	}
}

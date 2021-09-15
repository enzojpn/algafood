package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.UsuarioInputDisassembler;
import com.algafood.api.assembler.UsuarioModelAssembler;
import com.algafood.api.model.UsuarioModel;
import com.algafood.api.model.input.UsuarioInput;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;
import com.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

	@GetMapping
	public List<UsuarioModel> listar() {
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}

	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		
		return usuarioModelAssembler.toModel(cadastroUsuarioService.buscarOuFalhar(usuarioId));
	}

	@PostMapping
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuarioRepository.save(usuario);
		return usuarioModelAssembler.toModel(usuario);
	}

}

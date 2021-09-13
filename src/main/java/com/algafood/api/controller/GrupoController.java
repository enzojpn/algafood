package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.model.Grupo;
import com.algafood.domain.repository.GrupoRepository;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@GetMapping
	public List<Grupo> listar(){
				
		return grupoRepository.findAll();
	}
	
	@GetMapping("/{grupoId}")	
	public Grupo buscar(@PathVariable Long grupoId) {
		Optional<Grupo> grupo = grupoRepository.findById(grupoId);
		
		return grupo.get();
	}
	
}

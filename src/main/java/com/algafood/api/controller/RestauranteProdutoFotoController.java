package com.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algafood.api.model.FotoProdutoModel;
import com.algafood.api.model.input.FotoProdutoInput;
import com.algafood.domain.model.FotoProduto;
import com.algafood.domain.model.Produto;
import com.algafood.domain.service.CadastroProdutoService;
import com.algafood.domain.service.CatalogoFotoProdutoService;
import com.algafood.domain.service.FotoStorageService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Autowired
	private CatalogoFotoProdutoService catalogoProdutoService;

	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

	@Autowired
	private FotoStorageService fotoStorageService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto fotoProduto = catalogoProdutoService.buscarOuFalhar(restauranteId, produtoId);
		return fotoProdutoModelAssembler.toMoldel(fotoProduto);
	}

	@GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId) {
		FotoProduto fotoProduto = catalogoProdutoService.buscarOuFalhar(restauranteId, produtoId);
		InputStream inputStream = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(inputStream));
	}

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId, restauranteId);

		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();

		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		foto.setTamanho(arquivo.getSize());

		FotoProduto fotoSalva = catalogoProdutoService.salvar(foto, arquivo.getInputStream());
		return fotoProdutoModelAssembler.toMoldel(fotoSalva);

	}

}

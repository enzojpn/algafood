package com.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	void armazenar(NovaFoto novaFoto);
	
	void deletar (String nomeArquivo);
	
	InputStream recuperar(String nomeArquivo);
	
	default void substituir(String FotoAntiga , NovaFoto novaFoto) {
		armazenar(novaFoto);
		
		if(FotoAntiga != null) deletar(FotoAntiga);
	}
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_"+ nomeOriginal;
	}

	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}
}

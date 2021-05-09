package com.algafood.api.exceptionhandler;

public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "entidade em uso"),
	NEGOCIO_EXCEPTION("/negocio-exception", "erro de negocio")	;

	private String title;
	private String uri;

	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://algafood.com.br" + path;
	}

	public String getTitle() {
		return title;
	}

	 
	public String getUri() {
		return uri;
	}
 
	
	
}

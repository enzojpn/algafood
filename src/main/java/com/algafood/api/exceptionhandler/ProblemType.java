package com.algafood.api.exceptionhandler;

public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "parâmetro inválido"),
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

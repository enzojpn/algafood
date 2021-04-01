package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.algafood.Algafood2Application;
import com.algafood.domain.model.Cozinha;

@Component
public class BuscaCozinha {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(Algafood2Application.class)
				.web(WebApplicationType.NONE).run(args);
		
		CadastroCozinha  cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

		Cozinha cozinha = cadastroCozinha.busca(2L);
		
		System.out.println(cozinha.getNome() + "ID : " + cozinha.getId());
		
	}
	
	
	
}

package com.algafood.jpa;
 

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.Algafood2Application;
import com.algafood.domain.model.Cozinha;

public class AdicionaCozinha {
	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(Algafood2Application.class)
				.web(WebApplicationType.NONE).run(args);

		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Mineira");

		cozinha1 = cadastroCozinha.adiciona(cozinha1);
		cozinha2 = cadastroCozinha.adiciona(cozinha2);

		System.out.printf("%s - %s", cozinha1.getId(), cozinha1.getNome());
		System.out.printf("%s - %s" , cozinha2.getId(), cozinha2.getNome());
	}
}

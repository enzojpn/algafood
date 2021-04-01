package com.algafood.jpa; 
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.Algafood2Application;
import com.algafood.domain.model.Cozinha;

public class ConsultaCozinha {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(Algafood2Application.class).web(WebApplicationType.NONE).run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		List<Cozinha> lista= cadastroCozinha.listar();
		
		for (Cozinha cozinha : lista) {
			System.out.println("cozinha " + cozinha.getNome());
		}
		

	
	}
}

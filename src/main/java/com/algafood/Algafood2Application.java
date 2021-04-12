package com.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algafood.domain.repository.CustomRepository;
import com.algafood.infrastructure.repository.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class Algafood2Application {

	public static void main(String[] args) {
		SpringApplication.run(Algafood2Application.class, args);
	}

}

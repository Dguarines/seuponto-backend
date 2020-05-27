package br.com.r4s.adm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AdministracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracaoApplication.class, args);
	}
}

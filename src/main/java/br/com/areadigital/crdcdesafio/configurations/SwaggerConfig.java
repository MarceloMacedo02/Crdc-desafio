package br.com.areadigital.crdcdesafio.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {


	@Bean
	public OpenAPI myOpenAPI() {
 

		Contact contact = new Contact();
		contact.setEmail("marcelo_macedo01@hotmail.com");
		contact.setName("CNAB");
		contact.setUrl("https://www.exemplosite.com");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Back-end Java Spring Boot ").version("1.0").contact(contact)
				.description("Utilizar o back-end Java Spring Boot \n"
						+ "para realizar o upload de arquivos CNAB")
				.termsOfService("https://www.siteteste/terms").license(mitLicense);

		return new OpenAPI().info(info);
	}
}

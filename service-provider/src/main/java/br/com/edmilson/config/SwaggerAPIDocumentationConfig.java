/**
 * 
 */
package br.com.edmilson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Edmilson Silva
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerAPIDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Avaliação de Desenvolvedor Java").description("Projeto para avaliação")
				.termsOfServiceUrl("").version("0.0.1-SNAPSHOT").contact(new Contact("Edmilson Silva", "", "")).build();
	}

	@Bean
	public Docket configureControllerPackageAndConvertors() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.edmilson.controller")).build()
				.apiInfo(apiInfo());
	}

}

package com.upday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * This class is responsible for Swagger2 configuration. Created by Amit
 * Mohapatra on 03/17/18.
 * <p/>
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.upday.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().license("Apache 2.0").version("Version 1.0").title("Upday API")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Amit Mohapatra", null, "amit.ricky1989@gmail.com"))
				.description("RESTful APIs to serve data to the upday app.").build();
	}

}

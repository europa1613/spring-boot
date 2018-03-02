package com.rest.services.restwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Andromeda", "http://www.andromeda.com", "europa1613@andromeda.com");
	public static final ApiInfo API_INFO = new ApiInfo("Restful Api", "Restful Api Desc", "1.0", "urn:tos",
			DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(API_INFO)
				.produces(PRODUCES_AND_CONSUMES)
				.consumes(PRODUCES_AND_CONSUMES)
				.select()
				.paths(PathSelectors.ant("/customers/*")) //only show apis with this uri
				.build();
	}
}

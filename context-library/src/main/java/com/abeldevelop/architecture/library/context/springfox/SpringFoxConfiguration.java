package com.abeldevelop.architecture.library.context.springfox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfiguration {
	
    private static final String APPLICATION_JSON_VALUE = "application/json";
    
    @Autowired
    private SpringfoxConfigurationProperties springfoxConfigurationProperties;
	
	@Bean
	public Docket apiDocket() {
	    Docket docket = new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage(springfoxConfigurationProperties.getApiPackage()))
			.paths(PathSelectors.any())
			.build()
			.produces(new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE)))
			.consumes(new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE)))
			.useDefaultResponseMessages(false)
			.apiInfo(getApiInfo());
	    return getTags(docket);
	}
	
	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            springfoxConfigurationProperties.getTitle(),
	            springfoxConfigurationProperties.getDescription(),
	            springfoxConfigurationProperties.getVersion(),
	            springfoxConfigurationProperties.getTermsOfServiceUrl(),
	            getContact(),
	            springfoxConfigurationProperties.getLicense(),
	            springfoxConfigurationProperties.getLicenseUrl(),
	            Collections.emptyList());
	}
	
	private Contact getContact() {
		return new Contact(springfoxConfigurationProperties.getContact().getName(), springfoxConfigurationProperties.getContact().getUrl(), springfoxConfigurationProperties.getContact().getEmail());
	}
	
	private Docket getTags(Docket docket) {
	    if(springfoxConfigurationProperties.getTags() == null || springfoxConfigurationProperties.getTags().isEmpty()) {
	        return docket;
	    }
	    if(springfoxConfigurationProperties.getTags().size() == 1) {
	        for(Map.Entry<String, String> entry : springfoxConfigurationProperties.getTags().entrySet()) {
	            docket.tags(new Tag(entry.getKey(), entry.getValue()));
	        }
	    } else {
	        Tag first = null;
	        List<Tag> remaining = new ArrayList<>();
	        int index = 0;
	        for(Map.Entry<String, String> entry : springfoxConfigurationProperties.getTags().entrySet()) {
                if(index == 0) {
                    first = new Tag(entry.getKey(), entry.getValue());
                } else {
                    remaining.add(new Tag(entry.getKey(), entry.getValue()));
                }
                index++;
            }
	        docket.tags(first, remaining.toArray(new Tag[remaining.size()]));
	    }
	    return docket;
    }
}

package com.abeldevelop.architecture.library.context.springfox;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "springfox")
public class SpringfoxConfigurationProperties {

    private String version;
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String apiPackage;
    private SpringfoxContactConfigurationProperties contact;
    private Map<String, String> tags;
    
}

package com.abeldevelop.architecture.library.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("error-code")
public class ErrorCodeArchitectureProperties {

    private String createErrorMessageRequestResourceNotNull;
    private String errorMessageResponseResourceNotNull;
    
    private String errorMessageIdNotNull;
    private String errorMessageServiceNameNotNull;
    private String errorMessageServiceNameSize;
    private String errorMessageLanguageCodeNotNull;
    private String errorMessageLanguageCodeSize;
    private String errorMessageCodeNotNull;
    private String errorMessageCodeSize;
    private String errorMessageMessageNotNull;
    private String errorMessageMessageSize;
    private String errorMessageAuditNotNull;
    
    private String errorMessageWithCodeAndLanguageCodeExist;
}

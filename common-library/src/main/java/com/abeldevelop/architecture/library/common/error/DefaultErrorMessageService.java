package com.abeldevelop.architecture.library.common.error;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class DefaultErrorMessageService implements ErrorMessageService {

    private Map<String, String> errorMessages;
    
    //When read data from microservices text-service pass the application name and the current language
    @Override
    public Optional<String> getMessage(String code) {
        if(errorMessages.containsKey(code)) {
            return Optional.of(errorMessages.get(code));
        } else {
            return Optional.empty();
        }
    }

    //TODO => The data in future come from microservice
    @Deprecated
    protected void addMessage(String code, String message) {
        if(errorMessages == null) {
            errorMessages = new HashMap<>();
        }
        errorMessages.put(code, message);
    }
}

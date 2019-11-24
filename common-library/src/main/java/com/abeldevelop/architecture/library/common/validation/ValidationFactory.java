package com.abeldevelop.architecture.library.common.validation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.factory.CommonFactory;

@Component
public class ValidationFactory extends CommonFactory<ValidationResource> {

    public void validate(Object toValidate) {
        Optional<ValidationResource> element = super.getElement(toValidate.getClass().getCanonicalName());
        if(element.isPresent()) {
            element.get().validate(toValidate);
        } else {
            throw new IllegalArgumentException("No exist validator for class: " + toValidate.getClass().getCanonicalName());
        }
    }
}

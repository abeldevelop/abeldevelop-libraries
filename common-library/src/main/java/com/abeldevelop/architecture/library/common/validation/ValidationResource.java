package com.abeldevelop.architecture.library.common.validation;

import com.abeldevelop.architecture.library.common.factory.CommonFactoryElement;

public interface ValidationResource extends CommonFactoryElement {

    public void validate(Object toValidate);
    
}

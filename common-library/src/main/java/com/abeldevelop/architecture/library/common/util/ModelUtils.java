package com.abeldevelop.architecture.library.common.util;

import java.util.UUID;

public class ModelUtils {

    private ModelUtils() {
        
    }
    
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
    
}

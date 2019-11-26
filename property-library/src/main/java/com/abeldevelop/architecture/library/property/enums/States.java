package com.abeldevelop.architecture.library.property.enums;

public enum States {

    ENABLED("ENABLED"),
    DISABLED("DISABLED"),
    DELETED("DELETED"),
    PENDING_TO_DELETED("PENDING_TO_DELETED");
    
    private String state;
    
    private States(String state) {
        this.state = state;
    }
    
    public String getState() {
        return state;
    }
    
    @Override
    public String toString() {
        return state;
    }
}

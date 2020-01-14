//package com.abeldevelop.architecture.library.test.integration.cucumber.common;
//
//import org.springframework.boot.web.server.LocalServerPort;
//
//import com.abeldevelop.architecture.library.test.integration.cucumber.config.CucumberTestContext;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//public class CucumberBaseSteps {
//
//    @LocalServerPort
//    private int port;
//
//    public String baseUrl() {
//        return "http://localhost:" + port;
//    }
//
//    public CucumberTestContext testContext() {
//        return CucumberTestContext.CONTEXT;
//    }
//    
//    @SuppressWarnings("unchecked")
//    protected <T extends Object> T getResponseClass(Class<?> clazz) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        return (T) mapper.readValue(testContext().getResponseBody(), clazz);
//    }
//    
//}

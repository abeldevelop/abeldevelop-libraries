//package com.abeldevelop.architecture.library.test.integration.cucumber.steps;
//
//
//import com.abeldevelop.architecture.library.test.integration.cucumber.common.CucumberBaseSteps;
//import com.abeldevelop.architecture.library.test.integration.cucumber.common.CucumberTestConstants;
//import com.abeldevelop.architecture.library.test.integration.cucumber.common.MakeRestCall;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class CucumberCommonSteps extends CucumberBaseSteps {
//
//    Class<?> requestResourceClazz;
//    
//    @Given("The endpoint {}")
//    public void the_endpoint(String endpoint) throws ClassNotFoundException {
//        testContext().setRequestBaseUrl(baseUrl());
//        testContext().setRequestEndpoint(endpoint.replaceAll("\"", ""));
//    }
//    
//    @And("The resource {}")
//    public void the_resource(String className) throws ClassNotFoundException {
//        testContext().getTestCase();
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "START STEP => the_resource {}", className);
//        requestResourceClazz = Class.forName(className);
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "END STEP => the_resource {}", className);
//    }
//    
//    @And("The input data")
//    public void the_input_data(DataTable datatable) throws JsonProcessingException {
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "START STEP => the_input_data");
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "datatable: {}", datatable);
//        Object requestBody = datatable.asList(requestResourceClazz).get(0);
//        String requestBodyInString = new ObjectMapper().writeValueAsString(requestBody);
//        testContext().setRequestBody(requestBodyInString);
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "requestBodyInString: " + requestBodyInString);
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "END STEP => the_input_data");
//    }
//
//    @When("Make {string} call")
//    public void make_call_to_the_endpoint(String method) throws Exception {
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "START STEP => Make {} call", method);
//        testContext().setRequestHttpMethod(method);
//        new MakeRestCall(testContext()).call(method);
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "END STEP => Make {} call", method);
//    }
//    
//}
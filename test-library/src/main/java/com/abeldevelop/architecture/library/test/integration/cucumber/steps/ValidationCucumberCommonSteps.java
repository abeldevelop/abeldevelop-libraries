//package com.abeldevelop.architecture.library.test.integration.cucumber.steps;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
//import com.abeldevelop.architecture.library.test.integration.cucumber.common.CucumberBaseSteps;
//import com.atlassian.oai.validator.OpenApiInteractionValidator;
//import com.atlassian.oai.validator.model.Request;
//import com.atlassian.oai.validator.model.Response;
//import com.atlassian.oai.validator.model.SimpleResponse;
//import com.atlassian.oai.validator.report.ValidationReport;
//import com.atlassian.oai.validator.report.ValidationReport.Message;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class ValidationCucumberCommonSteps extends CucumberBaseSteps {
//
//    private static final String SWAGGER_DOCS_ENDPOINT = "/v2/api-docs";
//    
//    @Then("I verify the {int} response code")
//    public void i_verify_the_response_code(Integer expectedStatusCode) {
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "START STEP => I verify the {} response code", expectedStatusCode);
//        assertThat(testContext().getResponseStatus()).as("Verify the response code").isEqualTo(expectedStatusCode);
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "END STEP => I verify the {} response code", expectedStatusCode);
//    }
//    
//    @And("If response code not {int} i verify the error response message {}")
//    public void i_verify_the_error_response_message(Integer expectedResponseCode, String errorResponseMessage) throws Exception {
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "START STEP => If response code not {} i verify the error response message {}", expectedResponseCode, errorResponseMessage);
//        if(testContext().getResponseStatus() != expectedResponseCode) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            try {
//                ErrorResponseResource errorResponseResource = mapper.readValue(testContext().getResponseBody(), ErrorResponseResource.class);
//                assertThat(errorResponseResource.getMessage()).as("Verify the error response message").isEqualTo(errorResponseMessage);
//                log.debug(CucumberTestConstants.INTEGRATION_TEST + "I verify the error response message {}.", errorResponseMessage);
//            } catch (Exception e) {
//                e.printStackTrace();
//                assertThat(false).as("Error parse the error response").isEqualTo(true);
//            }
//        }
//        log.debug(CucumberTestConstants.INTEGRATION_TEST + "END STEP => If response code not {} i verify the error response message {}", expectedResponseCode, errorResponseMessage);
//    }
//    
//    @And("If response code is {int} i verify the contract")
//    public void if_response_code_is_correct_i_verify_the_contract(Integer expectedStatusCode) {
//        if(testContext().getResponseStatus() == expectedStatusCode) {
//            OpenApiInteractionValidator validator = OpenApiInteractionValidator
//                    .createForSpecificationUrl(baseUrl() + SWAGGER_DOCS_ENDPOINT)
//                    .build();
//            Response response = SimpleResponse.Builder.status(expectedStatusCode)
//                    .withContentType("application/json")
//                    .withBody(testContext().getResponseBody())
//                    .build();
//            final ValidationReport report = validator.validateResponse(testContext().getRequestEndpoint(), getRequestMethod(testContext().getRequestHttpMethod()), response);
//            if(report.hasErrors()) {
//                for(Message message : report.getMessages()) {
//                    log.debug(CucumberTestConstants.INTEGRATION_TEST + "Contract errors => {}", message.getMessage());
//                }
//                assertThat(false).as("The response contract is not correct").isEqualTo(true);
//            }
//        }
//    }
//    
//    private Request.Method getRequestMethod(String method) {
//        Request.Method requestMethod = null; 
//        switch (method) {
//            case "POST":
//                requestMethod = Request.Method.POST;
//                break;
//            case "DELETE":
//                requestMethod = Request.Method.DELETE;
//                break;
//            case "GET":
//                requestMethod = Request.Method.GET;
//                break;
//            case "PUT":
//                requestMethod = Request.Method.PUT;
//                break;
//            
//            default:
//                requestMethod = null;
//                break;
//        }
//        return requestMethod;
//    }
//}

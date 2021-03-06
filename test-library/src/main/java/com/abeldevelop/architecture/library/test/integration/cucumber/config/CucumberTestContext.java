//package com.abeldevelop.architecture.library.test.integration.cucumber.config;
//
//import static java.lang.ThreadLocal.withInitial;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpHeaders;
//
//public enum CucumberTestContext {
//
//    CONTEXT;
//
//    private static final String TEST_CASE = "TEST_CASE";
//    private static final String REQUEST_BASE_URL = "REQUEST_BASE_URL";
//    private static final String REQUEST_ENDPOINT = "REQUEST_ENDPOINT";
//    private static final String REQUEST_HTTP_METHOD = "REQUEST_HTTP_METHOD";
//    private static final String REQUEST_HEADERS = "REQUEST_HEADERS";
//    private static final String REQUEST_PARAMS = "REQUEST_PARAMS";
//    private static final String REQUEST_BODY = "REQUEST_BODY";
//    private static final String RESPONSE_STATUS = "RESPONSE_STATUS";
//    private static final String RESPONSE_HEADERS = "RESPONSE_HEADERS";
//    private static final String RESPONSE_BODY = "RESPONSE_BODY";
//    private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);
//
//    @SuppressWarnings("unchecked")
//    private <T> T get(String name) {
//        return (T) testContexts.get().get(name);
//    }
//
//    private <T> T set(String name, T object) {
//        testContexts.get().put(name, object);
//        return object;
//    }
//
//    public void setRequestBaseUrl(String requestBaseUrl) {
//        set(REQUEST_BASE_URL, requestBaseUrl);
//    }
//    
//    public String getRequestBaseUrl() {
//        return get(REQUEST_BASE_URL);
//    }
//    
//    public void setRequestHttpMethod(String requestHttpMethod) {
//        set(REQUEST_HTTP_METHOD, requestHttpMethod);
//    }
//    
//    public String getRequestHttpMethod() {
//        return get(REQUEST_HTTP_METHOD);
//    }
//    
//    public void setTestCase(String testCase) {
//        set(TEST_CASE, testCase);
//    }
//
//    public String getTestCase() {
//        return get(TEST_CASE);
//    }
//
//    public void setRequestEndpoint(String requestEndpoint) {
//        set(REQUEST_ENDPOINT, requestEndpoint);
//    }
//
//    public String getRequestEndpoint() {
//        return get(REQUEST_ENDPOINT);
//    }
//
//    public void setRequestHeaders(HttpHeaders requestHeaders) {
//        set(REQUEST_HEADERS, requestHeaders);
//    }
//
//    public HttpHeaders getRequestHeaders() {
//        return get(REQUEST_HEADERS);
//    }
//
//    public void setRequestBody(Object requestBody) {
//        set(REQUEST_BODY, requestBody);
//    }
//
//    public String getRequestBody() {
//        return get(REQUEST_BODY);
//    }
//
//    public void setRequestParams(Object requestParams) {
//        set(REQUEST_PARAMS, requestParams);
//    }
//
//    public String getRequestParams() {
//        return get(REQUEST_PARAMS);
//    }
//
//    public void setResponseStatus(Object responseStatus) {
//        set(RESPONSE_STATUS, responseStatus);
//    }
//
//    public int getResponseStatus() {
//        return get(RESPONSE_STATUS);
//    }
//
//    public void setResponseHeaders(HttpHeaders responseHeaders) {
//        set(RESPONSE_HEADERS, responseHeaders);
//    }
//
//    public HttpHeaders getResponseHeaders() {
//        return get(RESPONSE_HEADERS);
//    }
//
//    public void setResponseBody(String responseBody) {
//        set(RESPONSE_BODY, responseBody);
//    }
//
//    public String getResponseBody() {
//        return get(RESPONSE_BODY);
//    }
//
//}
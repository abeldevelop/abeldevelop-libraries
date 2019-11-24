package com.abeldevelop.architecture.library.exception.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abeldevelop.architecture.library.common.error.ErrorMessageService;
import com.abeldevelop.architecture.library.context.Environments;
import com.abeldevelop.architecture.library.exception.AbelDevelopException;
import com.abeldevelop.architecture.library.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.exception.dto.ErrorResponseResource;
import com.abeldevelop.architecture.library.exception.dto.ErrorResponseResource.ErrorResponseResourceBuilder;
import com.abeldevelop.architecture.library.exception.mapper.StackTraceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

private static final String ERROR_LOG_PREFIX = "ErrorResponseResource: {}";
	
	private final Environment environment;
	private final ErrorMessageService errorMessages;
	private final StackTraceMapper stackTraceMapper;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if(ex instanceof AbelDevelopException) {
			status = ((AbelDevelopException) ex).getStatus();
		}
		ResponseEntity<Object> responseEntityError = handleResponseException(ex, status);
		if(status.is5xxServerError()) {
		    log.error(ERROR_LOG_PREFIX, responseEntityError.getBody());
		} else {
		    log.warn(ERROR_LOG_PREFIX, responseEntityError.getBody());
		}
		
		return responseEntityError;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("handleMethodArgumentNotValid");
	    return handleResponseException(ex, status);
	}

	@Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    log.info("handleHttpRequestMethodNotSupported");
	    return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleHttpMediaTypeNotSupported");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleHttpMediaTypeNotAcceptable");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleMissingPathVariable");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleMissingServletRequestParameter");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleServletRequestBindingException");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleConversionNotSupported");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleTypeMismatch");
        return handleResponseException(new BadRequestException("requestFieldValueNotValid", Arrays.asList(ex.getValue()), ex), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleHttpMessageNotReadable");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleHttpMessageNotWritable");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleMissingServletRequestPart");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleBindException");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("handleNoHandlerFoundException");
        return handleResponseException(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        log.info("handleAsyncRequestTimeoutException");
        return handleResponseException(ex, status);
    }

	private ResponseEntity<Object> handleResponseException(Exception ex, HttpStatus status) {
		return new ResponseEntity<>(createErrorResponseResource(ex, status), status);
	}
	
	private ErrorResponseResource createErrorResponseResource(Exception ex, HttpStatus status) {
		ErrorResponseResourceBuilder errorResponseResourceBuilder = ErrorResponseResource.builder();
		errorResponseResourceBuilder
					.timestamp(LocalDateTime.now())
					.status(status.value())
					.error(status.getReasonPhrase());	
		
		if(ex instanceof AbelDevelopException && ((AbelDevelopException) ex).getArguments() != null) {
			errorResponseResourceBuilder.message(messageFormatter(((AbelDevelopException) ex).getMessage(), ((AbelDevelopException) ex).getArguments()));
		} else if(ex instanceof MethodArgumentNotValidException) {
			errorResponseResourceBuilder.message(getMessage((MethodArgumentNotValidException) ex));
		} else {
			errorResponseResourceBuilder.message(getMessageFromProperties(ex.getMessage()));
		}
		
		addSensitiveInformation(ex, errorResponseResourceBuilder);
		return errorResponseResourceBuilder.build();
	}
	
	private String getMessageFromProperties(String code) {
		return errorMessages.getMessage(code).orElse(code);
	}
	
	private String messageFormatter(String message, List<Object> arguments) {
		return MessageFormatter.arrayFormat(getMessageFromProperties(message), arguments.toArray()).getMessage();
	}
	
	private String getMessage(MethodArgumentNotValidException exception) {
		FieldError fieldError = (FieldError) exception.getBindingResult().getAllErrors().get(0);
		return getMessageFromProperties(fieldError.getDefaultMessage());
	}
	
	private void addSensitiveInformation(Exception exception, ErrorResponseResourceBuilder errorResponseResourceBuilder) {
		if(!Arrays.asList(environment.getActiveProfiles()).contains(Environments.PRO.getEnvironment())) {
			if(exception.getCause() instanceof Exception) {
				errorResponseResourceBuilder.cause(createErrorResponseResource((Exception) exception.getCause(), HttpStatus.INTERNAL_SERVER_ERROR));
			}
			errorResponseResourceBuilder.detail(exception.getClass().getCanonicalName());
			errorResponseResourceBuilder.stackTrace(stackTraceMapper.map(exception.getStackTrace()));
		}
	}
	
}

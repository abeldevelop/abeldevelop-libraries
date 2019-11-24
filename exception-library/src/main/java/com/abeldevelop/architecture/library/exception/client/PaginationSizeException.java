package com.abeldevelop.architecture.library.exception.client;

import java.util.List;

//TODO Move to pagination-library
public class PaginationSizeException extends BadRequestException {

	private static final long serialVersionUID = -7238073841537015251L;

	public PaginationSizeException(String message) {
		super(message);
	}
	
	public PaginationSizeException(String message, List<Object> arguments) {
		super(message, arguments);
	}
	
	public PaginationSizeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PaginationSizeException(String message, Throwable cause, List<Object> arguments) {
		super(message, arguments, cause);
	}
	
}

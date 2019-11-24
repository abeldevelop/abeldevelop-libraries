package com.abeldevelop.architecture.library.pagination.mapper;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.exception.client.PaginationPageException;
import com.abeldevelop.architecture.library.exception.client.PaginationSizeException;
import com.abeldevelop.architecture.library.pagination.domain.PaginationIn;
import com.abeldevelop.architecture.library.pagination.domain.PaginationOut;
import com.abeldevelop.architecture.library.pagination.resource.PaginationResponseResource;

@Component
public class PaginationMapper {

    public static final String PAGINATION_MIN_PAGE_ERROR = "paginationMinPageError";
    public static final String PAGINATION_MIN_SIZE_ERROR = "paginationMinSizeError";
    
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final Integer MIN_PAGE = 1;
	private static final Integer MIN_PAGE_SIZE = 1;
	private static final Integer ADD_NUMBER_TO_PAGE = 1;
	private static final Integer SUBTRACT_NUMBER_TO_PAGE = 1;
	
	
	public PaginationIn map(Integer page, Integer size) {
		return PaginationIn.builder()
				.page(validatePageIn(page))
				.size(validateSizeIn(size))
				.build();
	}
	
	public PaginationResponseResource map(PaginationOut paginationResponse) {
		return PaginationResponseResource.builder()
				.page(paginationResponse.getPage() + ADD_NUMBER_TO_PAGE)
				.size(paginationResponse.getSize())
				.numberOfElements(paginationResponse.getNumberOfElements())
				.totalPages(calculateTotalPages(paginationResponse.getTotalElements(), paginationResponse.getSize()))
				.totalElements(paginationResponse.getTotalElements())
				.first(calculateIsFirstPage(paginationResponse.getPage() + ADD_NUMBER_TO_PAGE))
				.last(calculateIsLastPage(paginationResponse.getTotalElements(), paginationResponse.getSize(), paginationResponse.getPage() + ADD_NUMBER_TO_PAGE))
				.build();
	}
	
	public PaginationOut mapPageToPaginationOut(@SuppressWarnings("rawtypes") Page page) {
	    return PaginationOut.builder()
	            .page(page.getNumber())
	            .size(page.getSize())
	            .numberOfElements(page.getNumberOfElements())
	            .totalElements(page.getTotalElements())
	            .build();
	}
	
	private Integer validatePageIn(Integer page) {
		if(page == null) {
			return DEFAULT_PAGE_NUMBER;
		} else if(page.intValue() < MIN_PAGE) {
			throw new PaginationPageException(PAGINATION_MIN_PAGE_ERROR, Arrays.asList(MIN_PAGE));
		} else {
			return page - SUBTRACT_NUMBER_TO_PAGE;
		}
	}
	
	private Integer validateSizeIn(Integer size) {
		if(size == null) {
			return DEFAULT_PAGE_SIZE;
		} else if(size.intValue() < MIN_PAGE_SIZE) {
			throw new PaginationSizeException(PAGINATION_MIN_SIZE_ERROR, Arrays.asList(MIN_PAGE_SIZE));
		} else {
			return size;
		}
	}
	
	private int calculateTotalPages(long totalElements, int size) {
		return (int) Math.ceil((double)totalElements / (double)size);
	}
	
	private boolean calculateIsFirstPage(int page) {
		return MIN_PAGE == page;
	}
	
	private boolean calculateIsLastPage(long totalElements, int size, int page) {
		return (calculateTotalPages(totalElements, size) == page);
	}
}

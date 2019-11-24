package com.abeldevelop.architecture.library.pagination.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PaginationResult <T> {

	private PaginationOut pagination;
	private List<T> results;
	
}
package com.abeldevelop.architecture.library.common.domain.pagination;

public enum SortDirection {

	ASC("ASC"),
	DESC("DESC");
	
	private String type;
	
	private SortDirection(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
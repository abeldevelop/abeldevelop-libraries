package com.abeldevelop.architecture.library.pagination.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PageIn {

    private PaginationIn pagination;
    private SortIn sort;
    
}

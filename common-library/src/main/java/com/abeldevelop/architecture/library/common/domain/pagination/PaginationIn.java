package com.abeldevelop.architecture.library.common.domain.pagination;

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
public class PaginationIn {

    private Integer page;
    private Integer size;
    
}

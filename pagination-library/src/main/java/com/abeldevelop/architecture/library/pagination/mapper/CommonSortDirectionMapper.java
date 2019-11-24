package com.abeldevelop.architecture.library.pagination.mapper;

import org.springframework.data.domain.Sort;

import com.abeldevelop.architecture.library.pagination.domain.SortDirection;
import com.abeldevelop.architecture.library.pagination.domain.SortIn;

public abstract class CommonSortDirectionMapper<T> {

    public abstract SortIn map(T enumSort);
    
    public final Sort map(SortIn sortIn) {
        if(sortIn.getDirection().getType().equals(SortDirection.ASC.getType())) {
            return Sort.by(sortIn.getColumn()).ascending();
        } else {
            return Sort.by(sortIn.getColumn()).descending();
        }
    }
}

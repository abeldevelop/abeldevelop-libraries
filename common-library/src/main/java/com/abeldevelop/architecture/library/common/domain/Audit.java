package com.abeldevelop.architecture.library.common.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Audit {

    private LocalDateTime auditCreatedDate;
    private String auditCreatedUser;
    private LocalDateTime auditModifiedDate;
    private String auditModifiedUser;
    private LocalDateTime auditDeletedDate;
    private String auditDeletedUser;
    private Integer version;
    
}

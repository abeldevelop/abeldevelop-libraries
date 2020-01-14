package com.abeldevelop.architecture.library.common.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "audit_created_date", nullable = false)
    private LocalDateTime auditCreatedDate;
    
    @Column(name = "audit_created_user", nullable = false)
    private String auditCreatedUser;
    
    @Column(name = "audit_modified_date")
    private LocalDateTime auditModifiedDate;
    
    @Column(name = "audit_modified_user")
    private String auditModifiedUser;
    
    @Column(name = "audit_deleted_date")
    private LocalDateTime auditDeletedDate;
    
    @Column(name = "audit_deleted_user")
    private String auditDeletedUser;
    
    @Version
    @Column(name = "version", nullable = false)
    private Integer version;
    
}

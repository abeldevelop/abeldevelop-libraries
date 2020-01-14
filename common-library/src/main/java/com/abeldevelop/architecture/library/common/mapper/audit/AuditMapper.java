package com.abeldevelop.architecture.library.common.mapper.audit;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.domain.audit.Audit;
import com.abeldevelop.architecture.library.common.dto.audit.AuditResponseResource;
import com.abeldevelop.architecture.library.common.model.BaseEntity;

@Component
public class AuditMapper {

    public Audit mapEntityToDomain(BaseEntity baseEntity) {
        return Audit.builder()
                .auditCreatedDate(baseEntity.getAuditCreatedDate())
                .auditCreatedUser(baseEntity.getAuditCreatedUser())
                .auditModifiedDate(baseEntity.getAuditModifiedDate())
                .auditModifiedUser(baseEntity.getAuditModifiedUser())
                .auditDeletedDate(baseEntity.getAuditDeletedDate())
                .auditDeletedUser(baseEntity.getAuditDeletedUser())
                .version(baseEntity.getVersion())
                .build();
    }
    
    public AuditResponseResource mapDomainToResource(Audit audit) {
        return AuditResponseResource.builder()
                .auditCreatedDate(audit.getAuditCreatedDate())
                .auditCreatedUser(audit.getAuditCreatedUser())
                .auditModifiedDate(audit.getAuditModifiedDate())
                .auditModifiedUser(audit.getAuditModifiedUser())
                .auditDeletedDate(audit.getAuditDeletedDate())
                .auditDeletedUser(audit.getAuditDeletedUser())
                .version(audit.getVersion())
                .build();
    }
    
}

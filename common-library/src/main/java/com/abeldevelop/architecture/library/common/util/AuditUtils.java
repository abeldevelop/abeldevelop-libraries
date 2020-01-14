package com.abeldevelop.architecture.library.common.util;

import java.time.LocalDateTime;

import com.abeldevelop.architecture.library.common.model.BaseEntity;

public class AuditUtils {

    private AuditUtils() {
        
    }
    
    public static void fillAuditData(AuditType auditType, BaseEntity baseEntity, String user) {
        switch (auditType) {
            case CREATED:
                fillCreatedAuditData(baseEntity, user);
                break;
            case MODIFIED:
                fillModifiedAuditData(baseEntity, user);
                break;
            case DELETED:
                fillDeletedAuditData(baseEntity, user);
                break;
        }
    }
    
    private static void fillCreatedAuditData(BaseEntity baseEntity, String user) {
        baseEntity.setAuditCreatedDate(LocalDateTime.now());
        baseEntity.setAuditCreatedUser(user);
    }
    
    private static void fillModifiedAuditData(BaseEntity baseEntity, String user) {
        baseEntity.setAuditModifiedDate(LocalDateTime.now());
        baseEntity.setAuditModifiedUser(user);
        baseEntity.setAuditDeletedDate(null);
        baseEntity.setAuditDeletedUser(null);
    }

    private static void fillDeletedAuditData(BaseEntity baseEntity, String user) {
        baseEntity.setAuditModifiedDate(LocalDateTime.now());
        baseEntity.setAuditModifiedUser(user);
        baseEntity.setAuditDeletedDate(LocalDateTime.now());
        baseEntity.setAuditDeletedUser(user);
    }
    
    public enum AuditType {
        CREATED("CREATED"),
        MODIFIED("MODIFIED"),
        DELETED("DELETED")
        ;
        
        private String value;
        
        private AuditType(String value) {
            this.value = value;
        }
        
        public String getType() {
            return value;
        }
    }
}

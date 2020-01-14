package com.abeldevelop.architecture.library.common.service;

import com.abeldevelop.architecture.library.common.model.BaseEntity;
import com.abeldevelop.architecture.library.common.util.AuditUtils;

public class CommonService {

    public void fillAuditData(AuditUtils.AuditType auditType, BaseEntity baseEntity, String user) {
        AuditUtils.fillAuditData(auditType, baseEntity, "user");
    }
    
    public void fillAuditData(AuditUtils.AuditType auditType, BaseEntity baseEntity) {
        AuditUtils.fillAuditData(auditType, baseEntity, "user");
    }
    
}

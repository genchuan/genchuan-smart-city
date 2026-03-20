package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 14:34
 */
@Data
public class InstitutionInspectionDetailDO extends InstitutionInspectionDO {
    /**
     * 关联public_institution.institution_id
     */
    private String institutionName;
    /**
     * 关联task.task_id
     */
    private String taskId;
    /**
     * 关联sys_task_type.id
     */
    private String taskTypeName;
    /**
     * 关联sys_user.id
     */
    private String reportName;
    /**
     * 关联sys_user.id
     */
    private String inspectName;
}
package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 14:17
 */
@Data
public class InstitutionProblemDetailDO extends InstitutionProblemDO {
    /**
     * 关联public_institution.institution_id
     */
    private String institutionName;
    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeName;
    /**
     * 关联sys_user.id
     */
    private String reportName;
    /**
     * 关联sys_dept.id
     */
    private String deptName;
    /**
     * 关联sys_user.id
     */
    private String handleName;
}
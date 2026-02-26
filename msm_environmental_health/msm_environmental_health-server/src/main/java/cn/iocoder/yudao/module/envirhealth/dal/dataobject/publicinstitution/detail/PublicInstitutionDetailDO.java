package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 13:59
 */
@Data
public class PublicInstitutionDetailDO extends PublicInstitutionDO {
    /**
     * 关联sys_institution_type.id
     */
    private String institutionTypeName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id
     */
    private String managerName;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusName;
}
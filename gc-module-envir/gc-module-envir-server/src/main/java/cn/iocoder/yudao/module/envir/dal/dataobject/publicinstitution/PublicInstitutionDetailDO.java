package cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/11 17:08
 */
@Data
public class PublicInstitutionDetailDO extends PublicInstitutionDO {
    /**
     * 机构类型（关联sys_institution_type.sys_institution_type_id）
     */
    private String institutionTypeName;
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerName;
}
package cn.iocoder.yudao.module.envir.dal.dataobject.user;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/12 10:05
 */
@Data
public class UserDetailDO extends UserDO {
    /**
     * 岗位类型（关联sys_job_type.sys_job_type_id）
     */
    private String jobTypeName;
    /**
     * 所属班组（关联sys_team.sys_team_id）
     */
    private String teamName;
    /**
     * 负责区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 人员状态（关联sys_person_status.sys_person_status_id）
     */
    private String personStatusName;
}
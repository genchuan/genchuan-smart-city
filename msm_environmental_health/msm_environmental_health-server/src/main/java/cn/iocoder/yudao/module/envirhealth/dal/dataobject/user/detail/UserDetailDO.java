package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/25 17:01
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
     * 人员状态（关联sys_person_status.sys_person_status_id）
     */
    private String personStatusName;
    /**
     * 负责区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createName;
    /**
     * 更新人ID（关联sys_user.user_id）
     */
    private String updateName;
}
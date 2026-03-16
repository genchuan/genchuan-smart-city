package cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 14:54
 */
@Data
public class TaskDetailWithPublicInstitutionDO extends TaskDO {
    /**
     * 关联sys_task_type.sys_task_type_id
     */
    private String taskTypeName;
    /**
     * 关联public_institution.id（公共机构相关任务）
     */
    private String institutionName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id（处置人员）
     */
    private String handleName;
}
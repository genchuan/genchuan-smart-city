package cn.iocoder.yudao.module.envirhealth.dal.dataobject.task;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 11:59
 */
@Data
public class TaskDetailWithGarbageTransferDO extends TaskDO {
    /**
     * 关联sys_task_type.sys_task_type_id
     */
    private String taskTypeName;
    /**
     * 关联garbage_transfer.id（转运站相关任务）
     */
    private String transferName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id（处置人员）
     */
    private String handleName;
}
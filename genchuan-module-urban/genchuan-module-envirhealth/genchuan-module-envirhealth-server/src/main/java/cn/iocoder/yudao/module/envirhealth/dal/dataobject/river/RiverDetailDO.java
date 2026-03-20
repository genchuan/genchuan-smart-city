package cn.iocoder.yudao.module.envirhealth.dal.dataobject.river;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/25 15:41
 */
@Data
public class RiverDetailDO extends RiverDO {
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
    /**
     * 关联sys_cleaning_type.id
     */
    private String cleaningTypeName;
    /**
     * 负责人员IDs，JSON
     */
    @JsonIgnore
    private String staffsNameStr;

    private List<String> staffsName;

    /**
     * 保洁工具IDs，JSON
     */
    @JsonIgnore
    private String toolsNameStr;

    private List<String> toolsName;

    /**
     * 关联sys_monitor_type.id
     */
    private String monitorTypeName;

    /**
     * 关联sys_user.id
     */
    private String monitorName;

    /**
     * 关联sys_monitor_status.id
     */
    private String monitorStatusName;

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

    /**
     * 关联sys_handle_status.id
     */
    private String handleStatusName;

    /**
     * 关联sys_task_type.id
     */
    private String taskTypeName;

    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName = StringSplitUtils.splitToStringList(staffsNameStr);
    }

    public void setToolsNameStr(String toolsNameStr) {
        this.toolsNameStr = toolsNameStr;
        this.toolsName = StringSplitUtils.splitToStringList(toolsNameStr);
    }
}
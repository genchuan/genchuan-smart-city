package cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/25 9:27
 */
@Data
public class UrbanVillageDetailDO extends UrbanVillageDO {
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
     * 负责人员IDs，JSON
     */
    @JsonIgnore
    private String staffsNameStr;

    private List<String> staffsName;

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
     * 关联sys_user.id
     */
    private String reviewName;

    /**
     * 关联sys_review_result.id
     */
    private String reviewResultName;

    /*关联sys_plan_status*/
    private String planStatusName;

    /*关联sys_problem_type*/
    private String problemTypeName;

    /*关联sys_task_type*/
    private String taskTypeName;

    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName= StringSplitUtils.splitToStringList(staffsNameStr);
    }
}
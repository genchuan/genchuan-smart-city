package cn.iocoder.yudao.module.envirhealth.dal.dataobject.market;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/25 11:27
 */
@Data
public class MarketDetailDO extends MarketDO {
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
     * 垃圾类型IDs，JSON
     */
    @JsonIgnore
    private String garbageTypesNameStr;

    private List<String> garbageTypesName;

    /**
     * 关联sys_vehicle.id
     */
    private String vehicleName;

    /**
     * 关联sys_user.id
     */
    private String checkName;

    /**
     * 关联sys_handle_status
     */
    private String handleStatusName;

    /**
     * 关联sys_check_result
     */
    private String checkResultName;

    /**
     * 关联sys_task_type
     */
    private String taskTypeName;

    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName = StringSplitUtils.splitToStringList(staffsNameStr);
    }

    public void setGarbageTypesNameStr(String garbageTypesNameStr) {
        this.garbageTypesNameStr = garbageTypesNameStr;
        this.garbageTypesName = StringSplitUtils.splitToStringList(garbageTypesNameStr);
    }
}
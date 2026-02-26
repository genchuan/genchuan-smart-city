package cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envirhealth.util.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 15:24
 */
@Data
public class CommercialStreetDetailDO extends CommercialStreetDO {
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
     * 保洁人员IDs，JSON 关联sys_user.id
     */
    @JsonIgnore
    private String cleanersNameStr;

    private List<String> cleanersName;

    /**
     * 设施类型IDs，JSON
     */
    @JsonIgnore
    private String facilitysNameStr;

    private List<String> facilitysName;

    /**
     * 关联sys_user.id
     */
    private String reportName;

    /**
     * 关联sys_user.id
     */
    private String handleName;

    /**
     * 关联sys_maintain_status.id
     */
    private String maintainStatusName;

    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeName;

    /**
     * 关联sys_handle_status.id
     */
    private String handleStatusName;

    /**
     * 负责车辆ID，关联sys_vehicle.sys_vehicle_id（支持车辆钻取）
     */
    private String vehicleName;
    /**
     * 负责人员IDs，JSON格式，关联sys_user.user_id
     */
    @JsonIgnore
    private String staffsNameStr;

    private List<String> staffsName;
    /**
     * 收运计划状态ID，关联sys_plan_status.sys_plan_status_id（支持状态筛选钻取）
     */
    private String planStatusName;

    private String taskTypeName;

    public void setCleanersNameStr(String cleanersNameStr) {
        this.cleanersNameStr = cleanersNameStr;
        this.cleanersName = StringSplitUtils.splitToStringList(cleanersNameStr);
    }

    public void setFacilitysNameStr(String facilitysNameStr) {
        this.facilitysNameStr = facilitysNameStr;
        this.facilitysName = StringSplitUtils.splitToStringList(facilitysNameStr);
    }

    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName = StringSplitUtils.splitToStringList(staffsNameStr);
    }
}
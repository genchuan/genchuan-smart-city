package cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 17:02
 */
@Data
public class ParkDetailDO extends ParkDO {
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
     * 绿化品类IDs，JSON
     */
    @JsonIgnore
    private String greenTypesNameStr;

    private List<String> greenTypesName;

    /**
     * 养护人员IDs，JSON
     */
    @JsonIgnore
    private String greenStaffsNameStr;

    private List<String> greenStaffsName;

    /**
     * 关联sys_vehicle.id
     */
    private String vehicleName;

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

    private String planStatusName;

    private String taskTypeName;


    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName = StringSplitUtils.splitToStringList(staffsNameStr);
    }

    public void setGreenTypesNameStr(String greenTypesNameStr) {
        this.greenTypesNameStr = greenTypesNameStr;
        this.greenTypesName = StringSplitUtils.splitToStringList(greenTypesNameStr);
    }

    public void setGreenStaffsNameStr(String greenStaffsNameStr) {
        this.greenStaffsNameStr = greenStaffsNameStr;
        this.greenStaffsName = StringSplitUtils.splitToStringList(greenStaffsNameStr);
    }

    public void setFacilitysNameStr(String facilitysNameStr) {
        this.facilitysNameStr = facilitysNameStr;
        this.facilitysName = StringSplitUtils.splitToStringList(facilitysNameStr);
    }
}
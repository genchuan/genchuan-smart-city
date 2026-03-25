package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/13 16:09
 */
@Data
public class GarbageCollectionDetailDO extends GarbageCollectionDO {
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeName;
    /**
     * 收运区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 负责车辆（关联sys_vehicle.sys_vehicle_id）
     */
    private String vehicleLicensePlate;
    /**
     * 计划状态（关联sys_plan_status.sys_plan_status_id）
     */
    private String planStatusName;
    /**
     * 负责人员IDs，JSON
     */
    @JsonIgnore
    private String usersNameStr;

    private List<String> usersName;

    /**
     * 收运点位IDs，JSON
     */
    @JsonIgnore
    private String pointsNameStr;

    private List<String> pointsName;

    public void setUsersNameStr(String usersNameStr) {
        this.usersNameStr = usersNameStr;
        this.usersName = StringSplitUtils.splitToStringList(usersNameStr);
    }

    public void setPointsNameStr(String pointsNameStr) {
        this.pointsNameStr = pointsNameStr;
        this.pointsName = StringSplitUtils.splitToStringList(pointsNameStr);
    }
}
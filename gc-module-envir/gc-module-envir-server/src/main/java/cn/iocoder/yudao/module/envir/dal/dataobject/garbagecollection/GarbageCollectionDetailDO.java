package cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/10 16:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GarbageCollectionDetailDO extends GarbageCollectionDO{
    /**
     * 收运品类（关联sys_garbage_type.id）
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
     * 负责人员，多个用逗号分隔
     */
    private String usersNameStr;
    private List<String> usersName;
    /**
     * 计划状态（关联sys_plan_status.sys_plan_status_id）
     */
    private String planStatusName;

    /**
     * 在 setter 方法中将字符串转换为列表
     */
    public void setUsersNameStr(String usersNameStr) {
        this.usersNameStr = usersNameStr;
        if (usersNameStr != null && !usersNameStr.trim().isEmpty()) {
            this.usersName = Arrays.asList(usersNameStr.split(","));
        } else {
            this.usersName = new ArrayList<>();
        }
    }
}
package cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/11 14:17
 */
@Data
public class PublicToiletDetailDO extends PublicToiletDO{
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaName;

    /**
     * 配套设施名称字符串（从数据库查询得到，逗号分隔）
     */
    private String facilityNameStr;

    /**
     * 配套设施名称列表
     */
    private List<String> facilityName;

    /**
     * 运营状态（关联sys_operation_status.sys_operation_status_id）
     */
    private String operationStatusName;

    /**
     * 负责人（关联sys_user.id）
     */
    private String managerName;

    /**
     * 在 setter 方法中将字符串转换为列表
     */
    public void setFacilityNameStr(String facilityNameStr) {
        this.facilityNameStr = facilityNameStr;
        if (facilityNameStr != null && !facilityNameStr.trim().isEmpty()) {
            this.facilityName = Arrays.asList(facilityNameStr.split(","));
        } else {
            this.facilityName = new ArrayList<>();
        }
    }
}
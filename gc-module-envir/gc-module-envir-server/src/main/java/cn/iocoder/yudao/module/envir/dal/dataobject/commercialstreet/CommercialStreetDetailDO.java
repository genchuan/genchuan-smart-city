package cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/11 17:26
 */
@Data
public class CommercialStreetDetailDO extends CommercialStreetDO {
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 设施类型（关联sys_facility.sys_facility_id，多个用逗号分隔）
     */
    private String facilitysNameStr;
    private List<String> facilitysName;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerName;

    public void setFacilitysNameStr(String facilitysNameStr) {
        this.facilitysNameStr = facilitysNameStr;
        if (facilitysNameStr != null && !facilitysNameStr.trim().isEmpty()) {
            this.facilitysName = Arrays.asList(facilitysNameStr.split(","));
        } else {
            this.facilitysName = new ArrayList<>();
        }
    }
}
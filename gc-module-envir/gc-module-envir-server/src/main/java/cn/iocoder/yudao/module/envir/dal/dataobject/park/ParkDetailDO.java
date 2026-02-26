package cn.iocoder.yudao.module.envir.dal.dataobject.park;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/12 8:35
 */
@Data
public class ParkDetailDO extends ParkDO {
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerName;
}
package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 16:57
 */
@Data
public class TransferReserveDetailDO extends TransferReserveDO {
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleName;
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id
     */
    private String handleName;
}
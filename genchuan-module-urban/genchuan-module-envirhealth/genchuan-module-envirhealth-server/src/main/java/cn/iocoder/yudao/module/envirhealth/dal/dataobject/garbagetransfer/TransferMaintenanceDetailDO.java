package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 11:29
 */
@Data
public class TransferMaintenanceDetailDO extends TransferMaintenanceDO {
    /**
     * 关联garbage_transfer.transfer_id
     */
    private String transferName;
    /**
     * 关联sys_equipment.id
     */
    private String equipmentName;
    /**
     * 关联sys_user.id
     */
    private String handleName;
}
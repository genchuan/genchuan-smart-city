package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 8:46
 */
@Data
public class TransferAlarmDetailDO extends TransferAlarmDO {
    /**
     * 关联garbage_transfer.transfer_id
     */
    private String transferName;
    /**
     * 关联sys_alarm_type.id
     */
    private String alarmTypeName;
    /**
     * 关联sys_user.id
     */
    private String handleName;
}
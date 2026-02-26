package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.util.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 16:25
 */
@Data
public class GarbageTransferDetailDO extends GarbageTransferDO {
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 核心设备IDs，JSON
     */
    @JsonIgnore
    private String equipmentsNameStr;

    private List<String> equipmentsName;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusName;
    /**
     * 关联sys_user.id
     */
    private String managerName;

    public void setEquipmentsNameStr(String equipmentsNameStr) {
        this.equipmentsNameStr = equipmentsNameStr;
        this.equipmentsName = StringSplitUtils.splitToStringList(equipmentsNameStr);
    }

}
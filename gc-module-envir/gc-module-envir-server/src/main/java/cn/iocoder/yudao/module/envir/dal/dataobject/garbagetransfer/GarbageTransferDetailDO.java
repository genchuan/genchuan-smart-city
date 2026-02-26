package cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/11 16:26
 */
@Data
public class GarbageTransferDetailDO extends GarbageTransferDO {
    /**
     * 所属区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 核心设备（关联sys_equipment.sys_equipment_id，多个用逗号分隔）
     */
    private String equipmentsNameStr;
    private List<String> equipmentsName;
    /**
     * 负责人（关联sys_user.id）
     */
    private String managerName;
    /**
     * 运营状态（关联sys_operation_status.sys_operation_status_id）
     */
    private String operationStatusName;

    public void setEquipmentsNameStr(String equipmentsNameStr) {
        this.equipmentsNameStr = equipmentsNameStr;
        if (equipmentsNameStr != null && !equipmentsNameStr.trim().isEmpty()) {
            this.equipmentsName = Arrays.asList(equipmentsNameStr.split(","));
        } else {
            this.equipmentsName = new ArrayList<>();
        }
    }
}
package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖维修工单新增 Response VO")
public class ManholeCoverRepairOrderAddRespVO {

    @Schema(description = "工单主键ID")
    private String orderId;

    @Schema(description = "工单编号")
    private String orderNo;

    @Schema(description = "工单状态 0-待派单 1-已派单")
    private Integer orderStatus;

    @Schema(description = "区块链存证哈希")
    private String chainHash;

    @Schema(description = "租户ID")
    private String tenantId;
}

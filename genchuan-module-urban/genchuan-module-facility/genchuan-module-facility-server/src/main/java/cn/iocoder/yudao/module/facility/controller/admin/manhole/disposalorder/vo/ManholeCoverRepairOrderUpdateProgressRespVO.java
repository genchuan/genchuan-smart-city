package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖维修工单进度更新响应参数")
public class ManholeCoverRepairOrderUpdateProgressRespVO {

    @Schema(description = "工单主键ID")
    private String orderId;

    @Schema(description = "更新后的工单状态")
    private Integer orderStatus;

    @Schema(description = "进度更新时间")
    private String updateTime;

    @Schema(description = "区块链存证哈希")
    private String chainHash;

    @Schema(description = "租户ID")
    private String tenantId;
}
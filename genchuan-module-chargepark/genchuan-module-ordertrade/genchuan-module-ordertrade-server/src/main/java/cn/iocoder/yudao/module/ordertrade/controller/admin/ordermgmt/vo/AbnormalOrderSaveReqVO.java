package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 异常订单新增/修改 Request VO")
@Data
public class AbnormalOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;
    @Schema(description = "订单类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderType;
    @Schema(description = "异常类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String abnormalType;
    @Schema(description = "异常识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime identifyTime;
    @Schema(description = "处置状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;
    @Schema(description = "忽略理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ignoreReason;
    @Schema(description = "处置进度", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String processProgress;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}

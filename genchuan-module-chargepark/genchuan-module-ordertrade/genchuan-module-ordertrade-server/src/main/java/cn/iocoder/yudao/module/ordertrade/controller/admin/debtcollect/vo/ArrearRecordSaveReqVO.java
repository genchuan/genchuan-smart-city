package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 欠费记录新增/修改 Request VO")
@Data
public class ArrearRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "记录编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String recordNo;
    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;
    @Schema(description = "关联欠费订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderIds;
    @Schema(description = "欠费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal arrearAmount;
    @Schema(description = "结清状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}

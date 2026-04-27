package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 对账记录新增/修改 Request VO")
@Data
public class ReconcileRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "所属对账单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "对账单ID不能为空")
    private Long billId;

    @Schema(description = "状态：normal/abnormal")
    private String status;

    @Schema(description = "异常原因")
    private String errorReason;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}

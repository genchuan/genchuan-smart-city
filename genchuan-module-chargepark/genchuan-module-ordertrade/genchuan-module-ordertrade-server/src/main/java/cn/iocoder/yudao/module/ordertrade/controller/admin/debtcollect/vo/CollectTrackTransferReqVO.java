package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 追缴跟踪转派 Request VO")
@Data
public class CollectTrackTransferReqVO {

    @Schema(description = "追缴跟踪ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "追缴跟踪ID不能为空")
    private Long id;

    @Schema(description = "转派目标用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "转派目标用户ID不能为空")
    private Long transferUserId;

    @Schema(description = "备注")
    private String remark;
}
package cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "巡查巡检 - 电子围栏状态修改 Request VO")
@Data
public class FenceMgmtStatusReqVO {

    @Schema(description = "围栏ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "围栏ID不能为空")
    private Long id;
}
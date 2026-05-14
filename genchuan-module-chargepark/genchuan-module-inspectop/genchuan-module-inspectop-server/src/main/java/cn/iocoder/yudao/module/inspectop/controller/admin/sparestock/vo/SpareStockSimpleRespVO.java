package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "巡查巡检 - 备件简略信息 Response VO")
@Data
public class SpareStockSimpleRespVO {

    @Schema(description = "备件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long spareId;

    @Schema(description = "备件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "充电枪密封圈")
    private String spareName;
}
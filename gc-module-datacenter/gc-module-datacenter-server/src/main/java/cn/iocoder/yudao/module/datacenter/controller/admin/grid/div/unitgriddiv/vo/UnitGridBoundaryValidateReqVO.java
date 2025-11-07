package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 单元网格边界校验 Request VO")
@Data
public class UnitGridBoundaryValidateReqVO {

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED, example = "116.3974,39.9093;116.3975,39.9094")
    @NotEmpty(message = "边界坐标不能为空")
    private String boundaryCoords;

    @Schema(description = "社区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotEmpty(message = "社区ID不能为空")
    private String commId;

    @Schema(description = "排除的网格ID（修改时使用）", example = "1024")
    private Long excludeId;

}
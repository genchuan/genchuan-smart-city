package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 单元网格坐标导入 Request VO")
@Data
public class UnitGridCoordinateImportReqVO {

    @Schema(description = "单元网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "圆恩寺社区001")
    @NotEmpty(message = "单元网格名称不能为空")
    private String unitGridName;

    @Schema(description = "社区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotEmpty(message = "社区ID不能为空")
    private String commId;

    @Schema(description = "比例尺", requiredMode = Schema.RequiredMode.REQUIRED, example = "1:2000")
    @NotEmpty(message = "比例尺不能为空")
    private String scale;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED, example = "116.3974,39.9093;116.3975,39.9094")
    @NotEmpty(message = "边界坐标不能为空")
    private String boundaryCoords;

    @Schema(description = "备注", example = "基于XX路边界")
    private String remark;

}

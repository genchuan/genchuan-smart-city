package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 单元网格简单 Response VO")
@Data
public class UnitGridSimpleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "单元网格ID", example = "unit_001")
    private String unitGridId;

    @Schema(description = "单元网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "圆恩寺社区001")
    private String unitGridName;

    @Schema(description = "所属社区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private String commId;

    @Schema(description = "面积(m²)", example = "10000")
    private Integer area;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    private String boundaryCoords;

}

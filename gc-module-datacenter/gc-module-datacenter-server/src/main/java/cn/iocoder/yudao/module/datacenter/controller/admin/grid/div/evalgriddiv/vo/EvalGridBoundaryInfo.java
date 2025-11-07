package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 评价网格边界坐标信息 VO")
@Data
public class EvalGridBoundaryInfo {

    @Schema(description = "管理网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mngGridId;

    @Schema(description = "单元网格ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String unitGridId;

    @Schema(description = "边界坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    private String boundaryCoords;
}
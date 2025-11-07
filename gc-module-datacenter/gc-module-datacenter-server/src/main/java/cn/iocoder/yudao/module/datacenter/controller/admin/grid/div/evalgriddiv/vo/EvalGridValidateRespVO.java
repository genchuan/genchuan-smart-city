package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 评价网格校验 Response VO")
@Data
public class EvalGridValidateRespVO {

    @Schema(description = "校验是否通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean valid;

    @Schema(description = "校验消息", example = "校验通过")
    private String message;

    @Schema(description = "计算面积(m²)", example = "50000")
    private Integer calculatedArea;

    @Schema(description = "管理网格数量", example = "3")
    private Integer mngGridCount;

    @Schema(description = "是否属于同一乡镇", example = "true")
    private Boolean sameTown;

    @Schema(description = "是否相邻", example = "true")
    private Boolean adjacent;

    @Schema(description = "冲突的评价网格ID列表")
    private List<Long> conflictEvalGridIds;

}
package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 单元网格边界校验 Response VO")
@Data
public class UnitGridBoundaryValidateRespVO {

    @Schema(description = "校验是否通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean valid;

    @Schema(description = "校验消息", example = "边界校验通过")
    private String message;

    @Schema(description = "计算面积(m²)", example = "10050")
    private Integer calculatedArea;

    @Schema(description = "是否超出中心城区面积范围", example = "false")
    private Boolean areaOutOfRange;

    @Schema(description = "重叠的网格ID列表")
    private java.util.List<Long> overlapGridIds;

    @Schema(description = "重叠的网格名称列表")
    private java.util.List<String> overlapGridNames;

}
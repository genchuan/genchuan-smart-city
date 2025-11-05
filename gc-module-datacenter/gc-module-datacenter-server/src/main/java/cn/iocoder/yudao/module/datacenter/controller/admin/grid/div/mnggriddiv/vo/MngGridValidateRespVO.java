package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 管理网格校验 Response VO")
@Data
public class MngGridValidateRespVO {

    @Schema(description = "校验是否通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean valid;

    @Schema(description = "校验消息", example = "校验通过")
    private String message;

    @Schema(description = "计算面积(m²)", example = "30000")
    private Integer calculatedArea;

    @Schema(description = "单元网格数量", example = "3")
    private Integer unitGridCount;

    @Schema(description = "是否跨社区", example = "false")
    private Boolean crossCommunity;

    @Schema(description = "是否相邻", example = "true")
    private Boolean adjacent;

    @Schema(description = "推荐的网格员数量", example = "1")
    private Integer recommendedStaffCount;

    @Schema(description = "冲突的管理网格ID列表")
    private List<Long> conflictMngGridIds;

}

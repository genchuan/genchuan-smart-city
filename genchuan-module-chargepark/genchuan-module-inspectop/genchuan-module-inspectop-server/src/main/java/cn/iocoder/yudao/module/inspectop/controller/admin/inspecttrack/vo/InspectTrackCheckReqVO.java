package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "巡查巡检 - 巡检轨迹核查 Request VO")
@Data
public class InspectTrackCheckReqVO {

    @Schema(description = "轨迹ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "轨迹ID不能为空")
    private Long id;

    @Schema(description = "核查备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "异常轨迹已核实，为信号漂移导致")
    @NotBlank(message = "核查备注不能为空")
    private String checkRemark;
}
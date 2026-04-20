package cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "巡查巡检 - 油车占位监测批量处置 Request VO")
@Data
@EqualsAndHashCode
@ToString
public class OilMonitorBatchProcessReqVO {

    @Schema(description = "监测记录ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测记录ID数组不能为空")
    private List<Long> ids;

    @Schema(description = "处置进度，百分比", example = "100")
    private Integer processProgress;
}
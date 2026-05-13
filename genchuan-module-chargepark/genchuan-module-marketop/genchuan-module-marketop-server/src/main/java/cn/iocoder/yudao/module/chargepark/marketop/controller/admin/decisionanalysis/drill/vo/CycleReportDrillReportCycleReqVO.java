package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表钻取-报表周期 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportDrillReportCycleReqVO extends PageParam {

    @Schema(description = "报表周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;

}

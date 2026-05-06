package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表钻取-通用 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportDrillBaseReqVO extends PageParam {

    @Schema(description = "报表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "报表ID不能为空")
    private Long reportId;

}

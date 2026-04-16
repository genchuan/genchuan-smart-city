package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 营销运营报表创建 Request VO")
@Data
public class MarketOpReportCreateReqVO {

    @Schema(description = "报表名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报表名称不能为空")
    private String name;

    @Schema(description = "报表类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报表类型不能为空")
    private String type;

    @Schema(description = "时间粒度")
    private String timeScale;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "备注")
    private String remark;

}

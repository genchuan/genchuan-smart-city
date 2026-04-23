package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * 场站资源报表图表 Request VO
 */
@Data
@Schema(description = "场站资源报表图表查询")
public class StationReportChartReqVO {

    @NotBlank(message = "报表周期不能为空")
    @Schema(description = "报表周期", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportCycle;

    @NotBlank(message = "开始时间不能为空")
    @Schema(description = "开始时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startTime;

    @NotBlank(message = "结束时间不能为空")
    @Schema(description = "结束时间 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endTime;

    @Schema(description = "租户ID", hidden = true)
    private Long tenantId;
}

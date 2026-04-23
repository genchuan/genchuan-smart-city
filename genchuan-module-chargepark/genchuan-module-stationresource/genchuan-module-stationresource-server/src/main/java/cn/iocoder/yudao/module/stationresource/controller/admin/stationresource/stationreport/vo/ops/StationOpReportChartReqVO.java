package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Schema(description = "管理后台 - 场站资源周期报表查询 Request VO")
@Data
public class StationOpReportChartReqVO {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报", requiredMode = Schema.RequiredMode.REQUIRED, example = "月报")
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "报表开始时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-01 00:00:00")
//    @NotNull(message = "报表开始时间不能为空")
    private LocalDateTime reportStartTime;

    @Schema(description = "报表结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-31 23:59:59")
//    @NotNull(message = "报表结束时间不能为空")
    private LocalDateTime reportEndTime;

//    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
//    @NotNull(message = "租户ID不能为空")
//    private Long tenantId;
}

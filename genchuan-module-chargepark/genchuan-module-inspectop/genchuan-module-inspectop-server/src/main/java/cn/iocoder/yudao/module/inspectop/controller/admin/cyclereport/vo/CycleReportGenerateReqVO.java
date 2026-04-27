// 文件: CycleReportGenerateReqVO.java
package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 生成巡检运维报表 Request VO")
@Data
public class CycleReportGenerateReqVO {

    @Schema(description = "报表周期（日报 / 周报 / 月报 / 季报 / 半年报 / 年报 / 自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "自定义报表")
    @NotNull(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "所属场站 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "所属场站 ID 不能为空")
    private Long stationId;

    @Schema(description = "统计开始时间，格式 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04-01 00:00:00")
//    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeStart;

    @Schema(description = "统计结束时间，格式 yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04-22 23:59:59")
//    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statTimeEnd;

    @Schema(description = "报表类型（自定义 / 自动）", requiredMode = Schema.RequiredMode.REQUIRED, example = "自定义")
//    @NotNull(message = "报表类型不能为空")
    private String reportType;

}
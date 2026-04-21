package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "场站运营报表 Response VO")
@Data
public class StationOpReportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "报表类型", example = "月报")
    private String reportType;

    @Schema(description = "报表统计时间标识", example = "2025-03")
    private String reportTime;

    @Schema(description = "开始时间戳(毫秒)", example = "1775011986000")
    private LocalDateTime startTime;

    @Schema(description = "结束时间戳(毫秒)", example = "1777603986000")
    private LocalDateTime endTime;

    @Schema(description = "创建者", example = "system")
    private String creator;

    @Schema(description = "创建时间", example = "1777603986000")
    private LocalDateTime createTime;
    // ========== 扩展展示字段 ==========
    @Schema(description = "场站数", example = "20")
    private Integer stationCount;

    @Schema(description = "车位使用率", example = "85.50")
    private BigDecimal spaceUseRate;

    @Schema(description = "规则匹配率", example = "98.00")
    private BigDecimal ruleMatchRate;

    @Schema(description = "同比", example = "10.20")
    private BigDecimal yearOnYear;

    @Schema(description = "环比", example = "5.30")
    private BigDecimal monthOnMonth;
}

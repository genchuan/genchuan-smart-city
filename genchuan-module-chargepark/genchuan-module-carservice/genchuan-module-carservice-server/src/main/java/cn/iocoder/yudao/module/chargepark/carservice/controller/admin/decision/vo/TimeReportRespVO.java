package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 时间尺度报表响应 VO
 */
@Schema(description = "时间尺度报表 RespVO")
@Data
public class TimeReportRespVO {

    @Schema(description = "尺度", example = "DAILY")
    private String period;

    @Schema(description = "尺度名称", example = "日报")
    private String periodLabel;

    @Schema(description = "窗口起始时刻")
    private LocalDateTime startTime;

    @Schema(description = "窗口结束时刻（不含）")
    private LocalDateTime endTime;

    @Schema(description = "报表生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "各业务模块统计")
    private Map<String, Object> modules = new LinkedHashMap<>();

}

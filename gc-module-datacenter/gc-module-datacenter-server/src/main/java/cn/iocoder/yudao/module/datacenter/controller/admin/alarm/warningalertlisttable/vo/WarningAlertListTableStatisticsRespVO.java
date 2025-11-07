package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.warningalertlisttable.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 预警告警统计分析 Response VO（统一承载维度名称与统计数据）
 */
@Schema(description = "管理后台 - 预警告警统计分析 Response VO")
@Data
public class WarningAlertListTableStatisticsRespVO {

    @Schema(description = "统计维度类型：LEVEL/STATUS", example = "LEVEL")
    private String categoryType;

    @Schema(description = "统计维度值：如等级'高'或状态'待处理'", example = "高")
    private String category;

    @Schema(description = "数量", example = "12")
    private Long count;

    @Schema(description = "占比(%)", example = "36.5")
    private Double percentage;
}

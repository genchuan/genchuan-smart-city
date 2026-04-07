package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "企业违规数据分析 - 统计报表 Response VO")
public class ViolationAnalyticsPageResp {

    // ====================== 统计维度（日/周/月） ======================
    @ExcelProperty(value = "统计维度", index = 1)
    @Schema(description = "统计维度：日、周、月")
    private String statisticPeriod;

    @ExcelProperty(value = "统计开始时间", index = 2)
    @Schema(description = "统计开始时间")
    private LocalDate beginTime;

    @ExcelProperty(value = "统计结束时间", index = 3)
    @Schema(description = "统计结束时间")
    private LocalDate endTime;

    @ExcelProperty(value = "统计时间标识", index = 11)
    @Schema(description = "用于图表展示：yyyy-MM-dd / yyyy-第W周 / yyyy-MM")
    private String timeLabel;
    // ====================== 企业信息 ======================
    @ExcelProperty(value = "企业ID", index = 4)
    @Schema(description = "企业ID")
    private Long entId;

    @ExcelProperty(value = "企业名称", index = 5)
    @Schema(description = "企业名称")
    private String entName;

    // ====================== 核心统计指标 ======================

    @ExcelProperty(value = "总告警次数", index = 6)
    @Schema(description = "总告警次数（AI预警按企业分组统计）")
    private Integer alarmCount;

    @ExcelProperty(value = "违规次数", index = 7)
    @Schema(description = "违规次数（整改复审计数）")
    private Integer violationCount;

    @ExcelProperty(value = "设备正常率(%)", index = 8)
    @Schema(description = "设备正常率（去重设备计算）")
    private Double deviceNormalRate;

    @ExcelProperty(value = "整改完成率(%)", index = 9)
    @Schema(description = "整改完成率（整改复审计算）")
    private Double rectifyFinishRate;

    // ====================== 导出/排名/图表通用字段 ======================

    @ExcelProperty(value = "企业违规排名", index = 10)
    @Schema(description = "企业违规频次排名（降序）")
    private Integer rank;


}

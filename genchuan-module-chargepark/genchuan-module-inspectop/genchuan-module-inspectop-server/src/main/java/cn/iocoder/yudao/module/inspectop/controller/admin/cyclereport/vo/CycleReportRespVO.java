package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Schema(description = "巡查巡检 - 巡检运维报表 Response VO")
@Data
public class CycleReportRespVO {

    @Schema(description = "报表主键 ID", example = "1")
    private Long id;

    @Schema(description = "报表周期", example = "月报")
    private String reportCycle;

    // 注意：接口文档中是一个合并的字符串，这里我们提供起止时间，前端可拼接
    @Schema(description = "统计开始时间", example = "2026-01-01 00:00:00")
    private LocalDateTime statTimeStart;
    @Schema(description = "统计结束时间", example = "2026-01-31 23:59:59")
    private LocalDateTime statTimeEnd;

    @Schema(description = "正常设备数", example = "156")
    private Integer normalDeviceNum;
    @Schema(description = "异常设备数", example = "8")
    private Integer abnormalDeviceNum;
    @Schema(description = "巡检任务数", example = "240")
    private Integer inspectTaskNum;
    @Schema(description = "任务完成率", example = "98.5")
    private BigDecimal taskCompleteRate;
    @Schema(description = "油车占位待处置数", example = "12")
    private Integer oilWaitHandleNum;
    @Schema(description = "处置完成率", example = "95.2")
    private BigDecimal oilHandleCompleteRate;
    @Schema(description = "巡检人员在岗数", example = "28")
    private Integer inspectUserOnlineNum;
    @Schema(description = "资产正常数", example = "320")
    private Integer assetNormalNum;
    @Schema(description = "库存预警数", example = "6")
    private Integer stockWarnNum;

    @Schema(description = "所属场站 ID", example = "1001")
    private Long stationId;
    @Schema(description = "所属场站名称", example = "泉州丰泽充电场站")
    private String stationName;

    @Schema(description = "生成状态", example = "已生成")
    private String generateStatus;
    @Schema(description = "报表生成时间")
    private LocalDateTime generateTime;
    @Schema(description = "操作人", example = "admin")
    private String operator;
    @Schema(description = "报表导出次数", example = "3")
    private Integer exportCount;
    @Schema(description = "同比数据", example = "同比增长 5.2%")
    private String yearOnYearData;
    @Schema(description = "环比数据", example = "环比增长 2.8%")
    private String chainRatioData;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
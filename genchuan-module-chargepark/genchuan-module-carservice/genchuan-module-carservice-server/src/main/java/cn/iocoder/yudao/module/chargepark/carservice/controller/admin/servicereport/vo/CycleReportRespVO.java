package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 Response VO(列表行,严格对齐 07 文档字段)")
@Data
public class CycleReportRespVO {

    @Schema(description = "报表主键 ID", example = "1")
    private Long id;

    @Schema(description = "报表周期", example = "月报")
    private String reportCycle;

    @Schema(description = "统计时段,形如 2026-03 / 2026-W15 / 自定义区间字符串", example = "2026-03")
    private String statTime;

    @Schema(description = "救援完成率(百分比,如 98.5 表 98.5%)", example = "98.5")
    private BigDecimal rescueCompleteRate;

    @Schema(description = "预约成功率", example = "96.2")
    private BigDecimal reserveSuccessRate;

    @Schema(description = "投诉处理率", example = "99.0")
    private BigDecimal complaintHandleRate;

    @Schema(description = "寻车定位成功率", example = "97.8")
    private BigDecimal findCarSuccessRate;

    @Schema(description = "空位推送成功率", example = "95.6")
    private BigDecimal spacePushSuccessRate;

    @Schema(description = "生效话术数", example = "120")
    private Integer effectiveWordingCount;

    @Schema(description = "救援总量", example = "520")
    private Integer rescueTotal;

    @Schema(description = "预约总量", example = "1280")
    private Integer reserveTotal;

    @Schema(description = "投诉总量", example = "86")
    private Integer complaintTotal;

    @Schema(description = "空位推送总量", example = "3600")
    private Integer spacePushTotal;

    @Schema(description = "生成状态", example = "已生成")
    private String generateStatus;

    @Schema(description = "报表生成时间", example = "2026-04-01 01:00:00")
    private LocalDateTime generateTime;

    @Schema(description = "操作人昵称", example = "芋道源码")
    private String operator;

    @Schema(description = "操作人用户 ID(供前端调 system-server 查账号/姓名使用,定时任务为 null)", example = "1")
    private Long operatorUserId;

    @Schema(description = "同比增长率(百分比,正负数均可)", example = "12.3")
    private BigDecimal yearOnYearGrowthRate;

    @Schema(description = "环比增长率", example = "5.6")
    private BigDecimal monthOnMonthGrowthRate;

    @Schema(description = "服务状态占比", example = "已完成:95%, 处理中:3%, 待处理:2%")
    private String serviceStatusRatio;

    @Schema(description = "创建者", example = "system")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}

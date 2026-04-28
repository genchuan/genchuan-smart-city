package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡检运维报表存储分页 Request VO")
@Data
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期(日报/周报/月报/季报/半年报/年报/自定义报表)")
    private String reportCycle;

    @Schema(description = "所属场站ID")
    private Long stationId;

    @Schema(description = "所属场站名称")
    private String stationName;

    @Schema(description = "统计开始时间")
    private LocalDateTime statTimeStart;

    @Schema(description = "统计结束时间")
    private LocalDateTime statTimeEnd;

    @Schema(description = "正常设备数")
    private Integer normalDeviceNum;

    @Schema(description = "异常设备数")
    private Integer abnormalDeviceNum;

    @Schema(description = "巡检任务数")
    private Integer inspectTaskNum;

    @Schema(description = "任务完成率(%)")
    private BigDecimal taskCompleteRate;

    @Schema(description = "油车占位待处置数")
    private Integer oilWaitHandleNum;

    @Schema(description = "处置完成率(%)")
    private BigDecimal oilHandleCompleteRate;

    @Schema(description = "巡检人员在岗数")
    private Integer inspectUserOnlineNum;

    @Schema(description = "资产正常数")
    private Integer assetNormalNum;

    @Schema(description = "库存预警数")
    private Integer stockWarnNum;

    @Schema(description = "生成状态(已生成/生成失败)")
    private String generateStatus;

    @Schema(description = "报表生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "操作人")
    private String operator;

    @Schema(description = "报表导出次数")
    private Integer exportCount;

    @Schema(description = "同比数据")
    private String yearOnYearData;

    @Schema(description = "环比数据")
    private String chainRatioData;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
package cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测仪表校准管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MeterCalibrationPageReqVO extends PageParam {

    @Schema(description = "仪表ID")
    private String meterId;

    @Schema(description = "仪表类型")
    private String meterType;

    @Schema(description = "校准周期(天)")
    private Double calibrationCycle;

    @Schema(description = "上次校准日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastCalibrationDate;

    @Schema(description = "本次校准日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] currentCalibrationDate;

    @Schema(description = "标准溶液浓度")
    private Double standardSolutionConc;

    @Schema(description = "校准前示值")
    private Double beforeCalibrationValue;

    @Schema(description = "校准后示值")
    private Double afterCalibrationValue;

    @Schema(description = "操作人员ID")
    private String operatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
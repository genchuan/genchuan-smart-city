package cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 在线数据与实验室比对分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OnlineLabComparisonPageReqVO extends PageParam {

    @Schema(description = "比对日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] comparisonDate;

    @Schema(description = "监测点ID")
    private String monitorPointId;

    @Schema(description = "仪器类型")
    private String instrumentType;

    @Schema(description = "在线监测值")
    private Double onlineValue;

    @Schema(description = "实验室检测值")
    private Double labValue;

    @Schema(description = "偏差值")
    private Double deviationValue;

    @Schema(description = "是否超标(0否1是)")
    private Boolean isExceeded;

    @Schema(description = "预警状态")
    private String warningStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
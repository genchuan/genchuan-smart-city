package cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警阈值管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarningThresholdPageReqVO extends PageParam {

    @Schema(description = "指标名称")
    private String indicatorName;

    @Schema(description = "阈值类型(上限/下限)")
    private String thresholdType;

    @Schema(description = "阈值数值")
    private Double thresholdValue;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "适用场景(如管网末梢)")
    private String applicableScene;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
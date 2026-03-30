package cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警指标配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarningIndicatorPageReqVO extends PageParam {

    @Schema(description = "预警指标名称")
    private String indicatorName;

    @Schema(description = "指标类型(水质/设备)")
    private String indicatorType;

    @Schema(description = "关联监测点类型(水源/水厂/管网)")
    private String relatedPointType;

    @Schema(description = "数据来源(在线监测/人工检测)")
    private String dataSource;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测项规则设置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonitoringRulePageReqVO extends PageParam {

    @Schema(description = "水位超限预警")
    private String waterLevelOverlimitWarning;

    @Schema(description = "流量异常预警")
    private String flowAbnormalityWarning;

    @Schema(description = "水质超标预警")
    private String waterQualityExceedanceWarning;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
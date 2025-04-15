package cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 智慧城管分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlarmHandlingCategoryPageReqVO extends PageParam {

    @Schema(description = "报警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] time;

    @Schema(description = "报警来源")
    private String alarmSource;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "报警描述", example = "你猜")
    private String alarmDescription;

    @Schema(description = "涉及区域")
    private String involvingRegions;

    @Schema(description = "处置措施")
    private String disposalMeasures;

    @Schema(description = "处置结果")
    private String disposalResults;

    @Schema(description = "备注")
    private String notes;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
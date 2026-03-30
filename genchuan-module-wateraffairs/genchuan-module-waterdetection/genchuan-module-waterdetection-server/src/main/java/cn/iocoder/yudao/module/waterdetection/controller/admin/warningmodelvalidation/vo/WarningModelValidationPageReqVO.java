package cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警模型校验分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarningModelValidationPageReqVO extends PageParam {

    @Schema(description = "模型名称")
    private String modelName;

    @Schema(description = "校验时间段")
    private String validationPeriod;

    @Schema(description = "预警次数")
    private Double warningCount;

    @Schema(description = "准确预警次数")
    private Double accurateWarningCount;

    @Schema(description = "误报次数")
    private Double falseAlarmCount;

    @Schema(description = "准确率(%)")
    private Double accuracyRate;

    @Schema(description = "调整建议")
    private String adjustmentSuggestion;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
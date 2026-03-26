package cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 养护考核评价分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssessmentAndEvaluationPageReqVO extends PageParam {

    @Schema(description = "养护人员编号")
    private String maintenancePersonnelNumber;

    @Schema(description = "养护任务编号")
    private String maintenanceTaskNumber;

    @Schema(description = "考核周期")
    private String assessmentCycle;

    @Schema(description = "考核得分")
    private String assessmentScore;

    @Schema(description = "考核等级")
    private String assessmentLevel;

    @Schema(description = "评价意见")
    private String evaluationOpinion;

    @Schema(description = "改进建议")
    private String improvementSuggestions;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
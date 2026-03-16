package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公厕保洁任务新增/修改 Request VO")
@Data
public class ToiletCleaningTaskSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "公厕ID，关联public_toilet.id", example = "6468")
    private String toiletId;

    @Schema(description = "任务编号")
    private String taskNo;

    @Schema(description = "保洁频次，如每天2次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁内容，如地面清洁/便池清洁/垃圾清理")
    private String cleaningContent;

    @Schema(description = "保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁人员IDs，JSON数组格式")
    @Pattern(regexp = "^$|^\\[.*\\]$", message = "cleanerIds必须为JSON数组格式（如[]）")
    private String cleanerIds;

    @Schema(description = "计划状态ID，关联sys_plan_status.id", example = "2017")
    private String planStatusId;

    @Schema(description = "完成率，%")
    private BigDecimal completionRate;

    @Schema(description = "是否异常：0-正常，1-异常")
    private Integer isAbnormal;

    @Schema(description = "异常描述")
    private String abnormalDesc;

    @Schema(description = "佐证材料URL，JSON数组格式")
    @Pattern(regexp = "^$|^\\[.*\\]$", message = "proofUrls必须为JSON数组格式（如[]）")
    private String proofUrls;

    // ============ 新增字段 ============
    @Schema(description = "完成时间")
    private LocalDateTime completeTime;

    @Schema(description = "处置结果")
    private String handleResult;


    @Schema(description = "任务耗时（分钟）")
    private Integer handleDuration;

    @Schema(description = "满意度：1-不满意，2-一般，3-满意，4-非常满意")
    private String satisfaction;

    @Schema(description = "统计周期，如2026-03")
    private String statPeriod;
}
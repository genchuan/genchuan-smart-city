package cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评分规则主分页 Request VO")
@Data
public class CommentRulePageReqVO extends PageParam {

    @Schema(description = "指标体系ID(关联eval_index_system)", example = "18741")
    private Long systemId;

    @Schema(description = "规则分类ID(关联eval_rule_category)", example = "24205")
    private Long ruleCategoryId;

    @Schema(description = "规则名称", example = "芋艿")
    private String ruleName;

    @Schema(description = "规则类型（1=加分，2=扣分）", example = "1")
    private Integer ruleType;

    @Schema(description = "状态（1=启用，2=停用）", example = "2")
    private Integer status;

    @Schema(description = "适用对象类型（如：网格/企业/个人）", example = "2")
    private String applyObjectType;

    @Schema(description = "生效开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveStartTime;

    @Schema(description = "生效结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveEndTime;

    @Schema(description = "状态变更备注", example = "你猜")
    private String statusChangeRemark;

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

    @Schema(description = "操作变更日志")
    private String operationLog;

}
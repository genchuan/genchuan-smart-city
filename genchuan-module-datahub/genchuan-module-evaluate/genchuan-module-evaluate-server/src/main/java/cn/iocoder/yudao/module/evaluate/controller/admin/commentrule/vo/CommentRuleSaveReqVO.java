package cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo;

import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评分规则主新增/修改 Request VO")
@Data
public class CommentRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5927")
    private Long id;

    @Schema(description = "指标体系ID(关联eval_index_system)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18741")
    @NotNull(message = "指标体系ID(关联eval_index_system)不能为空")
    private Long systemId;

    @Schema(description = "规则分类ID(关联eval_rule_category)", requiredMode = Schema.RequiredMode.REQUIRED, example = "24205")
    @NotNull(message = "规则分类ID(关联eval_rule_category)不能为空")
    private Long ruleCategoryId;

    @Schema(description = "指标项ID(关联eval_index_item)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15670")
    @NotNull(message = "指标项ID(关联eval_index_item)不能为空")
    private Long itemId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "规则类型（1=加分，2=扣分）", example = "1")
    private Integer ruleType;

    @Schema(description = "状态（1=启用，2=停用）", example = "2")
    private Integer status;

    @Schema(description = "适用对象类型（如：网格/企业/个人）", example = "2")
    private String applyObjectType;

    @Schema(description = "生效开始时间")
    private LocalDateTime effectiveStartTime;

    @Schema(description = "生效结束时间")
    private LocalDateTime effectiveEndTime;

    @Schema(description = "状态变更备注", example = "你猜")
    private String statusChangeRemark;

    @Schema(description = "操作变更日志")
    private String operationLog;

    @Schema(description = "规则明细列表")
    private List<RuleDetailSaveReqVO> details;

}
package cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo;

import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 评分规则主 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CommentRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5927")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标体系ID(关联eval_index_system)", requiredMode = Schema.RequiredMode.REQUIRED, example = "18741")
    @ExcelProperty("指标体系ID(关联eval_index_system)")
    private Long systemId;

    @Schema(description = "指标体系UUID", example = "15590")
    @ExcelIgnore
    private String systemIdPk;

    @Schema(description = "指标体系名称", example = "市政道路评分体系")
    @ExcelIgnore
    private String systemName;

    @Schema(description = "规则分类ID(关联eval_rule_category)", requiredMode = Schema.RequiredMode.REQUIRED, example = "24205")
    @ExcelProperty("规则分类ID(关联eval_rule_category)")
    private Long ruleCategoryId;

    @Schema(description = "指标项ID(关联eval_index_item)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15670")
    @ExcelProperty("指标项ID(关联eval_index_item)")
    private Long itemId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "规则类型（1=加分，2=扣分）", example = "1")
    @ExcelProperty("规则类型（1=加分，2=扣分）")
    private Integer ruleType;

    @Schema(description = "状态（1=启用，2=停用）", example = "2")
    @ExcelProperty("状态（1=启用，2=停用）")
    private Integer status;

    @Schema(description = "适用对象类型（如：网格/企业/个人）", example = "2")
    @ExcelProperty("适用对象类型（如：网格/企业/个人）")
    private String applyObjectType;

    @Schema(description = "生效开始时间")
    @ExcelProperty("生效开始时间")
    private LocalDateTime effectiveStartTime;

    @Schema(description = "生效结束时间")
    @ExcelProperty("生效结束时间")
    private LocalDateTime effectiveEndTime;

    @Schema(description = "状态变更备注", example = "你猜")
    @ExcelProperty("状态变更备注")
    private String statusChangeRemark;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "操作变更日志")
    @ExcelProperty("操作变更日志")
    private String operationLog;

    @Schema(description = "规则明细列表")
    private List<RuleDetailRespVO> details;

}

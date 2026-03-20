package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 规则分类完整保存 VO")
@Data
public class RuleCategorySaveFullReqVO {

    @Schema(description = "规则分类ID，新增时为空，修改时必填", example = "1")
    private Long id;

    @Schema(description = "规则分类名称", example = "履职尽责类规则")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.id）", example = "15590")
    private String systemId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "1")
    private Integer statusId;

    @Schema(description = "指标项ID（关联eval_index_item.id）", example = "1")
    private Integer itemId;

    @Schema(description = "规则类型ID（关联sys_rule_type.id）", example = "1")
    private Integer ruleId;

    @Schema(description = "对象类型ID（关联sys_object_type.id）", example = "1")
    private Integer objectTypeId;

    @Schema(description = "评分规则列表")
    @Valid
    private List<CommentRuleSaveReqVO> commentRules;

}

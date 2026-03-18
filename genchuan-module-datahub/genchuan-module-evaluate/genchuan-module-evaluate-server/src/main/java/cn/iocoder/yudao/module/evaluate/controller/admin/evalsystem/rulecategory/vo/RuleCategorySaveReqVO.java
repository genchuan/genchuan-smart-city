package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.*;

@Schema(description = "管理后台 - 规则分类管理新增/修改 Request VO")
@Data
public class RuleCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27497")
    private Long id;

    @Schema(description = "规则分类名称", example = "芋艿")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "26353")
    private String systemId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "15797")
    private Integer statusId;

    @Schema(description = "指标项名称ideval_rule_item.index_id关联“指标项表”", example = "12510")
    private Integer itemId;

    @Schema(description = "规则项id关联“规则类型字典表”sys_rule_type", example = "3654")
    private Integer ruleId;

    @Schema(description = "对象类型ID关联“对象类型字典表”sys_object_type", example = "26110")
    private Integer objectTypeId;

    @Schema(description = "评分规则列表")
    private List<CommentRuleSaveReqVO> commentRules;

}
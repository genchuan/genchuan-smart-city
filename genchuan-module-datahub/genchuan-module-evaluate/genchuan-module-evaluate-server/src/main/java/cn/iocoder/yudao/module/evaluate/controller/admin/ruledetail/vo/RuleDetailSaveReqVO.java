package cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 评分规则明细新增/修改 Request VO")
@Data
public class RuleDetailSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24540")
    private Long id;

    @Schema(description = "规则ID(eval_comment_rule.id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "4919")
    @NotNull(message = "规则ID(eval_comment_rule.id)不能为空")
    private Long ruleId;

    @Schema(description = "区间最小值（null表示无下限）")
    private BigDecimal minValue;

    @Schema(description = "区间最大值（null表示无上限）")
    private BigDecimal maxValue;

    @Schema(description = "最小值运算符（>=、>）")
    private String operatorMin;

    @Schema(description = "最大值运算符（<=、<）")
    private String operatorMax;

    @Schema(description = "该区间对应的分数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "该区间对应的分数不能为空")
    private BigDecimal score;

    @Schema(description = "排序优先级（值越小越优先匹配）")
    private Integer sortOrder;

    @Schema(description = "规则描述（如：=0、>1且<5）", example = "你说的对")
    private String remark;

}
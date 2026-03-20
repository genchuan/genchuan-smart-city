package cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 评分规则明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RuleDetailRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24540")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则ID(eval_comment_rule.id)", requiredMode = Schema.RequiredMode.REQUIRED, example = "4919")
    @ExcelProperty("规则ID(eval_comment_rule.id)")
    private Long ruleId;

    @Schema(description = "区间最小值（null表示无下限）")
    @ExcelProperty("区间最小值（null表示无下限）")
    private BigDecimal minValue;

    @Schema(description = "区间最大值（null表示无上限）")
    @ExcelProperty("区间最大值（null表示无上限）")
    private BigDecimal maxValue;

    @Schema(description = "最小值运算符（>=、>）")
    @ExcelProperty("最小值运算符（>=、>）")
    private String operatorMin;

    @Schema(description = "最大值运算符（<=、<）")
    @ExcelProperty("最大值运算符（<=、<）")
    private String operatorMax;

    @Schema(description = "该区间对应的分数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("该区间对应的分数")
    private BigDecimal score;

    @Schema(description = "排序优先级（值越小越优先匹配）")
    @ExcelProperty("排序优先级（值越小越优先匹配）")
    private Integer sortOrder;

    @Schema(description = "规则描述（如：=0、>1且<5）", example = "你说的对")
    @ExcelProperty("规则描述（如：=0、>1且<5）")
    private String remark;

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

}
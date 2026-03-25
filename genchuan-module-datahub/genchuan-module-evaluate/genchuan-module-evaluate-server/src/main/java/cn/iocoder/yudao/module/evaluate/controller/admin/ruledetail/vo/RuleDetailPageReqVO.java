package cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评分规则明细分页 Request VO")
@Data
public class RuleDetailPageReqVO extends PageParam {

    @Schema(description = "规则ID(eval_comment_rule.id)", example = "4919")
    private Long ruleId;

    @Schema(description = "区间最小值（null表示无下限）")
    private BigDecimal minValue;

    @Schema(description = "区间最大值（null表示无上限）")
    private BigDecimal maxValue;

    @Schema(description = "最小值运算符（>=、>）")
    private String operatorMin;

    @Schema(description = "最大值运算符（<=、<）")
    private String operatorMax;

    @Schema(description = "该区间对应的分数")
    private BigDecimal score;

    @Schema(description = "排序优先级（值越小越优先匹配）")
    private Integer sortOrder;

    @Schema(description = "规则描述（如：=0、>1且<5）", example = "你说的对")
    private String remark;

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

}
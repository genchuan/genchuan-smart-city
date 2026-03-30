package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 选项分页 Request VO")
@Data
public class OptionPageReqVO extends PageParam {

    @Schema(description = "选项ID（UUID）", example = "8759")
    private String optionId;

    @Schema(description = "题目ID（关联survey_question.question_id）", example = "30458")
    private String questionId;

    @Schema(description = "选项内容")
    private String optionContent;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "题目分值")
    private Integer score;

    @Schema(description = "创建人，关联sys_user.user_id")
    private Integer createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    private Integer updateBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
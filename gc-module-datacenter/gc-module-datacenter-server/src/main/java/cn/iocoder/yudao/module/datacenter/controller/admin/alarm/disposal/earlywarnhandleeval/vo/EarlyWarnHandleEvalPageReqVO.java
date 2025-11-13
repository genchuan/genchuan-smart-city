package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警处置评估分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnHandleEvalPageReqVO extends PageParam {

    @Schema(description = "评估ID")
    private String evalId;

    @Schema(description = "预警ID")
    private String earlyWarnId;

    @Schema(description = "派单ID")
    private String dispatchId;

    @Schema(description = "评估人ID")
    private String evalUserId;

    @Schema(description = "评估人姓名")
    private String evalUserName;

    @Schema(description = "问题解决度得分")
    private Integer problemSolveScore;

    @Schema(description = "响应及时性得分")
    private Integer timelinessScore;

    @Schema(description = "现场规范性得分")
    private Integer stdizationScore;

    @Schema(description = "综合得分")
    private BigDecimal comprehensiveScore;

    @Schema(description = "评估等级")
    private String evalLevel;

    @Schema(description = "评估时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evalTime;

    @Schema(description = "评估备注")
    private String evalRemark;

    @Schema(description = "是否二次处置")
    private String isSecondHandle;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检督办结果反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectSupvFeedbackPageReqVO extends PageParam {

    @Schema(description = "反馈ID")
    private String feedbackId;

    @Schema(description = "反馈编码")
    private String feedbackCode;

    @Schema(description = "关联督办任务ID")
    private String supvTaskId;

    @Schema(description = "关联督办任务编码")
    private String supvTaskCode;

    @Schema(description = "关联问题名称")
    private String problemName;

    @Schema(description = "督办结果")
    private String supvResult;

    @Schema(description = "督办结果说明")
    private String supvResultDesc;

    @Schema(description = "督办反馈意见")
    private String supvFeedbackOpinion;

    @Schema(description = "关联整改验收结果")
    private String rectifyAcceptResult;

    @Schema(description = "反馈人")
    private String feedbackUser;

    @Schema(description = "反馈时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "督办任务状态变更")
    private String supvStatusChange;

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
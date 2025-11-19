package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检督办结果反馈新增/修改 Request VO")
@Data
public class InspectSupvFeedbackSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

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
    private LocalDateTime feedbackTime;

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

}
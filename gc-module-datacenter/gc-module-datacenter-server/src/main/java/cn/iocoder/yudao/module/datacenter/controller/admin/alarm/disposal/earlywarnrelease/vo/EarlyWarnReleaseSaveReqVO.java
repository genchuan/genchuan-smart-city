package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警解除新增/修改 Request VO")
@Data
public class EarlyWarnReleaseSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "解除ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除ID不能为空")
    private String releaseId;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警名称不能为空")
    private String earlyWarnName;

    @Schema(description = "解除申请人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除申请人ID不能为空")
    private String applyUserId;

    @Schema(description = "解除申请人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除申请人姓名不能为空")
    private String applyUserName;

    @Schema(description = "解除审核人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除审核人ID不能为空")
    private String auditUserId;

    @Schema(description = "解除审核人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除审核人姓名不能为空")
    private String auditUserName;

    @Schema(description = "解除状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除状态不能为空")
    private String releaseStatus;

    @Schema(description = "解除原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "解除原因不能为空")
    private String releaseReason;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "驳回原因")
    private String rejectReason;

    @Schema(description = "是否复发", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否复发不能为空")
    private String isRecurrence;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
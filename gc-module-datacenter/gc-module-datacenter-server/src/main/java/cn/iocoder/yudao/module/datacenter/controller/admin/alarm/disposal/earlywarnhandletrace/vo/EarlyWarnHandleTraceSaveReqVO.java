package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警处置跟踪新增/修改 Request VO")
@Data
public class EarlyWarnHandleTraceSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "跟踪ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "跟踪ID不能为空")
    private String handleTraceId;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单ID不能为空")
    private String dispatchId;

    @Schema(description = "派单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单编号不能为空")
    private String dispatchNo;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "处置进度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置进度不能为空")
    private String handleProgress;

    @Schema(description = "进度描述")
    private String progressDesc;

    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人ID不能为空")
    private String operUserId;

    @Schema(description = "操作人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人姓名不能为空")
    private String operUserName;

    @Schema(description = "操作时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "操作时间不能为空")
    private LocalDateTime operTime;

    @Schema(description = "附件URL")
    private String attachUrl;

    @Schema(description = "是否关键节点", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否关键节点不能为空")
    private String isKeyNode;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
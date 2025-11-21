package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警派单新增/修改 Request VO")
@Data
public class EarlyWarnDispatchSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单ID不能为空")
    private String dispatchId;

    @Schema(description = "派单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单编号不能为空")
    private String dispatchNo;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警名称不能为空")
    private String earlyWarnName;

    @Schema(description = "派单类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单类型不能为空")
    private String dispatchType;

    @Schema(description = "接收部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "接收部门ID不能为空")
    private String receiveDeptId;

    @Schema(description = "接收部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "接收部门名称不能为空")
    private String receiveDeptName;

    @Schema(description = "处置人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置人ID不能为空")
    private String handleUserId;

    @Schema(description = "处置人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处置人姓名不能为空")
    private String handleUserName;

    @Schema(description = "派单状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单状态不能为空")
    private String dispatchStatus;

    @Schema(description = "处置时限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处置时限不能为空")
    private Integer handleLimit;

    @Schema(description = "派单时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "派单时间不能为空")
    private LocalDateTime dispatchTime;

    @Schema(description = "接收时间")
    private LocalDateTime receiveTime;

    @Schema(description = "拒单原因")
    private String rejectReason;

    @Schema(description = "派单人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单人ID不能为空")
    private String dispatchUserId;

    @Schema(description = "派单人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单人姓名不能为空")
    private String dispatchUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
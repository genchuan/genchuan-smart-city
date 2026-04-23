package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 开闸管理新增/修改 Request VO")
@Data
public class GateOpenSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15790")
    private Long id;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5128")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason", requiredMode = Schema.RequiredMode.REQUIRED, example = "不对")
    @NotEmpty(message = "开闸原因：紧急通行 / 故障处理 / 其他，关联字典gate_open_open_reason不能为空")
    private String openReason;

    @Schema(description = "申请人ID，关联system_user用户表", requiredMode = Schema.RequiredMode.REQUIRED, example = "12006")
    @NotNull(message = "申请人ID，关联system_user用户表不能为空")
    private Long applyUserId;

    @Schema(description = "申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审批 / 已通过 / 已驳回 / 已执行，关联字典gate_open_status不能为空")
    private String status;

    @Schema(description = "审批人ID，关联system_user用户表", example = "30477")
    private Long auditUserId;

    @Schema(description = "审批时间")
    private LocalDateTime auditTime;

    @Schema(description = "执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "驳回理由", example = "不喜欢")
    private String rejectReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
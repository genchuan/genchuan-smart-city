package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "管理后台 - 处置工单新增/修改 Request VO")
@Data
public class DisposalOrderSaveReqVO {

    @Schema(description = "主键（自增）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1576")
    private Long id;

    @Schema(description = "关联告警表sys_warn的warn_id", example = "6209")
    private Long warnId;

    @Schema(description = "关联窨井盖表manhole_cover的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19667")
    @NotNull(message = "关联窨井盖表manhole_cover的id不能为空")
    private Long coverId;

    @Schema(description = "异常类型：倾斜/振动/开合异常", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "异常类型：倾斜/振动/开合异常不能为空")
    private String abnormalType;

    @Schema(description = "关联风险等级表sys_risk_level的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29172")
    @NotNull(message = "关联风险等级表sys_risk_level的id不能为空")
    private Long riskLevelId;

    @Schema(description = "关联用户表sys_user的id（指派运维员）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8015")
    @NotNull(message = "关联用户表sys_user的id（指派运维员）不能为空")
    private Long assignStaffId;

    @Schema(description = "处置时限（日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "处置时限（日期）不能为空")
    private LocalDate dealLimit;

    @Schema(description = "处置进度：待处置/现场处置/处置中/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "处置进度：待处置/现场处置/处置中/已完成不能为空")
    private String processStatus;

    @Schema(description = "工单完成时间（日期）")
    private LocalDate completeTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
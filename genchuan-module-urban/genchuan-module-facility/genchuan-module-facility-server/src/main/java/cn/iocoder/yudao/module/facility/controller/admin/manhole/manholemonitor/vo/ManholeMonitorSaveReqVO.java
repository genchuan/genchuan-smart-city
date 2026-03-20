package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 窨井盖监测新增/修改 Request VO")
@Data
public class ManholeMonitorSaveReqVO {

    @Schema(description = "主键（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "28680")
    private Long id;

    @Schema(description = "关联窨井盖表manhole_cover的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11309")
    @NotNull(message = "关联窨井盖表manhole_cover的id不能为空")
    private Long coverId;

    @Schema(description = "关联设备表sys_device的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26002")
    @NotNull(message = "关联设备表sys_device的id不能为空")
    private Long deviceId;

    @Schema(description = "关联用户表sys_user的id（运维员）", example = "3128")
    private Long staffId;

    @Schema(description = "关联开合状态表sys_open_status的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23671")
    @NotNull(message = "关联开合状态表sys_open_status的id不能为空")
    private Long openStatusId;

    @Schema(description = "倾斜角度（数值，单位：度）")
    private BigDecimal tiltAngle;

    @Schema(description = "振动数据（数值，单位：m/s²）")
    private BigDecimal vibrationData;

    @Schema(description = "关联风险等级表sys_risk_level的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29862")
    @NotNull(message = "关联风险等级表sys_risk_level的id不能为空")
    private Long riskLevelId;

    @Schema(description = "监测状态：运行中/已停止", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "监测状态：运行中/已停止不能为空")
    private String monitorStatus;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
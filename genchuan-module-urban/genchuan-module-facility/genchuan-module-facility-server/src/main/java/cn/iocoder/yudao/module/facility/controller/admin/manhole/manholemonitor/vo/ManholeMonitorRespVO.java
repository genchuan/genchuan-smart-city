package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 窨井盖监测 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManholeMonitorRespVO {

    @Schema(description = "主键（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "28680")
    @ExcelProperty("主键（UUID）")
    private Long id;

    @Schema(description = "关联窨井盖表manhole_cover的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11309")
    @ExcelProperty("关联窨井盖表manhole_cover的id")
    private Long coverId;

    @Schema(description = "关联设备表sys_device的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26002")
    @ExcelProperty("关联设备表sys_device的id")
    private Long deviceId;

    @Schema(description = "关联用户表sys_user的id（运维员）", example = "3128")
    @ExcelProperty("关联用户表sys_user的id（运维员）")
    private Long staffId;

    @Schema(description = "关联开合状态表sys_open_status的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23671")
    @ExcelProperty("关联开合状态表sys_open_status的id")
    private Long openStatusId;

    @Schema(description = "倾斜角度（数值，单位：度）")
    @ExcelProperty("倾斜角度（数值，单位：度）")
    private BigDecimal tiltAngle;

    @Schema(description = "振动数据（数值，单位：m/s²）")
    @ExcelProperty("振动数据（数值，单位：m/s²）")
    private BigDecimal vibrationData;

    @Schema(description = "关联风险等级表sys_risk_level的id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29862")
    @ExcelProperty("关联风险等级表sys_risk_level的id")
    private Long riskLevelId;

    @Schema(description = "监测状态：运行中/已停止", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("监测状态：运行中/已停止")
    private String monitorStatus;

    @Schema(description = "[创建时间] 记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}